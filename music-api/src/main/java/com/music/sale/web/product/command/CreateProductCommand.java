package com.music.sale.web.product.command;

import com.music.sale.domain.product.enums.ProductCondition;
import com.music.sale.domain.product.enums.ProductConditionGrade;
import com.music.sale.domain.product.enums.ProductStatus;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.util.Map;

@Getter
@AllArgsConstructor
public class CreateProductCommand {
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
