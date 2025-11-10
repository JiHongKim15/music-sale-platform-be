package com.music.sale.persistence.product.entity;

import com.music.sale.domain.product.enums.*;
import com.music.sale.persistence.common.BaseEntity;
import com.music.sale.persistence.product.support.JsonMapConverter;
import jakarta.persistence.*;

import java.util.List;
import java.util.Map;

@Entity
@Table(name = "product_item")
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

    @OneToMany(mappedBy = "productItem", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductImageEntity> images;

    @Column(name = "name", nullable = false, length = 255)
    private String name;

    @Column(name = "brand", length = 255)
    private String brand;

    // 원 단위 가격 -> BIGINT 매핑
    @Column(name = "price", nullable = false)
    private long price;

    @Enumerated(EnumType.STRING)
    @Column(name = "condition", nullable = false, columnDefinition = "enum('NEW','USED')")
    private ProductCondition condition;

    @Enumerated(EnumType.STRING)
    @Column(name = "condition_grade", nullable = false,
            columnDefinition = "enum('S','A','B','C','D')")
    private ProductConditionGrade conditionGrade;

    @Column(name = "stock_quantity", nullable = false)
    private int stockQuantity;

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
    private long viewCount;

    // ===== Getters/Setters =====

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getCatalogId() { return catalogId; }
    public void setCatalogId(Long catalogId) { this.catalogId = catalogId; }

    public Long getSellerId() { return sellerId; }
    public void setSellerId(Long sellerId) { this.sellerId = sellerId; }

    public Long getStoreId() { return storeId; }
    public void setStoreId(Long storeId) { this.storeId = storeId; }

    //image 추후 업데이트

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getBrand() { return brand; }
    public void setBrand(String brand) { this.brand = brand; }

    public long getPrice() { return price; }
    public void setPrice(long price) { this.price = price; }

    public ProductCondition getCondition() { return condition; }
    public void setCondition(ProductCondition condition) { this.condition = condition; }

    public ProductConditionGrade getConditionGrade() { return conditionGrade; }
    public void setConditionGrade(ProductConditionGrade conditionGrade) { this.conditionGrade = conditionGrade; }

    public int getStockQuantity() { return stockQuantity; }
    public void setStockQuantity(int stockQuantity) { this.stockQuantity = stockQuantity; }

    public ProductStatus getStatus() { return status; }
    public void setStatus(ProductStatus status) { this.status = status; }

    public Map<String, Object> getAttributes() { return attributes; }
    public void setAttributes(Map<String, Object> attributes) { this.attributes = attributes; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public long getViewCount() { return viewCount; }
    public void setViewCount(long viewCount) { this.viewCount = viewCount; }
}
