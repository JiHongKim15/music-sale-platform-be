package com.music.sale.persistence.image.mapper;

import com.music.sale.application.image.dto.ImageSaveResult;
import com.music.sale.domain.image.ProductImage;
import com.music.sale.persistence.image.entity.ProductImageEntity;
import org.springframework.stereotype.Component;

@Component
public class ImagePersistenceMapper {

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
    public ImageSaveResult toSaveResult(ProductImageEntity entity) {
        return new ImageSaveResult(
                entity.getId(),
                entity.getUrl()
        );
    }

} // class
