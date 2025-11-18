package com.music.sale.persistence.product.entity;

import com.music.sale.domain.product.enums.*;
import com.music.sale.persistence.common.BaseEntity;
import com.music.sale.persistence.product.support.JsonMapConverter;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.LastModifiedBy;

import java.util.List;
import java.util.Map;

@Entity
@Table(name = "product_item")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@SuperBuilder
public class ProductItemEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "catalog_id", nullable = false)
    private Long catalogId;

    @Column(name = "seller_id", nullable = false)
    private Long sellerId;

    @Column(name = "store_id", nullable = false)
    private Long storeId;

    //image 추후 업데이트

//    @OneToMany(mappedBy = "productItem", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<ProductImageEntity> images;

    @Column(name = "name", nullable = false, length = 255)
    private String name;

    @Column(name = "brand", length = 255)
    private String brand;

    // 원 단위 가격 -> BIGINT 매핑
    @Column(name = "price", nullable = false)
    private Long price;

    @Enumerated(EnumType.STRING)
    @Column(name = "condition", nullable = false, columnDefinition = "enum('NEW','USED')")
    private ProductCondition condition;

    @Enumerated(EnumType.STRING)
    @Column(name = "condition_grade", nullable = false,
            columnDefinition = "enum('S','A','B','C','D')")
    private ProductConditionGrade conditionGrade;

    @Column(name = "stock_quantity", nullable = false)
    private Integer stockQuantity;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false,
            columnDefinition = "enum('AVAILABLE','SOLD_OUT','RESERVED','DISCONTINUED')")
    private ProductStatus status;

    @Convert(converter = JsonMapConverter.class)
    @Column(name = "custom_attributes", columnDefinition = "text")
    private Map<String, Object> attributes;

    @Lob
    @Column(name = "description")
    private String description;

    @Column(name = "view_count", nullable = false)
    private Long viewCount;
}
