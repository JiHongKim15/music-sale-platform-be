package com.music.sale.adapter.persistence.product.entity;

/**
 * 제품 카탈로그 JPA 엔티티 제품의 기본 정보와 특성을 저장
 */
@jakarta.persistence.Entity()
@jakarta.persistence.Table(name = "product_catalog")
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u0000\n\u0002\b\u000b\b\u0017\u0018\u00002\u00020\u0001B9\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0016\b\u0002\u0010\b\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\n\u0018\u00010\t\u00a2\u0006\u0002\u0010\u000bR$\u0010\b\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\n\u0018\u00010\t8\u0016X\u0097\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0016\u0010\u0006\u001a\u00020\u00078\u0016X\u0097\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0016X\u0097\u0004\u00a2\u0006\n\n\u0002\u0010\u0012\u001a\u0004\b\u0010\u0010\u0011R\u0016\u0010\u0004\u001a\u00020\u00058\u0016X\u0097\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014\u00a8\u0006\u0015"}, d2 = {"Lcom/music/sale/adapter/persistence/product/entity/ProductCatalogEntity;", "Lcom/music/sale/adapter/persistence/common/BaseEntity;", "id", "", "name", "", "category", "Lcom/music/sale/adapter/persistence/category/entity/CategoryEntity;", "attributes", "", "", "(Ljava/lang/Long;Ljava/lang/String;Lcom/music/sale/adapter/persistence/category/entity/CategoryEntity;Ljava/util/Map;)V", "getAttributes", "()Ljava/util/Map;", "getCategory", "()Lcom/music/sale/adapter/persistence/category/entity/CategoryEntity;", "getId", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getName", "()Ljava/lang/String;", "music"})
public class ProductCatalogEntity extends com.music.sale.adapter.persistence.common.BaseEntity {
    @jakarta.persistence.Id()
    @jakarta.persistence.GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    @org.jetbrains.annotations.Nullable()
    private final java.lang.Long id = null;
    @jakarta.persistence.Column(nullable = false)
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String name = null;
    @jakarta.persistence.ManyToOne(fetch = jakarta.persistence.FetchType.LAZY)
    @jakarta.persistence.JoinColumn(name = "category_id", nullable = false)
    @org.jetbrains.annotations.NotNull()
    private final com.music.sale.adapter.persistence.category.entity.CategoryEntity category = null;
    @jakarta.persistence.Column(name = "attributes", nullable = false, columnDefinition = "JSON")
    @jakarta.persistence.Convert(converter = com.music.sale.adapter.persistence.common.JsonConverter.class)
    @org.jetbrains.annotations.Nullable()
    private final java.util.Map<java.lang.String, java.lang.Object> attributes = null;
    
    public ProductCatalogEntity(@org.jetbrains.annotations.Nullable()
    java.lang.Long id, @org.jetbrains.annotations.NotNull()
    java.lang.String name, @org.jetbrains.annotations.NotNull()
    com.music.sale.adapter.persistence.category.entity.CategoryEntity category, @org.jetbrains.annotations.Nullable()
    java.util.Map<java.lang.String, ? extends java.lang.Object> attributes) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public java.lang.Long getId() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public java.lang.String getName() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public com.music.sale.adapter.persistence.category.entity.CategoryEntity getCategory() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public java.util.Map<java.lang.String, java.lang.Object> getAttributes() {
        return null;
    }
}