package com.music.sale.persistence.product.mapper;

import com.music.sale.domain.product.ProductImage;
import com.music.sale.persistence.product.entity.ProductImageEntity;
import org.springframework.stereotype.Component;

@Component
public class ProductImagePersistenceMapper {

    public ProductImageEntity toEntity(ProductImage domain) {
        return new ProductImageEntity(
                domain.getId(),
                domain.getProductId(),
                domain.getUrl(),
                domain.isThumbnail(),
                domain.getImageOrder(),
                domain.getFileSize(),
                domain.getFileName(),
                domain.getFileType()
        );
    }

    public ProductImage toDomain(ProductImageEntity entity) {
        return ProductImage.builder()
                .id(entity.getId())
                .productId(entity.getProductItemId())
                .url(entity.getUrl())
                .isThumbnail(entity.isThumbnail())
                .imageOrder(entity.getImageOrder())
                .fileSize(entity.getFileSize())
                .fileName(entity.getFileName())
                .fileType(entity.getFileType())
                .build();
    }
}
