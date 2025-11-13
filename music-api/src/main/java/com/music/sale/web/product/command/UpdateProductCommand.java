package com.music.sale.web.product.command;

import com.music.sale.domain.product.enums.ProductCondition;
import com.music.sale.domain.product.enums.ProductConditionGrade;
import com.music.sale.domain.product.enums.ProductStatus;
import lombok.Data;

import java.util.Map;

@Data
public class UpdateProductCommand {
    private String name;
    private String brand;
    private Long storeId;
    private Long price;

    private ProductCondition condition;
    private ProductConditionGrade conditionGrade;
    private Integer stockQuantity;
    private ProductStatus status;

    private Map<String, Object> attributes;
    private String description;
    private Long updatedBy;
}
