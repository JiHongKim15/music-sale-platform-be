package com.music.sale.web.product.mapper;

import com.music.sale.application.product.dto.CreateProductInput;
import com.music.sale.application.product.dto.ProductOutput;
import com.music.sale.application.product.dto.UpdateProductInput;
import com.music.sale.web.product.command.CreateProductCommand;
import com.music.sale.web.product.command.UpdateProductCommand;
import com.music.sale.web.product.response.ProductResponse;
import org.springframework.stereotype.Component;

@Component
public class ProductWebMapper {

    public CreateProductInput toCreateInput(CreateProductCommand cmd) {
        return new CreateProductInput (
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

    public UpdateProductInput toUpdateInput(UpdateProductCommand cmd) {
        return new UpdateProductInput(
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
