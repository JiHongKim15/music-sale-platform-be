package com.music.sale.application.image.service;

import com.music.sale.application.common.BusinessException;
import com.music.sale.application.common.ErrorCode;
import com.music.sale.application.image.dto.ImageOutput;
import com.music.sale.application.image.dto.ImageSaveResult;
import com.music.sale.application.image.dto.UploadImageInput;
import com.music.sale.application.image.port.inport.ImageUseCase;
import com.music.sale.application.image.port.outport.ImagePort;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ImageService implements ImageUseCase {

    private final ImagePort imagePort;

    @Override
    public List<ImageOutput> uploadImage(List<UploadImageInput> inputs) {
        // 저장 후 ID + URL 받기
        List<ImageSaveResult> saved = imagePort.saveAll(inputs);
        return zipToOutput(saved, inputs);
    }

    private List<ImageOutput> zipToOutput(
            List<ImageSaveResult> saved,
            List<UploadImageInput> inputs
    ) {
        if (saved.size() != inputs.size()) {
            throw new IllegalStateException("저장된 이미지 수와 요청 수가 일치하지 않습니다.");
        }

        return IntStream.range(0, saved.size())
                .mapToObj(i -> createImageOutput(saved.get(i), inputs.get(i)))
                .toList();
    }

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
        // 이미지 존재 여부 및 상품 일치 확인
        if (!imagePort.existsByIdAndProductId(imageId, productId)) {
            throw new BusinessException(ErrorCode.IMAGE_NOT_FOUND);
        }

        try {
            // 이미지 삭제
            imagePort.deleteById(imageId);
        } catch (Exception e) {
            throw new BusinessException(ErrorCode.IMAGE_DELETE_FAILED);
        }
    }

    @Override
    @Transactional
    public List<ImageOutput> updateThumbnail(Long productId, Long imageId, boolean isThumbnail) {
        if (!imagePort.existsByIdAndProductId(imageId, productId)) {
            throw new IllegalArgumentException("image not found for product");
        }

        return imagePort.updateThumbnail(productId, imageId, isThumbnail)
            .stream()
            .map(domain -> new ImageOutput(
                domain.getId(),
                productId,
                generateUrl(productId, domain.getFileName()), // 도메인에는 URL이 없으므로 생성
                domain.isThumbnail(),
                domain.getImageOrder(),
                domain.getFileSize(),
                domain.getFileName(),
                domain.getFileType(),
                null,  // createdAt은 엔티티에서만 관리
                null   // updatedAt은 엔티티에서만 관리
            ))
            .collect(Collectors.toList());
    }

}
