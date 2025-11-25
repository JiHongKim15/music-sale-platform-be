package com.music.sale.application.product.dto.input;

import java.util.Map;

import com.music.sale.domain.product.enums.ProductCondition;
import com.music.sale.domain.product.enums.ProductConditionGrade;
import com.music.sale.domain.product.enums.ProductStatus;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

public record CreateProductInput(
    @NotNull
    Long catalogId,

    @NotNull
    Long sellerId,

    @NotNull
    Long storeId,

    @NotBlank
    String name,

    String brand,

    @NotNull
    @PositiveOrZero
    Long price,

    @NotNull
    ProductCondition condition,

    ProductConditionGrade conditionGrade,

    @NotNull
    @Positive
    Integer stockQuantity,

    @NotNull
    ProductStatus status,

    Map<String, Object> attributes,

    @NotBlank
    String description
) {}
