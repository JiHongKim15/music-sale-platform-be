package com.music.sale.web.product.response;

import com.music.sale.domain.product.enums.ProductCondition;
import com.music.sale.domain.product.enums.ProductConditionGrade;
import com.music.sale.domain.product.enums.ProductStatus;
import java.util.Map;
import lombok.Builder;

@Builder
public record CreateProductResponse(
    Long id,
    Long catalogId,
    Long sellerId,
    Long storeId,
    String name,
    String brand,
    Long price,
    ProductCondition condition,
    ProductConditionGrade conditionGrade,
    int stockQuantity,
    ProductStatus status,
    Map<String, Object> attributes,
    String description,
    Long viewCount) {}
