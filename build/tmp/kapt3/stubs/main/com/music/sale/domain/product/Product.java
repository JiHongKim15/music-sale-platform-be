package com.music.sale.domain.product;

/**
 * 통합된 제품 도메인 모델
 * 제품의 모든 속성과 비즈니스 로직을 포함
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\b\u0016\u0018\u0000 +2\u00020\u0001:\u0001+B\u0091\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\b\u0010\f\u001a\u0004\u0018\u00010\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011\u0012\u0006\u0010\u0012\u001a\u00020\t\u0012\u0006\u0010\u0013\u001a\u00020\u0014\u0012\u0012\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0016\u0012\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u0005\u0012\u0016\b\u0002\u0010\u0018\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0016\u00a2\u0006\u0002\u0010\u0019J\u0012\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0016J\u0006\u0010\u0004\u001a\u00020\u0005R\u001a\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0016X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0011\u0010\u000e\u001a\u00020\u000f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0013\u0010\u0010\u001a\u0004\u0018\u00010\u0011\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u001c\u0010\u0018\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0016X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b \u0010!R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0011\u0010\b\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\"\u0010#R\u0011\u0010\n\u001a\u00020\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b$\u0010%R\u0011\u0010\u0013\u001a\u00020\u0014\u00a2\u0006\b\n\u0000\u001a\u0004\b&\u0010'R\u0011\u0010\u0012\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b(\u0010#R\u0013\u0010\f\u001a\u0004\u0018\u00010\r\u00a2\u0006\b\n\u0000\u001a\u0004\b)\u0010*\u00a8\u0006,"}, d2 = {"Lcom/music/sale/domain/product/Product;", "", "id", "", "name", "", "category", "Lcom/music/sale/domain/category/Category;", "price", "", "seller", "Lcom/music/sale/domain/user/User;", "store", "Lcom/music/sale/domain/store/model/Store;", "condition", "Lcom/music/sale/domain/product/enum/ProductCondition;", "conditionGrade", "Lcom/music/sale/domain/product/enum/ProductConditionGrade;", "stockQuantity", "status", "Lcom/music/sale/domain/product/enum/ProductStatus;", "attributes", "", "customName", "customAttributes", "(JLjava/lang/String;Lcom/music/sale/domain/category/Category;ILcom/music/sale/domain/user/User;Lcom/music/sale/domain/store/model/Store;Lcom/music/sale/domain/product/enum/ProductCondition;Lcom/music/sale/domain/product/enum/ProductConditionGrade;ILcom/music/sale/domain/product/enum/ProductStatus;Ljava/util/Map;Ljava/lang/String;Ljava/util/Map;)V", "getCategory", "()Lcom/music/sale/domain/category/Category;", "getCondition", "()Lcom/music/sale/domain/product/enum/ProductCondition;", "getConditionGrade", "()Lcom/music/sale/domain/product/enum/ProductConditionGrade;", "getId", "()J", "getPrice", "()I", "getSeller", "()Lcom/music/sale/domain/user/User;", "getStatus", "()Lcom/music/sale/domain/product/enum/ProductStatus;", "getStockQuantity", "getStore", "()Lcom/music/sale/domain/store/model/Store;", "Companion", "music"})
public final class Product {
    private final long id = 0L;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String name = null;
    @org.jetbrains.annotations.NotNull()
    private final com.music.sale.domain.category.Category category = null;
    private final int price = 0;
    @org.jetbrains.annotations.NotNull()
    private final com.music.sale.domain.user.User seller = null;
    @org.jetbrains.annotations.Nullable()
    private final com.music.sale.domain.store.model.Store store = null;
    private final int stockQuantity = 0;
    @org.jetbrains.annotations.NotNull()
    private final java.util.Map<java.lang.String, java.lang.Object> attributes = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String customName = null;
    @org.jetbrains.annotations.Nullable()
    private final java.util.Map<java.lang.String, java.lang.Object> customAttributes = null;
    @org.jetbrains.annotations.NotNull()
    public static final com.music.sale.domain.product.Product.Companion Companion = null;
    
    public final long getId() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.music.sale.domain.category.Category getCategory() {
        return null;
    }
    
    public final int getPrice() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.music.sale.domain.user.User getSeller() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.music.sale.domain.store.model.Store getStore() {
        return null;
    }
    
    public final int getStockQuantity() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String name() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.Map<java.lang.String, java.lang.Object> attributes() {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002Jk\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\f2\u0006\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u0012\u001a\u00020\n2\u0006\u0010\u0013\u001a\u00020\u00142\u0012\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00010\u0016\u00a2\u0006\u0002\u0010\u0017Js\u0010\u0018\u001a\u00020\u00042\u0006\u0010\u0019\u001a\u00020\f2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\f2\u0006\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u0012\u001a\u00020\n2\u0006\u0010\u0013\u001a\u00020\u00142\u0012\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00010\u0016\u00a2\u0006\u0002\u0010\u001a\u00a8\u0006\u001b"}, d2 = {"Lcom/music/sale/domain/product/Product$Companion;", "", "()V", "create", "Lcom/music/sale/domain/product/Product;", "name", "", "category", "Lcom/music/sale/domain/category/Category;", "price", "", "sellerId", "", "storeId", "condition", "Lcom/music/sale/domain/product/enum/ProductCondition;", "conditionGrade", "Lcom/music/sale/domain/product/enum/ProductConditionGrade;", "stockQuantity", "status", "Lcom/music/sale/domain/product/enum/ProductStatus;", "attributes", "", "(Ljava/lang/String;Lcom/music/sale/domain/category/Category;IJLjava/lang/Long;Lcom/music/sale/domain/product/enum/ProductCondition;Lcom/music/sale/domain/product/enum/ProductConditionGrade;ILcom/music/sale/domain/product/enum/ProductStatus;Ljava/util/Map;)Lcom/music/sale/domain/product/Product;", "update", "id", "(JLjava/lang/String;Lcom/music/sale/domain/category/Category;IJLjava/lang/Long;Lcom/music/sale/domain/product/enum/ProductCondition;Lcom/music/sale/domain/product/enum/ProductConditionGrade;ILcom/music/sale/domain/product/enum/ProductStatus;Ljava/util/Map;)Lcom/music/sale/domain/product/Product;", "music"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}