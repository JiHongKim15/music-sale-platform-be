package com.music.sale.persistence.product.mapper;

import com.music.sale.application.product.dto.ProductImageSaveResult;
import com.music.sale.domain.product.ProductImage;
import com.music.sale.persistence.product.entity.ProductImageEntity;
import org.springframework.stereotype.Component;

@Component
public class ProductImagePersistenceMapper {

    /**
     * Domain + URL + productId → Entity
     * 영속성 엔티티 -> 도메인 엔티티 (DB 데이터 조회 후)
     */
    public ProductImageEntity toEntity(
            ProductImage domain,
            String url,
            Long productId
    ) {
        return new ProductImageEntity(
                domain.id(),
                productId,
                url,
                domain.isThumbnail(),
                domain.imageOrder(),
                domain.fileSize(),
                domain.fileName(),
                domain.fileType()
        );
    }

    /**
     * 도메인 엔티티 -> 영속성 엔티티 (DB 저장 직전)
     */
    public ProductImage toDomain(ProductImageEntity entity) {
        return new ProductImage(
                entity.getId(),
                entity.getFileName(),
                entity.getFileType(),
                entity.getFileSize(),
                entity.isThumbnail(),
                entity.getImageOrder()
        );
    }

    /**
     * Entity → ImageSaveResult (ID + URL)
     */
    public ProductImageSaveResult toSaveResult(ProductImageEntity entity) {
        return new ProductImageSaveResult(
                entity.getId(),
                entity.getUrl()
        );
    }

} // class
