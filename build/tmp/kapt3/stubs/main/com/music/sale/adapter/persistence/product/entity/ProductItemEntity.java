package com.music.sale.adapter.persistence.product.entity;

/**
 * 제품 아이템 JPA 엔티티 실제 판매되는 상품 인스턴스를 표현
 */
@jakarta.persistence.Entity()
@jakarta.persistence.Table(name = "product_item")
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010$\n\u0002\u0010\u0000\n\u0002\b\u0018\b\u0017\u0018\u00002\u00020\u0001B\u0081\u0001\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000f\u0012\u0006\u0010\u0010\u001a\u00020\u000b\u0012\u0006\u0010\u0011\u001a\u00020\u0012\u0012\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0014\u0012\u0016\b\u0002\u0010\u0015\u001a\u0010\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u0017\u0018\u00010\u0016\u00a2\u0006\u0002\u0010\u0018R\u0016\u0010\u0004\u001a\u00020\u00058\u0016X\u0097\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0016\u0010\f\u001a\u00020\r8\u0016X\u0097\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0018\u0010\u000e\u001a\u0004\u0018\u00010\u000f8\u0016X\u0097\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR$\u0010\u0015\u001a\u0010\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u0017\u0018\u00010\u00168\u0016X\u0097\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u0018\u0010\u0013\u001a\u0004\u0018\u00010\u00148\u0016X\u0097\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b!\u0010\"R\u001a\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0016X\u0097\u0004\u00a2\u0006\n\n\u0002\u0010%\u001a\u0004\b#\u0010$R\u0016\u0010\n\u001a\u00020\u000b8\u0016X\u0097\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b&\u0010'R\u0018\u0010\u0006\u001a\u0004\u0018\u00010\u00078\u0016X\u0097\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b(\u0010)R\u0016\u0010\u0011\u001a\u00020\u00128\u0016X\u0097\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b*\u0010+R\u0016\u0010\u0010\u001a\u00020\u000b8\u0016X\u0097\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b,\u0010'R\u0018\u0010\b\u001a\u0004\u0018\u00010\t8\u0016X\u0097\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b-\u0010.\u00a8\u0006/"}, d2 = {"Lcom/music/sale/adapter/persistence/product/entity/ProductItemEntity;", "Lcom/music/sale/adapter/persistence/common/BaseEntity;", "id", "", "catalog", "Lcom/music/sale/adapter/persistence/product/entity/ProductCatalogEntity;", "seller", "Lcom/music/sale/adapter/persistence/user/entity/UserEntity;", "store", "Lcom/music/sale/adapter/persistence/store/entity/StoreEntity;", "price", "", "condition", "Lcom/music/sale/domain/product/enum/ProductCondition;", "conditionGrade", "Lcom/music/sale/domain/product/enum/ProductConditionGrade;", "stockQuantity", "status", "Lcom/music/sale/domain/product/enum/ProductStatus;", "customName", "", "customAttributes", "", "", "(Ljava/lang/Long;Lcom/music/sale/adapter/persistence/product/entity/ProductCatalogEntity;Lcom/music/sale/adapter/persistence/user/entity/UserEntity;Lcom/music/sale/adapter/persistence/store/entity/StoreEntity;ILcom/music/sale/domain/product/enum/ProductCondition;Lcom/music/sale/domain/product/enum/ProductConditionGrade;ILcom/music/sale/domain/product/enum/ProductStatus;Ljava/lang/String;Ljava/util/Map;)V", "getCatalog", "()Lcom/music/sale/adapter/persistence/product/entity/ProductCatalogEntity;", "getCondition", "()Lcom/music/sale/domain/product/enum/ProductCondition;", "getConditionGrade", "()Lcom/music/sale/domain/product/enum/ProductConditionGrade;", "getCustomAttributes", "()Ljava/util/Map;", "getCustomName", "()Ljava/lang/String;", "getId", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getPrice", "()I", "getSeller", "()Lcom/music/sale/adapter/persistence/user/entity/UserEntity;", "getStatus", "()Lcom/music/sale/domain/product/enum/ProductStatus;", "getStockQuantity", "getStore", "()Lcom/music/sale/adapter/persistence/store/entity/StoreEntity;", "music"})
public class ProductItemEntity extends com.music.sale.adapter.persistence.common.BaseEntity {
    @jakarta.persistence.Id()
    @jakarta.persistence.GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    @org.jetbrains.annotations.Nullable()
    private final java.lang.Long id = null;
    @jakarta.persistence.ManyToOne(fetch = jakarta.persistence.FetchType.LAZY)
    @jakarta.persistence.JoinColumn(name = "catalog_id", nullable = false)
    @org.jetbrains.annotations.NotNull()
    private final com.music.sale.adapter.persistence.product.entity.ProductCatalogEntity catalog = null;
    @jakarta.persistence.ManyToOne(fetch = jakarta.persistence.FetchType.LAZY)
    @jakarta.persistence.JoinColumn(name = "seller_id")
    @org.jetbrains.annotations.Nullable()
    private final com.music.sale.adapter.persistence.user.entity.UserEntity seller = null;
    @jakarta.persistence.ManyToOne(fetch = jakarta.persistence.FetchType.LAZY)
    @jakarta.persistence.JoinColumn(name = "store_id")
    @org.jetbrains.annotations.Nullable()
    private final com.music.sale.adapter.persistence.store.entity.StoreEntity store = null;
    @jakarta.persistence.Column(nullable = false)
    private final int price = 0;
    @jakarta.persistence.Column(name = "stock_quantity", nullable = false)
    private final int stockQuantity = 0;
    @jakarta.persistence.Column(name = "custom_name")
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String customName = null;
    @jakarta.persistence.Column(name = "custom_attributes", columnDefinition = "text")
    @jakarta.persistence.Convert(converter = com.music.sale.adapter.persistence.common.JsonConverter.class)
    @org.jetbrains.annotations.Nullable()
    private final java.util.Map<java.lang.String, java.lang.Object> customAttributes = null;
    
    @org.jetbrains.annotations.Nullable()
    public java.lang.Long getId() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public com.music.sale.adapter.persistence.product.entity.ProductCatalogEntity getCatalog() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public com.music.sale.adapter.persistence.user.entity.UserEntity getSeller() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public com.music.sale.adapter.persistence.store.entity.StoreEntity getStore() {
        return null;
    }
    
    public int getPrice() {
        return 0;
    }
    
    public int getStockQuantity() {
        return 0;
    }
    
    @org.jetbrains.annotations.Nullable()
    public java.lang.String getCustomName() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public java.util.Map<java.lang.String, java.lang.Object> getCustomAttributes() {
        return null;
    }
}