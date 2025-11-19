package com.music.sale.web.product.command;

import com.music.sale.domain.product.enums.ProductCondition;
import com.music.sale.domain.product.enums.ProductConditionGrade;
import com.music.sale.domain.product.enums.ProductStatus;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.util.Map;

@Getter
@AllArgsConstructor
public class UpdateProductCommand {
    private String name;
    private String brand;
    private Long storeId;
    private Long catalogId;
    private Long price;

    private ProductCondition condition;
    private ProductConditionGrade conditionGrade;
    private Integer stockQuantity;
    private ProductStatus status;

    private Map<String, Object> attributes;
    private String description;
}
