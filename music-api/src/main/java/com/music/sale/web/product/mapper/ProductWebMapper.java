package com.music.sale.web.product.mapper;

import com.music.sale.application.product.dto.output.ProductOutput;
import com.music.sale.web.product.response.ProductResponse;
import org.springframework.stereotype.Component;

@Component
public class ProductWebMapper {

    public ProductResponse toResponse(ProductOutput output) {
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
                .createdAt(output.getCreatedAt())
                .updatedAt(output.getUpdatedAt())
                .build();
    }
}
