package com.music.sale.application.image.service;

import com.music.sale.application.common.BusinessException;
import com.music.sale.application.common.ErrorCode;
import com.music.sale.application.image.dto.ImageOutput;
import com.music.sale.application.image.dto.ImageSaveResult;
import com.music.sale.application.image.dto.UploadImageInput;
import com.music.sale.application.image.port.inport.ImageUseCase;
import com.music.sale.application.image.port.outport.ImagePort;
import java.util.List;
import java.util.stream.IntStream;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ImageService implements ImageUseCase {

    private final ImagePort imagePort;

    /**
     * 이미지 업로드 비즈니스 로직
     * 1. Infrastructure Layer를 통해 이미지 저장 (DB + URL 생성)
     */
    @Override
    public List<ImageOutput> uploadImage(List<UploadImageInput> inputs) {
        List<ImageSaveResult> saved = imagePort.saveAll(inputs);
        return zipToOutput(saved, inputs);
    }

    /**
     * @param saved DB 저장 후 받은 결과 (id, url)
     * @param inputs 원본 입력 데이터 (파일명, 크기, 타입, 순서, 썸네일 여부 등)
     * @return 저장 결과와 입력을 병합하여 최종 출력 DTO 생성
     */
    private List<ImageOutput> zipToOutput(
            List<ImageSaveResult> saved,
            List<UploadImageInput> inputs
    ) {
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
     * - ImageSaveResult: DB에서 생성된 id, url 제공
     * - UploadImageInput: 파일 메타데이터 제공 (크기, 이름, 타입, 순서 등)
     * @param result DB 저장 결과 (id, url)
     * @param input 원본 입력 데이터 (파일 메타데이터)
     * @return 두 정보를 합친 완전한 ImageOutput
     */
    private ImageOutput createImageOutput(
            ImageSaveResult result,
            UploadImageInput input
    ) {
        return new ImageOutput(
                result.id(),
                input.productId(),
                result.url(),
                input.isThumbnail(),
                input.imageOrder(),
                input.fileSize(),
                input.fileName(),
                input.fileType()
        );
    }

    @Transactional
    public void deleteImage(Long productId, Long imageId) {
        // 1. 비즈니스 규칙 검증: 이미지가 존재하고 해당 상품에 속하는지 확인
        if (!imagePort.existsByIdAndProductId(imageId, productId)) {
            throw new BusinessException(ErrorCode.IMAGE_NOT_FOUND);
        }

        // 2. 검증 통과 후 삭제 실행
        // 현재 DB 레코드만 삭제 (URL만 저장하는 Mock 구조, 향후 실제 스토리지 파일 삭제 로직 추가 필요)
        imagePort.deleteById(imageId);
    }

    @Override
    @Transactional
    public List<ImageOutput> updateThumbnail(Long productId, Long imageId, boolean isThumbnail) {
        if (!imagePort.existsByIdAndProductId(imageId, productId)) {
            throw new BusinessException(ErrorCode.IMAGE_NOT_FOUND);
        }

        // Port에 위임 (URL 생성 포함)
        return imagePort.updateThumbnail(productId, imageId, isThumbnail);
    }

}
