package com.music.sale.persistence.image;

import com.music.sale.application.image.dto.ImageQueryOutPut;
import com.music.sale.application.image.port.outport.ImageQueryPort;
import com.music.sale.persistence.image.entity.ProductImageEntity;
import com.music.sale.persistence.image.repository.ImageQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * LoadImagePort의 Infrastructure 구현체
 */
@Component
@RequiredArgsConstructor
public class ImageQueryAdapter implements ImageQueryPort {

    private final ImageQueryRepository imageQueryRepository;

    /**
     * productId로 이미지 조회 (imageOrder 오름차순)
     * DB에서 조회한 ProductImageEntity를 ImageQueryOutPut으로 변환하여 반환
     */
    @Override
    public List<ImageQueryOutPut> findImagesByProductId(Long productId) {
        List<ProductImageEntity> entities =
                imageQueryRepository.findByProductIdOrderByImageOrderAsc(productId);

        return entities.stream()
                .map(entity -> ImageQueryOutPut.builder()
                        .id(entity.getId())
                        .productId(entity.getProductId())
                        .url(entity.getUrl())
                        .isThumbnail(entity.isThumbnail())
                        .imageOrder(entity.getImageOrder())
                        .fileSize(entity.getFileSize())
                        .fileName(entity.getFileName())
                        .fileType(entity.getFileType())
                        .build())
                .toList();
    }

}
