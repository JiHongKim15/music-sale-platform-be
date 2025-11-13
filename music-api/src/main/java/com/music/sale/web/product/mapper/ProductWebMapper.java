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
        CreateProductInput input = new CreateProductInput();
        input.setCatalogId(cmd.getCatalogId());
        input.setSellerId(cmd.getSellerId());
        input.setStoreId(cmd.getStoreId());
        input.setName(cmd.getName());
        input.setBrand(cmd.getBrand());
        input.setPrice(cmd.getPrice());
        input.setCondition(cmd.getCondition());
        input.setConditionGrade(cmd.getConditionGrade());
        input.setStockQuantity(cmd.getStockQuantity());
        input.setStatus(cmd.getStatus());
        input.setAttributes(cmd.getAttributes());
        input.setDescription(cmd.getDescription());
        return input;
    }

    public UpdateProductInput toUpdateInput(UpdateProductCommand cmd) {
        UpdateProductInput input = new UpdateProductInput();
        input.setName(cmd.getName());
        input.setBrand(cmd.getBrand());
        input.setStoreId(cmd.getStoreId());
        input.setPrice(cmd.getPrice());
        input.setCondition(cmd.getCondition());
        input.setConditionGrade(cmd.getConditionGrade());
        input.setStockQuantity(cmd.getStockQuantity());
        input.setStatus(cmd.getStatus());
        input.setAttributes(cmd.getAttributes());
        input.setDescription(cmd.getDescription());
        input.setUpdatedBy(cmd.getUpdatedBy());
        return input;
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
