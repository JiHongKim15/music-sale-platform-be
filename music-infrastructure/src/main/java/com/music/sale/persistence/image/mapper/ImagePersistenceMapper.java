package com.music.sale.persistence.image.mapper;

import com.music.sale.domain.image.ProductImage;
import com.music.sale.persistence.image.entity.ProductImageEntity;
import org.springframework.stereotype.Component;

@Component
public class ImagePersistenceMapper {

    /**
     * 영속성 엔티티 -> 도메인 엔티티 (DB 데이터 조회 후)
     */
    public ProductImage toDomain(ProductImageEntity entity) {
        return new ProductImage(entity.getId());
    }

    /**
     * 도메인 엔티티 -> 영속성 엔티티 (DB 저장 직전)
     */
    public ProductImageEntity toEntity(ProductImage domain, String url, String fileName, String fileType,
        long fileSize, boolean isThumbnail, int imageOrder, Long productId) {
        return new ProductImageEntity(
            domain.id(),
            productId,
            url,
            isThumbnail,
            imageOrder,
            fileSize,
            fileName,
            fileType
        );
    }

} // class
