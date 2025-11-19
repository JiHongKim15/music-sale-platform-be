package com.music.sale.domain.product;

import com.music.sale.domain.product.enums.ProductConditionJ;
import com.music.sale.domain.product.enums.ProductConditionGradeJ;
import com.music.sale.domain.product.enums.ProductStatusJ;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Map;

@Value
@Builder(toBuilder = true)
public class ProductItemJ {
    Long id;
    Long catalogId;
    Long sellerId;
    Long storeId;
    String name;
    String brand;
    Long price;
    ProductConditionJ condition;
    ProductConditionGradeJ conditionGrade;

    @Builder.Default
    Integer stockQuantity = 1;

    @Builder.Default
    ProductStatusJ status = ProductStatusJ.AVAILABLE;

    @Builder.Default
    Map<String, Object> attributes = Collections.emptyMap();

    String description;

    @Builder.Default
    Long viewCount = 0L;

    LocalDateTime createdAt;
    LocalDateTime updatedAt;
    Long createdBy;
    Long updatedBy;
}
