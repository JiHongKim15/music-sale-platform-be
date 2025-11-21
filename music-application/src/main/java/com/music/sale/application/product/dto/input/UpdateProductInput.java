package com.music.sale.application.product.dto.input;

import com.music.sale.domain.product.enums.ProductCondition;
import com.music.sale.domain.product.enums.ProductConditionGrade;
import com.music.sale.domain.product.enums.ProductStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
@AllArgsConstructor
@Builder
public class UpdateProductInput {
    private String name;
    private String brand;
    private Long catalogId;
    private Long storeId;
    private Long price;

    private ProductCondition condition;
    private ProductConditionGrade conditionGrade;
    private Integer stockQuantity;
    private ProductStatus status;

    private Map<String, Object> attributes;
    private String description;
}

