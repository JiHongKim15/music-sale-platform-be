package com.music.sale.persistence.product;

import com.music.sale.application.product.port.outport.ProductImageQueryPort;
import com.music.sale.persistence.product.entity.ProductImageEntity;
import com.music.sale.persistence.product.repository.ProductImageQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * LoadImagePort의 Infrastructure 구현체
 */
@Component
@RequiredArgsConstructor
public class ProductProductImageQueryAdapter implements ProductImageQueryPort {

    private final ProductImageQueryRepository productImageQueryRepository;

    /**
     * productId로 이미지 조회 (imageOrder 오름차순)
     * DB에서 조회한 ProductImageEntity를 ImageQueryOutPut으로 변환하여 반환
     */
    @Override
    public List<ImageQueryOutPut> findImagesByProductId(Long productId) {
        List<ProductImageEntity> entities =
                productImageQueryRepository.findByProductIdOrderByImageOrderAsc(productId);

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
