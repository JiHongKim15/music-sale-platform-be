package com.music.sale.adapter.persistence.shop;

@jakarta.persistence.Entity()
@jakarta.persistence.Table(name = "shops")
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0016\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0017\u0018\u0000 %2\u00020\u0001:\u0001%BK\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0005\u0012\u0006\u0010\t\u001a\u00020\u0005\u0012\b\b\u0002\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u00a2\u0006\u0002\u0010\u000eJ\b\u0010#\u001a\u00020$H\u0016R\u001e\u0010\u0007\u001a\u00020\u00058\u0016@\u0016X\u0097\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001e\u0010\t\u001a\u00020\u00058\u0016@\u0016X\u0097\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0010\"\u0004\b\u0014\u0010\u0012R\u001e\u0010\u0006\u001a\u00020\u00058\u0016@\u0016X\u0097\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0010\"\u0004\b\u0016\u0010\u0012R\u001a\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0016X\u0097\u0004\u00a2\u0006\n\n\u0002\u0010\u0019\u001a\u0004\b\u0017\u0010\u0018R\u001e\u0010\n\u001a\u00020\u000b8\u0016@\u0016X\u0097\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u001a\"\u0004\b\u001b\u0010\u001cR\u001e\u0010\u0004\u001a\u00020\u00058\u0016@\u0016X\u0097\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u0010\"\u0004\b\u001e\u0010\u0012R\u001e\u0010\b\u001a\u00020\u00058\u0016@\u0016X\u0097\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010\u0010\"\u0004\b \u0010\u0012R\u0016\u0010\f\u001a\u00020\r8\u0016X\u0097\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b!\u0010\"\u00a8\u0006&"}, d2 = {"Lcom/music/sale/adapter/persistence/shop/ShopEntity;", "Lcom/music/sale/adapter/persistence/common/BaseEntity;", "id", "", "name", "", "description", "address", "phoneNumber", "businessHours", "isShippingAvailable", "", "seller", "Lcom/music/sale/adapter/persistence/user/entity/UserEntity;", "(Ljava/lang/Long;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZLcom/music/sale/adapter/persistence/user/entity/UserEntity;)V", "getAddress", "()Ljava/lang/String;", "setAddress", "(Ljava/lang/String;)V", "getBusinessHours", "setBusinessHours", "getDescription", "setDescription", "getId", "()Ljava/lang/Long;", "Ljava/lang/Long;", "()Z", "setShippingAvailable", "(Z)V", "getName", "setName", "getPhoneNumber", "setPhoneNumber", "getSeller", "()Lcom/music/sale/adapter/persistence/user/entity/UserEntity;", "toDomain", "Lcom/music/sale/domain/shop/Shop;", "Companion", "music"})
public class ShopEntity extends com.music.sale.adapter.persistence.common.BaseEntity {
    @jakarta.persistence.Id()
    @jakarta.persistence.GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    @org.jetbrains.annotations.Nullable()
    private final java.lang.Long id = null;
    @jakarta.persistence.Column(nullable = false)
    @org.jetbrains.annotations.NotNull()
    private java.lang.String name;
    @jakarta.persistence.Column(nullable = false)
    @org.jetbrains.annotations.NotNull()
    private java.lang.String description;
    @jakarta.persistence.Column(nullable = false)
    @org.jetbrains.annotations.NotNull()
    private java.lang.String address;
    @jakarta.persistence.Column(name = "phone_number", nullable = false)
    @org.jetbrains.annotations.NotNull()
    private java.lang.String phoneNumber;
    @jakarta.persistence.Column(name = "business_hours")
    @org.jetbrains.annotations.NotNull()
    private java.lang.String businessHours;
    @jakarta.persistence.Column(name = "shipping_available")
    private boolean isShippingAvailable;
    @jakarta.persistence.ManyToOne(fetch = jakarta.persistence.FetchType.LAZY)
    @jakarta.persistence.JoinColumn(name = "seller_id")
    @org.jetbrains.annotations.NotNull()
    private final com.music.sale.adapter.persistence.user.entity.UserEntity seller = null;
    @org.jetbrains.annotations.NotNull()
    public static final com.music.sale.adapter.persistence.shop.ShopEntity.Companion Companion = null;
    
    public ShopEntity(@org.jetbrains.annotations.Nullable()
    java.lang.Long id, @org.jetbrains.annotations.NotNull()
    java.lang.String name, @org.jetbrains.annotations.NotNull()
    java.lang.String description, @org.jetbrains.annotations.NotNull()
    java.lang.String address, @org.jetbrains.annotations.NotNull()
    java.lang.String phoneNumber, @org.jetbrains.annotations.NotNull()
    java.lang.String businessHours, boolean isShippingAvailable, @org.jetbrains.annotations.NotNull()
    com.music.sale.adapter.persistence.user.entity.UserEntity seller) {
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
    
    public void setName(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public java.lang.String getDescription() {
        return null;
    }
    
    public void setDescription(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public java.lang.String getAddress() {
        return null;
    }
    
    public void setAddress(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public java.lang.String getPhoneNumber() {
        return null;
    }
    
    public void setPhoneNumber(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public java.lang.String getBusinessHours() {
        return null;
    }
    
    public void setBusinessHours(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    public boolean isShippingAvailable() {
        return false;
    }
    
    public void setShippingAvailable(boolean p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public com.music.sale.adapter.persistence.user.entity.UserEntity getSeller() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public com.music.sale.domain.shop.Shop toDomain() {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0016\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b\u00a8\u0006\t"}, d2 = {"Lcom/music/sale/adapter/persistence/shop/ShopEntity$Companion;", "", "()V", "fromDomain", "Lcom/music/sale/adapter/persistence/shop/ShopEntity;", "shop", "Lcom/music/sale/domain/shop/Shop;", "sellerEntity", "Lcom/music/sale/adapter/persistence/user/entity/UserEntity;", "music"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.music.sale.adapter.persistence.shop.ShopEntity fromDomain(@org.jetbrains.annotations.NotNull()
        com.music.sale.domain.shop.Shop shop, @org.jetbrains.annotations.NotNull()
        com.music.sale.adapter.persistence.user.entity.UserEntity sellerEntity) {
            return null;
        }
    }
}