package com.music.sale.application.product.dto.input;

import com.music.sale.domain.product.enums.ProductCondition;
import com.music.sale.domain.product.enums.ProductConditionGrade;
import com.music.sale.domain.product.enums.ProductStatus;
import java.util.Map;

public record UpdateProductInput(
    String name,
    String brand,
    Long catalogId,
    Long storeId,
    Long price,
    ProductCondition condition,
    ProductConditionGrade conditionGrade,
    Integer stockQuantity,
    ProductStatus status,
    Map<String, Object> attributes,
    String description) {}
