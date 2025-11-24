package com.music.sale.web.product.mapper;

import com.music.sale.application.product.dto.output.CreateProductOutput;
import com.music.sale.application.product.dto.output.GetProductOutput;
import com.music.sale.application.product.dto.output.UpdateProductOutput;
import com.music.sale.web.product.response.ProductResponse;
import org.springframework.stereotype.Component;

@Component
public class ProductWebMapper {

    public ProductResponse toCreateProductResponse(CreateProductOutput output) {
        return ProductResponse.builder()
                .id(output.getId())
                .catalogId(output.getCatalogId())
                .sellerId(output.getSellerId())
                .storeId(output.getStoreId())
                .name(output.getName())
                .brand(output.getBrand())
                .price(output.getPrice())
                .condition(output.getCondition())
                .conditionGrade(output.getConditionGrade())
                .stockQuantity(output.getStockQuantity())
                .status(output.getStatus())
                .attributes(output.getAttributes())
                .description(output.getDescription())
                .viewCount(output.getViewCount())
                .build();
    }

    public ProductResponse toUpdateProductResponse(UpdateProductOutput output) {
        return ProductResponse.builder()
                .id(output.getId())
                .catalogId(output.getCatalogId())
                .sellerId(output.getSellerId())
                .storeId(output.getStoreId())
                .name(output.getName())
                .brand(output.getBrand())
                .price(output.getPrice())
                .condition(output.getCondition())
                .conditionGrade(output.getConditionGrade())
                .stockQuantity(output.getStockQuantity())
                .status(output.getStatus())
                .attributes(output.getAttributes())
                .description(output.getDescription())
                .viewCount(output.getViewCount())
                .build();
    }

    public ProductResponse toGetProductResponse(GetProductOutput output) {
        return ProductResponse.builder()
                .id(output.getId())
                .catalogId(output.getCatalogId())
                .sellerId(output.getSellerId())
                .storeId(output.getStoreId())
                .name(output.getName())
                .brand(output.getBrand())
                .price(output.getPrice())
                .condition(output.getCondition())
                .conditionGrade(output.getConditionGrade())
                .stockQuantity(output.getStockQuantity())
                .status(output.getStatus())
                .attributes(output.getAttributes())
                .description(output.getDescription())
                .viewCount(output.getViewCount())
                .build();
    }
}
