package com.music.sale.application.product.service;

import com.music.sale.common.BusinessException;
import com.music.sale.common.ErrorCode;
import com.music.sale.application.product.dto.output.ProductImageOutput;
import com.music.sale.application.product.dto.ProductImageSaveResult;
import com.music.sale.application.product.dto.input.UpdateProductImageInput;
import com.music.sale.application.product.port.inport.ProductImageCommandUseCase;
import com.music.sale.application.product.port.outport.ProductImageCommandPort;
import java.util.List;
import java.util.stream.IntStream;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 이미지 업로드 및 삭제 비즈니스 로직을 담당하는 서비스
 *
 * 헥사고날 아키텍처의 Application Layer에 위치하며,
 * ImageUseCase 인터페이스를 구현하여 비즈니스 로직을 제공합니다.
 */
@Service
@Transactional  // 클래스 레벨: 모든 public 메서드에 트랜잭션 적용
@RequiredArgsConstructor
public class ProductImageCommandService implements ProductImageCommandUseCase {

    private final ProductImageCommandPort productImageCommandPort;  // Infrastructure Layer와의 통신을 위한 Port

    /**
     * 이미지 업로드 비즈니스 로직
     *
     * 처리 흐름:
     * 1. Infrastructure Layer를 통해 이미지 저장 (DB + URL 생성)
     * 2. 저장 결과(ImageSaveResult)와 입력(UploadImageInput)을 병합
     * 3. 클라이언트에게 반환할 ImageOutput으로 변환
     *
     * @param inputs 업로드할 이미지 정보 리스트 (파일명, 크기, 타입, 순서 등)
     * @return 저장된 이미지 정보 리스트 (DB에서 생성된 ID와 URL 포함)
     */
    @Override
    public List<ProductImageOutput> uploadImage(List<UpdateProductImageInput> inputs) {
        // 1. Infrastructure Layer에 저장 요청 (DB INSERT + URL 생성)
        // ImageSaveResult에는 DB에서 생성된 ID와 URL만 포함됨
        List<ProductImageSaveResult> saved = productImageCommandPort.saveAll(inputs);

        // 2. 저장 결과와 원본 입력을 병합하여 완전한 응답 DTO 생성
        return zipToOutput(saved, inputs);
    }

    /**
     * 저장 결과와 입력을 병합하여 최종 출력 DTO 생성
     *
     * ImageSaveResult(id, url)와 UploadImageInput(파일 메타데이터)을
     * 하나의 ImageOutput으로 합쳐서 클라이언트에게 완전한 정보를 제공합니다.
     *
     * @param saved DB 저장 후 받은 결과 (id, url)
     * @param inputs 원본 입력 데이터 (파일명, 크기, 타입, 순서, 썸네일 여부 등)
     * @return 병합된 최종 출력 DTO 리스트
     * @throws IllegalStateException 저장된 개수와 입력 개수가 불일치할 경우
     */
    private List<ProductImageOutput> zipToOutput(
            List<ProductImageSaveResult> saved,
            List<UpdateProductImageInput> inputs
    ) {
        // 안전성 검증: 저장 결과와 입력 개수가 일치하지 않으면 데이터 정합성 오류
        if (saved.size() != inputs.size()) {
            throw new IllegalStateException("저장된 이미지 수와 요청 수가 일치하지 않습니다.");
        }

        // IntStream을 사용하여 인덱스 기반으로 두 리스트를 병합
        // 예: saved.get(0) + inputs.get(0) -> ImageOutput
        //     saved.get(1) + inputs.get(1) -> ImageOutput
        return IntStream.range(0, saved.size())
                .mapToObj(i -> createImageOutput(saved.get(i), inputs.get(i)))
                .toList();
    }

    /**
     * ImageSaveResult와 UploadImageInput을 병합하여 ImageOutput 생성
     *
     * 역할:
     * - ImageSaveResult: DB에서 생성된 id, url 제공
     * - UploadImageInput: 파일 메타데이터 제공 (크기, 이름, 타입, 순서 등)
     *
     * @param result DB 저장 결과 (id, url)
     * @param input 원본 입력 데이터 (파일 메타데이터)
     * @return 두 정보를 합친 완전한 ImageOutput
     */
    private ProductImageOutput createImageOutput(
            ProductImageSaveResult result,
            UpdateProductImageInput input
    ) {
        return new ProductImageOutput(
                result.id(),           // DB에서 생성된 이미지 ID
                input.productId(),     // 연결된 상품 ID
                result.url(),          // 생성된 이미지 접근 URL
                input.isThumbnail(),   // 썸네일 여부
                input.imageOrder(),    // 이미지 표시 순서
                input.fileSize(),      // 파일 크기 (bytes)
                input.fileName(),      // 원본 파일명
                input.fileType()       // 파일 타입 (MIME type)
        );
    }

    /**
     * 이미지 삭제 비즈니스 로직
     *
     * 처리 흐름:
     * 1. 이미지 존재 여부 확인 (imageId와 productId가 모두 일치하는지)
     * 2. 존재하지 않으면 BusinessException 발생 (GlobalExceptionHandler가 처리)
     * 3. 존재하면 DB에서 삭제
     *
     * @Transactional이 적용되어 있어, 예외 발생 시 자동 롤백됩니다.
     *
     * @param productId 상품 ID (이미지가 속한 상품, 보안 검증용)
     * @param imageId 삭제할 이미지 ID
     * @throws BusinessException 이미지가 존재하지 않거나 productId가 일치하지 않을 경우
     */
    @Transactional
    public void deleteImage(Long productId, Long imageId) {
        // 1. 비즈니스 규칙 검증: 이미지가 존재하고 해당 상품에 속하는지 확인
        // productId도 함께 확인하는 이유:
        // - 다른 상품의 이미지를 삭제하는 것을 방지
        // - RESTful URL 구조와 일치 (/products/{productId}/images/{imageId})
        // - 향후 권한 검증 로직 추가 시 productId 기반으로 소유자 확인 가능
        if (!productImageCommandPort.existsByIdAndProductId(imageId, productId)) {
            throw new BusinessException(ErrorCode.IMAGE_NOT_FOUND);
        }

        // 2. 검증 통과 후 삭제 실행
        // 현재는 DB 레코드만 삭제 (URL만 저장하는 Mock 구조)
        // 향후 실제 스토리지(S3 등) 연동 시 파일 삭제 로직 추가 필요
        productImageCommandPort.deleteById(imageId);

        // 참고: try-catch가 없는 이유
        // - @Transactional이 RuntimeException 발생 시 자동 롤백
        // - GlobalExceptionHandler가 모든 예외를 최종 처리하여 HTTP 응답 생성
        // - 예외를 그대로 전파해야 실제 에러 원인을 로그에서 확인 가능
    }

}
