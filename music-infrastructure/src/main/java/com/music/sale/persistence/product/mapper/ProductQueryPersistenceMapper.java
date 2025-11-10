package com.music.sale.persistence.product.mapper;

import com.music.sale.domain.product.ProductItem;
import com.music.sale.persistence.product.entity.ProductItemEntity;
import org.springframework.stereotype.Component;

@Component
public class ProductQueryPersistenceMapper {

    /** 엔티티 -> 도메인 (조회 응답용) */
    public ProductItem toDomain(ProductItemEntity e) {
        return ProductItem.builder()
                .id(e.getId())
                .catalogId(e.getCatalogId())
                .sellerId(e.getSellerId())
                .storeId(e.getStoreId())
                .name(e.getName())
                .brand(e.getBrand())
                .price(e.getPrice())
                .condition(e.getCondition())
                .conditionGrade(e.getConditionGrade())
                .stockQuantity(e.getStockQuantity())
                .status(e.getStatus())
                .attributes(e.getAttributes())
                .description(e.getDescription())
                .viewCount(e.getViewCount())
                .createdAt(e.getCreatedAt())
                .updatedAt(e.getUpdatedAt())
                .createdBy(e.getCreatedBy())
                .updatedBy(e.getUpdatedBy())
                .build();
    }
}
