package com.music.sale.persistence.product.mapper;

import com.music.sale.domain.product.ProductItem;
import com.music.sale.persistence.product.entity.ProductItemEntity;
import org.springframework.stereotype.Component;

@Component
public class ProductItemPersistenceMapper {

  public ProductItemEntity toEntity(ProductItem domain) {
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
        .build();
  }
}
