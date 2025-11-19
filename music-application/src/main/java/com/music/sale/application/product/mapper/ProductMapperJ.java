package com.music.sale.application.product.mapper;

import com.music.sale.application.product.dto.CreateProductInputJ;


import com.music.sale.application.product.dto.ProductOutputJ;
import com.music.sale.domain.product.ProductItemJ;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductMapperJ {
    public ProductItemJ toDomainForCreate(CreateProductInputJ input, Long createdBy) {
        return ProductItemJ.builder()
                .catalogId(input.getCatalogId())
                .sellerId(input.getSellerId())
                .storeId(input.getStoreId())
                .name(input.getName())
                .brand(input.getBrand())
                .price(input.getPrice())
                .condition(input.getCondition())
                .conditionGrade(input.getConditionGrade())
                .stockQuantity(input.getStockQuantity())
                .status(input.getStatus())
                .attributes(input.getAttributes())
                .description(input.getDescription())
                .viewCount(0L)
                .build();
    }

    public ProductOutputJ toOutput(ProductItemJ item) {
        return ProductOutputJ.builder()
                .id(item.getId())
                .catalogId(item.getCatalogId())
                .sellerId(item.getSellerId())
                .storeId(item.getStoreId())
                .name(item.getName())
                .brand(item.getBrand())
                .price(item.getPrice())
                .condition(item.getCondition())
                .conditionGrade(item.getConditionGrade())
                .stockQuantity(item.getStockQuantity())
                .status(item.getStatus())
                .attributes(item.getAttributes())
                .description(item.getDescription())
                .viewCount(item.getViewCount())
                .createdAt(item.getCreatedAt())
                .updatedAt(item.getUpdatedAt())
                .build();
    }
}
