package com.music.sale.application.product.mapper;

import com.music.sale.application.product.dto.input.CreateProductInput;
import com.music.sale.application.product.dto.input.UpdateProductInput;
import com.music.sale.application.product.dto.output.CreateProductOutput;
import com.music.sale.application.product.dto.output.GetProductOutput;
import com.music.sale.application.product.dto.output.UpdateProductOutput;
import com.music.sale.domain.product.ProductItem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductMapper {
    public ProductItem toDomainForCreate(CreateProductInput input, Long createdBy) {
        return ProductItem.builder()
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

    public ProductItem toDomainForUpdate(ProductItem existing, UpdateProductInput input) {
        return existing.toBuilder()
                .name(getValueOrDefault(input.getName(), existing.getName()))
                .brand(getValueOrDefault(input.getBrand(), existing.getBrand()))
                .catalogId(getValueOrDefault(input.getCatalogId(), existing.getCatalogId()))
                .storeId(getValueOrDefault(input.getStoreId(), existing.getStoreId()))
                .price(getValueOrDefault(input.getPrice(), existing.getPrice()))
                .condition(getValueOrDefault(input.getCondition(), existing.getCondition()))
                .conditionGrade(getValueOrDefault(input.getConditionGrade(), existing.getConditionGrade()))
                .stockQuantity(getValueOrDefault(input.getStockQuantity(), existing.getStockQuantity()))
                .status(getValueOrDefault(input.getStatus(), existing.getStatus()))
                .attributes(getValueOrDefault(input.getAttributes(), existing.getAttributes()))
                .description(getValueOrDefault(input.getDescription(), existing.getDescription()))
                .build();
    }

    private <T> T getValueOrDefault(T value, T defaultValue) {
        return value != null ? value : defaultValue;
    }

    public CreateProductOutput toCreateOutput(ProductItem item) {
        return CreateProductOutput.builder()
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
                .build();
    }

    public UpdateProductOutput toUpdateOutput(ProductItem item) {
        return UpdateProductOutput.builder()
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
                .build();
    }

    public GetProductOutput toGetOutput(ProductItem item) {
        return GetProductOutput.builder()
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
                .build();
    }
}
