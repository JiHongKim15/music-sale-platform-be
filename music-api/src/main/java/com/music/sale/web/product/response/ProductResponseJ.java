package com.music.sale.web.product.response;

import com.music.sale.domain.product.enums.ProductConditionJ;
import com.music.sale.domain.product.enums.ProductConditionGradeJ;
import com.music.sale.domain.product.enums.ProductStatusJ;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;
import java.util.Map;

@Value
@Builder
public class ProductResponseJ {

    Long id;
    Long catalogId;
    Long sellerId;
    Long storeId;

    String name;
    String brand;
    Long price;

    ProductConditionJ condition;
    ProductConditionGradeJ conditionGrade;
    int stockQuantity;
    ProductStatusJ status;

    Map<String, Object> attributes;
    String description;
    Long viewCount;

    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}
