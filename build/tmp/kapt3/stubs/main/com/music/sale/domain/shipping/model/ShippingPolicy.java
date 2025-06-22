package com.music.sale.domain.shipping.model;

/**
 * 배송 정책 도메인 모델 여러 상품이 공유할 수 있는 배송 정책을 표현
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u001c\u0018\u0000 +2\u00020\u0001:\u0004+,-.B_\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\u0003\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\u0006\u0010\u000f\u001a\u00020\u000e\u0012\u0006\u0010\u0010\u001a\u00020\u000e\u0012\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012\u00a2\u0006\u0002\u0010\u0014J\u000e\u0010'\u001a\u00020\t2\u0006\u0010(\u001a\u00020\tJ\u000e\u0010)\u001a\u00020\u000e2\u0006\u0010*\u001a\u00020\u0013R\u0011\u0010\u000f\u001a\u00020\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\n\u001a\u00020\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0013\u0010\b\u001a\u0004\u0018\u00010\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0015\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\n\n\u0002\u0010\u001d\u001a\u0004\b\u001b\u0010\u001cR\u0011\u0010\u0010\u001a\u00020\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0016R\u0011\u0010\r\u001a\u00020\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u0016R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u0017\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012\u00a2\u0006\b\n\u0000\u001a\u0004\b!\u0010\"R\u0011\u0010\f\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b#\u0010$R\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b%\u0010&\u00a8\u0006/"}, d2 = {"Lcom/music/sale/domain/shipping/model/ShippingPolicy;", "", "id", "", "name", "Lcom/music/sale/domain/shipping/model/ShippingPolicy$PolicyName;", "shippingFee", "Lcom/music/sale/domain/shipping/model/ShippingPolicy$Fee;", "freeShippingThreshold", "Ljava/math/BigDecimal;", "deliveryDays", "Lcom/music/sale/domain/shipping/model/ShippingPolicy$DeliveryDays;", "sellerId", "isDefault", "", "canPickup", "internationalShipping", "restrictedAreas", "", "", "(Ljava/lang/Long;Lcom/music/sale/domain/shipping/model/ShippingPolicy$PolicyName;Lcom/music/sale/domain/shipping/model/ShippingPolicy$Fee;Ljava/math/BigDecimal;Lcom/music/sale/domain/shipping/model/ShippingPolicy$DeliveryDays;JZZZLjava/util/List;)V", "getCanPickup", "()Z", "getDeliveryDays", "()Lcom/music/sale/domain/shipping/model/ShippingPolicy$DeliveryDays;", "getFreeShippingThreshold", "()Ljava/math/BigDecimal;", "getId", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getInternationalShipping", "getName", "()Lcom/music/sale/domain/shipping/model/ShippingPolicy$PolicyName;", "getRestrictedAreas", "()Ljava/util/List;", "getSellerId", "()J", "getShippingFee", "()Lcom/music/sale/domain/shipping/model/ShippingPolicy$Fee;", "calculateShippingFee", "orderAmount", "canShipTo", "area", "Companion", "DeliveryDays", "Fee", "PolicyName", "music"})
public final class ShippingPolicy {
    @org.jetbrains.annotations.Nullable()
    private final java.lang.Long id = null;
    @org.jetbrains.annotations.NotNull()
    private final com.music.sale.domain.shipping.model.ShippingPolicy.PolicyName name = null;
    @org.jetbrains.annotations.NotNull()
    private final com.music.sale.domain.shipping.model.ShippingPolicy.Fee shippingFee = null;
    @org.jetbrains.annotations.Nullable()
    private final java.math.BigDecimal freeShippingThreshold = null;
    @org.jetbrains.annotations.NotNull()
    private final com.music.sale.domain.shipping.model.ShippingPolicy.DeliveryDays deliveryDays = null;
    private final long sellerId = 0L;
    private final boolean isDefault = false;
    private final boolean canPickup = false;
    private final boolean internationalShipping = false;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<java.lang.String> restrictedAreas = null;
    @org.jetbrains.annotations.NotNull()
    public static final com.music.sale.domain.shipping.model.ShippingPolicy.Companion Companion = null;
    
    public ShippingPolicy(@org.jetbrains.annotations.Nullable()
    java.lang.Long id, @org.jetbrains.annotations.NotNull()
    com.music.sale.domain.shipping.model.ShippingPolicy.PolicyName name, @org.jetbrains.annotations.NotNull()
    com.music.sale.domain.shipping.model.ShippingPolicy.Fee shippingFee, @org.jetbrains.annotations.Nullable()
    java.math.BigDecimal freeShippingThreshold, @org.jetbrains.annotations.NotNull()
    com.music.sale.domain.shipping.model.ShippingPolicy.DeliveryDays deliveryDays, long sellerId, boolean isDefault, boolean canPickup, boolean internationalShipping, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> restrictedAreas) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Long getId() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.music.sale.domain.shipping.model.ShippingPolicy.PolicyName getName() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.music.sale.domain.shipping.model.ShippingPolicy.Fee getShippingFee() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.math.BigDecimal getFreeShippingThreshold() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.music.sale.domain.shipping.model.ShippingPolicy.DeliveryDays getDeliveryDays() {
        return null;
    }
    
    public final long getSellerId() {
        return 0L;
    }
    
    public final boolean isDefault() {
        return false;
    }
    
    public final boolean getCanPickup() {
        return false;
    }
    
    public final boolean getInternationalShipping() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.lang.String> getRestrictedAreas() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.math.BigDecimal calculateShippingFee(@org.jetbrains.annotations.NotNull()
    java.math.BigDecimal orderAmount) {
        return null;
    }
    
    public final boolean canShipTo(@org.jetbrains.annotations.NotNull()
    java.lang.String area) {
        return false;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002Jf\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\b2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u00102\b\b\u0002\u0010\u0012\u001a\u00020\u00102\u000e\b\u0002\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00060\u0014Jf\u0010\u0015\u001a\u00020\u00042\u0006\u0010\u0016\u001a\u00020\u000e2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\b2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\u00102\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00060\u0014\u00a8\u0006\u0017"}, d2 = {"Lcom/music/sale/domain/shipping/model/ShippingPolicy$Companion;", "", "()V", "create", "Lcom/music/sale/domain/shipping/model/ShippingPolicy;", "name", "", "shippingFee", "Ljava/math/BigDecimal;", "freeShippingThreshold", "minDeliveryDays", "", "maxDeliveryDays", "sellerId", "", "isDefault", "", "canPickup", "internationalShipping", "restrictedAreas", "", "reconstitute", "id", "music"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.music.sale.domain.shipping.model.ShippingPolicy create(@org.jetbrains.annotations.NotNull()
        java.lang.String name, @org.jetbrains.annotations.NotNull()
        java.math.BigDecimal shippingFee, @org.jetbrains.annotations.Nullable()
        java.math.BigDecimal freeShippingThreshold, int minDeliveryDays, int maxDeliveryDays, long sellerId, boolean isDefault, boolean canPickup, boolean internationalShipping, @org.jetbrains.annotations.NotNull()
        java.util.List<java.lang.String> restrictedAreas) {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.music.sale.domain.shipping.model.ShippingPolicy reconstitute(long id, @org.jetbrains.annotations.NotNull()
        java.lang.String name, @org.jetbrains.annotations.NotNull()
        java.math.BigDecimal shippingFee, @org.jetbrains.annotations.Nullable()
        java.math.BigDecimal freeShippingThreshold, int minDeliveryDays, int maxDeliveryDays, long sellerId, boolean isDefault, boolean canPickup, boolean internationalShipping, @org.jetbrains.annotations.NotNull()
        java.util.List<java.lang.String> restrictedAreas) {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0005J\t\u0010\t\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\n\u001a\u00020\u0003H\u00c6\u0003J\u001d\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u000f\u001a\u00020\u0003H\u00d6\u0001J\b\u0010\u0010\u001a\u00020\u0011H\u0016R\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007\u00a8\u0006\u0012"}, d2 = {"Lcom/music/sale/domain/shipping/model/ShippingPolicy$DeliveryDays;", "", "min", "", "max", "(II)V", "getMax", "()I", "getMin", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "", "music"})
    public static final class DeliveryDays {
        private final int min = 0;
        private final int max = 0;
        
        public DeliveryDays(int min, int max) {
            super();
        }
        
        public final int getMin() {
            return 0;
        }
        
        public final int getMax() {
            return 0;
        }
        
        @java.lang.Override()
        @org.jetbrains.annotations.NotNull()
        public java.lang.String toString() {
            return null;
        }
        
        public final int component1() {
            return 0;
        }
        
        public final int component2() {
            return 0;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.music.sale.domain.shipping.model.ShippingPolicy.DeliveryDays copy(int min, int max) {
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
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\f\u001a\u00020\rH\u00d6\u0001J\u0006\u0010\u000e\u001a\u00020\nJ\t\u0010\u000f\u001a\u00020\u0010H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0011"}, d2 = {"Lcom/music/sale/domain/shipping/model/ShippingPolicy$Fee;", "", "value", "Ljava/math/BigDecimal;", "(Ljava/math/BigDecimal;)V", "getValue", "()Ljava/math/BigDecimal;", "component1", "copy", "equals", "", "other", "hashCode", "", "isFree", "toString", "", "music"})
    public static final class Fee {
        @org.jetbrains.annotations.NotNull()
        private final java.math.BigDecimal value = null;
        
        public Fee(@org.jetbrains.annotations.NotNull()
        java.math.BigDecimal value) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.math.BigDecimal getValue() {
            return null;
        }
        
        public final boolean isFree() {
            return false;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.math.BigDecimal component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.music.sale.domain.shipping.model.ShippingPolicy.Fee copy(@org.jetbrains.annotations.NotNull()
        java.math.BigDecimal value) {
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
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\f\u001a\u00020\rH\u00d6\u0001J\t\u0010\u000e\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u000f"}, d2 = {"Lcom/music/sale/domain/shipping/model/ShippingPolicy$PolicyName;", "", "value", "", "(Ljava/lang/String;)V", "getValue", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "music"})
    public static final class PolicyName {
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String value = null;
        
        public PolicyName(@org.jetbrains.annotations.NotNull()
        java.lang.String value) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getValue() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.music.sale.domain.shipping.model.ShippingPolicy.PolicyName copy(@org.jetbrains.annotations.NotNull()
        java.lang.String value) {
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
}