package com.music.sale.domain.product;

/**
 * 통합된 제품 도메인 모델
 * 제품의 모든 속성과 비즈니스 로직을 포함
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b%\n\u0002\u0010\u000b\n\u0002\b\u0006\b\u0086\b\u0018\u00002\u00020\u0001B\u009f\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0016\b\u0002\u0010\t\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0001\u0018\u00010\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u000e\u0012\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010\u0012\u0006\u0010\u0011\u001a\u00020\u0012\u0012\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014\u0012\u0006\u0010\u0015\u001a\u00020\f\u0012\u0006\u0010\u0016\u001a\u00020\u0017\u0012\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u0006\u0012\u0016\b\u0002\u0010\u0019\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0001\u0018\u00010\n\u00a2\u0006\u0002\u0010\u001aJ\u0014\u0010\t\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0001\u0018\u00010\nJ\t\u0010-\u001a\u00020\u0003H\u00c6\u0003J\u000b\u0010.\u001a\u0004\u0018\u00010\u0014H\u00c6\u0003J\t\u0010/\u001a\u00020\fH\u00c6\u0003J\t\u00100\u001a\u00020\u0017H\u00c6\u0003J\u000b\u00101\u001a\u0004\u0018\u00010\u0006H\u00c2\u0003J\u0017\u00102\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0001\u0018\u00010\nH\u00c2\u0003J\t\u00103\u001a\u00020\u0003H\u00c6\u0003J\t\u00104\u001a\u00020\u0006H\u00c2\u0003J\t\u00105\u001a\u00020\bH\u00c6\u0003J\u0017\u00106\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0001\u0018\u00010\nH\u00c2\u0003J\t\u00107\u001a\u00020\fH\u00c6\u0003J\u000b\u00108\u001a\u0004\u0018\u00010\u000eH\u00c6\u0003J\u000b\u00109\u001a\u0004\u0018\u00010\u0010H\u00c6\u0003J\t\u0010:\u001a\u00020\u0012H\u00c6\u0003J\u00b9\u0001\u0010;\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\u0016\b\u0002\u0010\t\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0001\u0018\u00010\n2\b\b\u0002\u0010\u000b\u001a\u00020\f2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000e2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u00122\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00142\b\b\u0002\u0010\u0015\u001a\u00020\f2\b\b\u0002\u0010\u0016\u001a\u00020\u00172\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u00062\u0016\b\u0002\u0010\u0019\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0001\u0018\u00010\nH\u00c6\u0001J\u0013\u0010<\u001a\u00020=2\b\u0010>\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010?\u001a\u00020\fH\u00d6\u0001J\u0006\u0010@\u001a\u00020=J\u0006\u0010A\u001a\u00020=J\u0006\u0010\u0005\u001a\u00020\u0006J\t\u0010B\u001a\u00020\u0006H\u00d6\u0001R\u001c\u0010\t\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0001\u0018\u00010\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0011\u0010\u0007\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0011\u0010\u0011\u001a\u00020\u0012\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u0013\u0010\u0013\u001a\u0004\u0018\u00010\u0014\u00a2\u0006\b\n\u0000\u001a\u0004\b!\u0010\"R\u001c\u0010\u0019\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0001\u0018\u00010\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0018\u001a\u0004\u0018\u00010\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b#\u0010\u001cR\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0011\u0010\u000b\u001a\u00020\f\u00a2\u0006\b\n\u0000\u001a\u0004\b$\u0010%R\u0013\u0010\r\u001a\u0004\u0018\u00010\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b&\u0010'R\u0011\u0010\u0016\u001a\u00020\u0017\u00a2\u0006\b\n\u0000\u001a\u0004\b(\u0010)R\u0011\u0010\u0015\u001a\u00020\f\u00a2\u0006\b\n\u0000\u001a\u0004\b*\u0010%R\u0013\u0010\u000f\u001a\u0004\u0018\u00010\u0010\u00a2\u0006\b\n\u0000\u001a\u0004\b+\u0010,\u00a8\u0006C"}, d2 = {"Lcom/music/sale/domain/product/Product;", "", "id", "", "catalogId", "name", "", "category", "Lcom/music/sale/domain/category/Category;", "attributes", "", "price", "", "seller", "Lcom/music/sale/domain/user/User;", "store", "Lcom/music/sale/domain/store/model/Store;", "condition", "Lcom/music/sale/domain/product/enum/ProductCondition;", "conditionGrade", "Lcom/music/sale/domain/product/enum/ProductConditionGrade;", "stockQuantity", "status", "Lcom/music/sale/domain/product/enum/ProductStatus;", "customName", "customAttributes", "(JJLjava/lang/String;Lcom/music/sale/domain/category/Category;Ljava/util/Map;ILcom/music/sale/domain/user/User;Lcom/music/sale/domain/store/model/Store;Lcom/music/sale/domain/product/enum/ProductCondition;Lcom/music/sale/domain/product/enum/ProductConditionGrade;ILcom/music/sale/domain/product/enum/ProductStatus;Ljava/lang/String;Ljava/util/Map;)V", "getCatalogId", "()J", "getCategory", "()Lcom/music/sale/domain/category/Category;", "getCondition", "()Lcom/music/sale/domain/product/enum/ProductCondition;", "getConditionGrade", "()Lcom/music/sale/domain/product/enum/ProductConditionGrade;", "getId", "getPrice", "()I", "getSeller", "()Lcom/music/sale/domain/user/User;", "getStatus", "()Lcom/music/sale/domain/product/enum/ProductStatus;", "getStockQuantity", "getStore", "()Lcom/music/sale/domain/store/model/Store;", "component1", "component10", "component11", "component12", "component13", "component14", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "", "other", "hashCode", "isCustomAttributes", "isCustomName", "toString", "music"})
public final class Product {
    private final long id = 0L;
    private final long catalogId = 0L;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String name = null;
    @org.jetbrains.annotations.NotNull()
    private final com.music.sale.domain.category.Category category = null;
    @org.jetbrains.annotations.Nullable()
    private final java.util.Map<java.lang.String, java.lang.Object> attributes = null;
    private final int price = 0;
    @org.jetbrains.annotations.Nullable()
    private final com.music.sale.domain.user.User seller = null;
    @org.jetbrains.annotations.Nullable()
    private final com.music.sale.domain.store.model.Store store = null;
    private final int stockQuantity = 0;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String customName = null;
    @org.jetbrains.annotations.Nullable()
    private final java.util.Map<java.lang.String, java.lang.Object> customAttributes = null;
    
    public final long getId() {
        return 0L;
    }
    
    public final long getCatalogId() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.music.sale.domain.category.Category getCategory() {
        return null;
    }
    
    public final int getPrice() {
        return 0;
    }
    
    @org.jetbrains.annotations.Nullable()
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
    
    @org.jetbrains.annotations.Nullable()
    public final java.util.Map<java.lang.String, java.lang.Object> attributes() {
        return null;
    }
    
    public final boolean isCustomName() {
        return false;
    }
    
    public final boolean isCustomAttributes() {
        return false;
    }
    
    public final long component1() {
        return 0L;
    }
    
    public final int component11() {
        return 0;
    }
    
    private final java.lang.String component13() {
        return null;
    }
    
    private final java.util.Map<java.lang.String, java.lang.Object> component14() {
        return null;
    }
    
    public final long component2() {
        return 0L;
    }
    
    private final java.lang.String component3() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.music.sale.domain.category.Category component4() {
        return null;
    }
    
    private final java.util.Map<java.lang.String, java.lang.Object> component5() {
        return null;
    }
    
    public final int component6() {
        return 0;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.music.sale.domain.user.User component7() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.music.sale.domain.store.model.Store component8() {
        return null;
    }
    
    @java.lang.Override()
    public boolean equals(@org.jetbrains.annotations.Nullable()
    java.lang.Object other) {
        return false;
    }
    
    @java.lang.Override()
    public int hashCode() {
        return 0;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public java.lang.String toString() {
        return null;
    }
}