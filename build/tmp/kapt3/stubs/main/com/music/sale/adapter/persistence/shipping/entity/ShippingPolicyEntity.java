package com.music.sale.adapter.persistence.shipping.entity;

/**
 * 배송 정책 JPA 엔티티 여러 상품이 공유할 수 있는 배송 정책 정보를 관리
 */
@jakarta.persistence.Entity()
@jakarta.persistence.Table(name = "shipping_policies")
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0016\b\u0017\u0018\u00002\u00020\u0001Bm\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0007\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\n\u0012\u0006\u0010\f\u001a\u00020\u0003\u0012\b\b\u0002\u0010\r\u001a\u00020\u000e\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u000e\u0012\b\b\u0002\u0010\u0010\u001a\u00020\u000e\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\u0002\u0010\u0012R\u0016\u0010\u000f\u001a\u00020\u000e8\u0016X\u0097\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0018\u0010\b\u001a\u0004\u0018\u00010\u00078\u0016X\u0097\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u001a\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0016X\u0097\u0004\u00a2\u0006\n\n\u0002\u0010\u0019\u001a\u0004\b\u0017\u0010\u0018R\u0016\u0010\u0010\u001a\u00020\u000e8\u0016X\u0097\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0014R\u0016\u0010\r\u001a\u00020\u000e8\u0016X\u0097\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u0014R\u0016\u0010\u000b\u001a\u00020\n8\u0016X\u0097\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0016\u0010\t\u001a\u00020\n8\u0016X\u0097\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001cR\u0016\u0010\u0004\u001a\u00020\u00058\u0016X\u0097\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u0018\u0010\u0011\u001a\u0004\u0018\u00010\u00058\u0016X\u0097\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b \u0010\u001fR\u0016\u0010\f\u001a\u00020\u00038\u0016X\u0097\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b!\u0010\"R\u0016\u0010\u0006\u001a\u00020\u00078\u0016X\u0097\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b#\u0010\u0016\u00a8\u0006$"}, d2 = {"Lcom/music/sale/adapter/persistence/shipping/entity/ShippingPolicyEntity;", "Lcom/music/sale/adapter/persistence/common/BaseEntity;", "id", "", "name", "", "shippingFee", "Ljava/math/BigDecimal;", "freeShippingThreshold", "minDeliveryDays", "", "maxDeliveryDays", "sellerId", "isDefault", "", "canPickup", "internationalShipping", "restrictedAreas", "(Ljava/lang/Long;Ljava/lang/String;Ljava/math/BigDecimal;Ljava/math/BigDecimal;IIJZZZLjava/lang/String;)V", "getCanPickup", "()Z", "getFreeShippingThreshold", "()Ljava/math/BigDecimal;", "getId", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getInternationalShipping", "getMaxDeliveryDays", "()I", "getMinDeliveryDays", "getName", "()Ljava/lang/String;", "getRestrictedAreas", "getSellerId", "()J", "getShippingFee", "music"})
public class ShippingPolicyEntity extends com.music.sale.adapter.persistence.common.BaseEntity {
    @jakarta.persistence.Id()
    @jakarta.persistence.GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    @org.jetbrains.annotations.Nullable()
    private final java.lang.Long id = null;
    @jakarta.persistence.Column(nullable = false)
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String name = null;
    @jakarta.persistence.Column(nullable = false, precision = 10, scale = 2)
    @org.jetbrains.annotations.NotNull()
    private final java.math.BigDecimal shippingFee = null;
    @jakarta.persistence.Column(nullable = true, precision = 10, scale = 2)
    @org.jetbrains.annotations.Nullable()
    private final java.math.BigDecimal freeShippingThreshold = null;
    @jakarta.persistence.Column(nullable = false)
    private final int minDeliveryDays = 0;
    @jakarta.persistence.Column(nullable = false)
    private final int maxDeliveryDays = 0;
    @jakarta.persistence.Column(nullable = false)
    private final long sellerId = 0L;
    @jakarta.persistence.Column(nullable = false)
    private final boolean isDefault = false;
    @jakarta.persistence.Column(nullable = false)
    private final boolean canPickup = false;
    @jakarta.persistence.Column(nullable = false)
    private final boolean internationalShipping = false;
    @jakarta.persistence.Column(nullable = true, length = 1000)
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String restrictedAreas = null;
    
    public ShippingPolicyEntity(@org.jetbrains.annotations.Nullable()
    java.lang.Long id, @org.jetbrains.annotations.NotNull()
    java.lang.String name, @org.jetbrains.annotations.NotNull()
    java.math.BigDecimal shippingFee, @org.jetbrains.annotations.Nullable()
    java.math.BigDecimal freeShippingThreshold, int minDeliveryDays, int maxDeliveryDays, long sellerId, boolean isDefault, boolean canPickup, boolean internationalShipping, @org.jetbrains.annotations.Nullable()
    java.lang.String restrictedAreas) {
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
    public java.math.BigDecimal getShippingFee() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public java.math.BigDecimal getFreeShippingThreshold() {
        return null;
    }
    
    public int getMinDeliveryDays() {
        return 0;
    }
    
    public int getMaxDeliveryDays() {
        return 0;
    }
    
    public long getSellerId() {
        return 0L;
    }
    
    public boolean isDefault() {
        return false;
    }
    
    public boolean getCanPickup() {
        return false;
    }
    
    public boolean getInternationalShipping() {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable()
    public java.lang.String getRestrictedAreas() {
        return null;
    }
}