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

    // 도메인 동작: 기존 값 유지 + 부분 변경 → 불변 객체 새로 생성
    public ProductItem changeInfo(UpdateCommand c) {
        return this.toBuilder()
                .name(c.name() != null ? c.name() : name)
                .brand(c.brand() != null ? c.brand() : brand)
                .price(c.price() != null ? c.price() : price)
                .condition(c.condition() != null ? c.condition() : condition)
                .conditionGrade(c.conditionGrade() != null ? c.conditionGrade() : conditionGrade)
                .stockQuantity(c.stockQuantity() != null ? c.stockQuantity() : stockQuantity)
                .status(c.status() != null ? c.status() : status)
                .attributes(c.attributes() != null ? c.attributes() : attributes)
                .description(c.description() != null ? c.description() : description)
                .imageId(c.imageId() != null ? c.imageId() : imageId)
                .updatedBy(c.updatedBy())
                .build();
    }

    public record UpdateCommand(
            String name,
            String brand,
            Long price,
            ProductCondition condition,
            ProductConditionGrade conditionGrade,
            Integer stockQuantity,
            ProductStatus status,
            Map<String, Object> attributes,
            String description,
            Long imageId,
            Long updatedBy
    ) {}
}
