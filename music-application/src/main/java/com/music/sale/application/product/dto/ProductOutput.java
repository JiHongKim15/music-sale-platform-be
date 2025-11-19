package com.music.sale.application.product.dto;

import com.music.sale.domain.product.enums.ProductCondition;
import com.music.sale.domain.product.enums.ProductConditionGrade;
import com.music.sale.domain.product.enums.ProductStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Value;

import java.time.LocalDateTime;
import java.util.Map;

@Getter
@AllArgsConstructor
@Builder
public class ProductOutput {
    Long id;
    Long catalogId;
    Long sellerId;
    Long storeId;

    String name;
    String brand;
    Long price;

    ProductCondition condition;
    ProductConditionGrade conditionGrade;
    Integer stockQuantity;
    ProductStatus status;

    Map<String, Object> attributes;
    String description;
    Long viewCount;

    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}
