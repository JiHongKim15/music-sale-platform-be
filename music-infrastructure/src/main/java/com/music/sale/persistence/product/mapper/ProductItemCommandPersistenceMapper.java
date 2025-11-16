package com.music.sale.persistence.product.mapper;

import com.music.sale.domain.product.ProductItem;
import com.music.sale.persistence.product.entity.ProductItemEntity;
import org.springframework.stereotype.Component;

@Component
public class ProductItemCommandPersistenceMapper {

    /** 도메인 -> 엔티티 (생성용) */
    public ProductItemEntity toEntityForCreate(ProductItem domain) {
        return ProductItemEntity.builder()
                .id(domain.getId())
                .catalogId(domain.getCatalogId())
                .sellerId(domain.getSellerId())
                .storeId(domain.getStoreId())
                .name(domain.getName())
                .brand(domain.getBrand())
                .price(domain.getPrice())
                .condition(domain.getCondition())
                .conditionGrade(domain.getConditionGrade())
                .stockQuantity(domain.getStockQuantity())
                .status(domain.getStatus())
                .attributes(domain.getAttributes())
                .description(domain.getDescription())
                .viewCount(domain.getViewCount())
                .build();
    }

    /** 도메인 -> 엔티티 (수정용) */
    public ProductItemEntity toEntityForUpdate(ProductItem domain, ProductItemEntity existing) {
        return ProductItemEntity.builder()
                .id(existing.getId())
                .catalogId(domain.getCatalogId())
                .sellerId(domain.getSellerId())
                .storeId(domain.getStoreId())
                .name(domain.getName())
                .brand(domain.getBrand())
                .price(domain.getPrice())
                .condition(domain.getCondition())
                .conditionGrade(domain.getConditionGrade())
                .stockQuantity(domain.getStockQuantity())
                .status(domain.getStatus())
                .attributes(domain.getAttributes())
                .description(domain.getDescription())
                .viewCount(domain.getViewCount())
                .createdAt(existing.getCreatedAt())
                .createdBy(existing.getCreatedBy())
                .updatedAt(existing.getUpdatedAt())
                .updatedBy(existing.getUpdatedBy())
                .build();
    }


    /** 엔티티 -> 도메인 (DB 저장 후 결과 반환용) */
    public ProductItem toDomain(ProductItemEntity entity) {
        return ProductItem.builder()
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
