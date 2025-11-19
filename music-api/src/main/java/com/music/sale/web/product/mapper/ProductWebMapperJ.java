package com.music.sale.web.product.mapper;

import com.music.sale.application.product.dto.CreateProductInputJ;
import com.music.sale.application.product.dto.ProductOutputJ;
import com.music.sale.application.product.dto.UpdateProductInputJ;
import com.music.sale.web.product.command.CreateProductCommandJ;
import com.music.sale.web.product.command.UpdateProductCommandJ;
import com.music.sale.web.product.response.ProductResponseJ;
import org.springframework.stereotype.Component;

@Component
public class ProductWebMapperJ {

    public CreateProductInputJ toCreateInput(CreateProductCommandJ cmd) {
        return new CreateProductInputJ(
                cmd.getCatalogId(),
                cmd.getSellerId(),
                cmd.getStoreId(),
                cmd.getName(),
                cmd.getBrand(),
                cmd.getPrice(),
                cmd.getCondition(),
                cmd.getConditionGrade(),
                cmd.getStockQuantity(),
                cmd.getStatus(),
                cmd.getAttributes(),
                cmd.getDescription()
        );
    }

    public UpdateProductInputJ toUpdateInput(UpdateProductCommandJ cmd) {
        return new UpdateProductInputJ(
                cmd.getName(),
                cmd.getBrand(),
                cmd.getCatalogId(),
                cmd.getStoreId(),
                cmd.getPrice(),
                cmd.getCondition(),
                cmd.getConditionGrade(),
                cmd.getStockQuantity(),
                cmd.getStatus(),
                cmd.getAttributes(),
                cmd.getDescription()
        );
    }

    public ProductResponseJ toResponse(ProductOutputJ output) {
        return ProductResponseJ.builder()
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
