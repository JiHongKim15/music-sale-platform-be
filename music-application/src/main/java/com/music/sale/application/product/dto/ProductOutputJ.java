package com.music.sale.application.product.dto;

import com.music.sale.domain.product.enums.ProductConditionJ;
import com.music.sale.domain.product.enums.ProductConditionGradeJ;
import com.music.sale.domain.product.enums.ProductStatusJ;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Map;

@Getter
@AllArgsConstructor
@Builder
public class ProductOutputJ {
    Long id;
    Long catalogId;
    Long sellerId;
    Long storeId;

    String name;
    String brand;
    Long price;

    ProductConditionJ condition;
    ProductConditionGradeJ conditionGrade;
    Integer stockQuantity;
    ProductStatusJ status;

    Map<String, Object> attributes;
    String description;
    Long viewCount;

    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}
