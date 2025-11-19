package com.music.sale.persistence.product.mapper;

import com.music.sale.domain.product.ProductItemJ;
import com.music.sale.persistence.product.entity.ProductItemEntityJ;
import org.springframework.stereotype.Component;

@Component
public class ProductItemQueryPersistenceMapperJ {

    /** 엔티티 -> 도메인 (조회 응답용) */
    public ProductItemJ toDomain(ProductItemEntityJ entity) {
        return ProductItemJ.builder()
                .id(entity.getId())
                .catalogId(entity.getCatalogId())
                .sellerId(entity.getSellerId())
                .storeId(entity.getStoreId())
                .name(entity.getName())
                .brand(entity.getBrand())
                .price(entity.getPrice())
                .condition(entity.getCondition())
                .conditionGrade(entity.getConditionGrade())
                .stockQuantity(entity.getStockQuantity())
                .status(entity.getStatus())
                .attributes(entity.getAttributes())
                .description(entity.getDescription())
                .viewCount(entity.getViewCount())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .createdBy(entity.getCreatedBy())
                .updatedBy(entity.getUpdatedBy())
                .build();
    }
}
