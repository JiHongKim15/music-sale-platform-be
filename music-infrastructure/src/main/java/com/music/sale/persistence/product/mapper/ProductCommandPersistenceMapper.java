package com.music.sale.persistence.product.mapper;

import com.music.sale.domain.product.ProductItem;
import com.music.sale.persistence.product.entity.ProductItemEntity;
import org.springframework.stereotype.Component;

@Component
public class ProductCommandPersistenceMapper {

    /** 도메인 -> 엔티티 (신규/수정 공통) */
    public ProductItemEntity toEntity(ProductItem item) {
        ProductItemEntity e = new ProductItemEntity();
        e.setId(item.getId());
        e.setCatalogId(item.getCatalogId());
        e.setSellerId(item.getSellerId());
        e.setStoreId(item.getStoreId());
        e.setName(item.getName());
        e.setBrand(item.getBrand());
        e.setPrice(item.getPrice());
        e.setCondition(item.getCondition());
        e.setConditionGrade(item.getConditionGrade());
        e.setStockQuantity(item.getStockQuantity());
        e.setStatus(item.getStatus());
        e.setAttributes(item.getAttributes());
        e.setDescription(item.getDescription());
        e.setViewCount(item.getViewCount());
        return e;
    }

    /** 엔티티 -> 도메인 (DB 저장 후 결과 반환용) */
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
