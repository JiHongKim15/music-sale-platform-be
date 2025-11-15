package com.music.sale.domain.product;

import com.music.sale.domain.product.enums.ProductCondition;
import com.music.sale.domain.product.enums.ProductConditionGrade;
import com.music.sale.domain.product.enums.ProductStatus;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Map;

@Value
@Builder(toBuilder = true)
public class ProductItem {
    Long id;
    Long catalogId;
    Long sellerId;
    Long storeId;
    Long imageId;
    String name;
    String brand;
    long price;
    ProductCondition condition;
    ProductConditionGrade conditionGrade;

    @Builder.Default
    int stockQuantity = 1;

    @Builder.Default
    ProductStatus status = ProductStatus.AVAILABLE;

    @Builder.Default
    Map<String, Object> attributes = Collections.emptyMap();

    String description;

    @Builder.Default
    long viewCount = 0L;

    LocalDateTime createdAt;
    LocalDateTime updatedAt;
    Long createdBy;
    Long updatedBy;
}
