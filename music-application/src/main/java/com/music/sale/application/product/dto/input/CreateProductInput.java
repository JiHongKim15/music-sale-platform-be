package com.music.sale.application.product.dto.input;

import java.util.Map;

import com.music.sale.domain.product.enums.ProductCondition;
import com.music.sale.domain.product.enums.ProductConditionGrade;
import com.music.sale.domain.product.enums.ProductStatus;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class CreateProductInput {
    @NotNull
    private Long catalogId;

    @NotNull
    private Long sellerId;

    @NotNull
    private Long storeId;

    @NotBlank
    private String name;

    private String brand;

    @NotNull
    @PositiveOrZero
    private Long price;

    @NotNull
    private ProductCondition condition;

    private ProductConditionGrade conditionGrade;

    @NotNull
    @Positive
    private Integer stockQuantity;

    @NotNull
    private ProductStatus status;

    private Map<String, Object> attributes;

    @NotBlank
    private String description;
}
