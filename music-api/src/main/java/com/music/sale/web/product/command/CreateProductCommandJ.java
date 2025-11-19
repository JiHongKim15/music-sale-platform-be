package com.music.sale.web.product.command;

import com.music.sale.domain.product.enums.ProductConditionJ;
import com.music.sale.domain.product.enums.ProductConditionGradeJ;
import com.music.sale.domain.product.enums.ProductStatusJ;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;

@Getter
@AllArgsConstructor
public class CreateProductCommandJ {
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
    private ProductConditionJ condition;

    private ProductConditionGradeJ conditionGrade;

    @NotNull
    @Positive
    private Integer stockQuantity;

    @NotNull
    private ProductStatusJ status;

    private Map<String, Object> attributes;

    @NotBlank
    private String description;
}
