package com.music.sale.web.product.command;

import com.music.sale.domain.product.enums.ProductConditionJ;
import com.music.sale.domain.product.enums.ProductConditionGradeJ;
import com.music.sale.domain.product.enums.ProductStatusJ;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;

@Getter
@AllArgsConstructor
public class UpdateProductCommandJ {
    private String name;
    private String brand;
    private Long storeId;
    private Long catalogId;
    private Long price;

    private ProductConditionJ condition;
    private ProductConditionGradeJ conditionGrade;
    private Integer stockQuantity;
    private ProductStatusJ status;

    private Map<String, Object> attributes;
    private String description;
}
