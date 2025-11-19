package com.music.sale.application.product.dto;

import com.music.sale.domain.product.enums.ProductConditionJ;
import com.music.sale.domain.product.enums.ProductConditionGradeJ;
import com.music.sale.domain.product.enums.ProductStatusJ;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
@AllArgsConstructor
@Builder
public class CreateProductInputJ {
    private Long catalogId;
    private Long sellerId;
    private Long storeId;

    private String name;
    private String brand;
    private Long price;

    private ProductConditionJ condition;
    private ProductConditionGradeJ conditionGrade;
    private Integer stockQuantity;
    private ProductStatusJ status;

    private Map<String, Object> attributes;
    private String description;
}
