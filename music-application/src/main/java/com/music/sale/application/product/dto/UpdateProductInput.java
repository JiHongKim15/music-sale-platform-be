package com.music.sale.application.product.dto;

import com.music.sale.domain.product.enums.ProductCondition;
import com.music.sale.domain.product.enums.ProductConditionGrade;
import com.music.sale.domain.product.enums.ProductStatus;
import lombok.Data;

import java.util.Map;

@Data
public class UpdateProductInput {
    private String name;
    private String brand;
    private String storeId;
    private Long price;

    private ProductCondition condition;
    private ProductConditionGrade conditionGrade;
    private Integer stockQuantity;
    private ProductStatus status;

    private Map<String, Object> attributes;
    private String description;
    private Long updatedBy;
}
