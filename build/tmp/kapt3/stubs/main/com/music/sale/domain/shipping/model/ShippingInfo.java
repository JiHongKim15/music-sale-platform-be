package com.music.sale.domain.shipping.model;

/**
 * 배송 정보 값 객체 (Value Object) 상품의 배송 관련 정보를 캡슐화하여 관리
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u001d\n\u0002\u0010\b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001:\u0002,-BK\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t\u0012\u000e\b\u0002\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b\u0012\b\b\u0002\u0010\r\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u000fJ\u000e\u0010\u001b\u001a\u00020\t2\u0006\u0010\u001c\u001a\u00020\tJ\u000e\u0010\u001d\u001a\u00020\u00032\u0006\u0010\u001e\u001a\u00020\fJ\t\u0010\u001f\u001a\u00020\u0003H\u00c6\u0003J\t\u0010 \u001a\u00020\u0005H\u00c6\u0003J\t\u0010!\u001a\u00020\u0007H\u00c6\u0003J\u000b\u0010\"\u001a\u0004\u0018\u00010\tH\u00c6\u0003J\u000f\u0010#\u001a\b\u0012\u0004\u0012\u00020\f0\u000bH\u00c6\u0003J\t\u0010$\u001a\u00020\u0003H\u00c6\u0003J\t\u0010%\u001a\u00020\u0003H\u00c6\u0003JW\u0010&\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t2\u000e\b\u0002\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\b\b\u0002\u0010\r\u001a\u00020\u00032\b\b\u0002\u0010\u000e\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\'\u001a\u00020\u00032\b\u0010(\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010)\u001a\u00020*H\u00d6\u0001J\t\u0010+\u001a\u00020\fH\u00d6\u0001R\u0011\u0010\r\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0013\u0010\b\u001a\u0004\u0018\u00010\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\u000e\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0011R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0011R\u0017\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001a\u00a8\u0006."}, d2 = {"Lcom/music/sale/domain/shipping/model/ShippingInfo;", "", "isShippable", "", "shippingFee", "Lcom/music/sale/domain/shipping/model/ShippingInfo$ShippingFee;", "estimatedDays", "Lcom/music/sale/domain/shipping/model/ShippingInfo$EstimatedDeliveryDays;", "freeShippingThreshold", "Ljava/math/BigDecimal;", "restrictedAreas", "", "", "canPickup", "internationalShipping", "(ZLcom/music/sale/domain/shipping/model/ShippingInfo$ShippingFee;Lcom/music/sale/domain/shipping/model/ShippingInfo$EstimatedDeliveryDays;Ljava/math/BigDecimal;Ljava/util/List;ZZ)V", "getCanPickup", "()Z", "getEstimatedDays", "()Lcom/music/sale/domain/shipping/model/ShippingInfo$EstimatedDeliveryDays;", "getFreeShippingThreshold", "()Ljava/math/BigDecimal;", "getInternationalShipping", "getRestrictedAreas", "()Ljava/util/List;", "getShippingFee", "()Lcom/music/sale/domain/shipping/model/ShippingInfo$ShippingFee;", "calculateShippingFee", "orderAmount", "canShipTo", "area", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "other", "hashCode", "", "toString", "EstimatedDeliveryDays", "ShippingFee", "music"})
public final class ShippingInfo {
    private final boolean isShippable = false;
    @org.jetbrains.annotations.NotNull()
    private final com.music.sale.domain.shipping.model.ShippingInfo.ShippingFee shippingFee = null;
    @org.jetbrains.annotations.NotNull()
    private final com.music.sale.domain.shipping.model.ShippingInfo.EstimatedDeliveryDays estimatedDays = null;
    @org.jetbrains.annotations.Nullable()
    private final java.math.BigDecimal freeShippingThreshold = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<java.lang.String> restrictedAreas = null;
    private final boolean canPickup = false;
    private final boolean internationalShipping = false;
    
    public ShippingInfo(boolean isShippable, @org.jetbrains.annotations.NotNull()
    com.music.sale.domain.shipping.model.ShippingInfo.ShippingFee shippingFee, @org.jetbrains.annotations.NotNull()
    com.music.sale.domain.shipping.model.ShippingInfo.EstimatedDeliveryDays estimatedDays, @org.jetbrains.annotations.Nullable()
    java.math.BigDecimal freeShippingThreshold, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> restrictedAreas, boolean canPickup, boolean internationalShipping) {
        super();
    }
    
    public final boolean isShippable() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.music.sale.domain.shipping.model.ShippingInfo.ShippingFee getShippingFee() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.music.sale.domain.shipping.model.ShippingInfo.EstimatedDeliveryDays getEstimatedDays() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.math.BigDecimal getFreeShippingThreshold() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.lang.String> getRestrictedAreas() {
        return null;
    }
    
    public final boolean getCanPickup() {
        return false;
    }
    
    public final boolean getInternationalShipping() {
        return false;
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
    
    public final boolean component1() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.music.sale.domain.shipping.model.ShippingInfo.ShippingFee component2() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.music.sale.domain.shipping.model.ShippingInfo.EstimatedDeliveryDays component3() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.math.BigDecimal component4() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.lang.String> component5() {
        return null;
    }
    
    public final boolean component6() {
        return false;
    }
    
    public final boolean component7() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.music.sale.domain.shipping.model.ShippingInfo copy(boolean isShippable, @org.jetbrains.annotations.NotNull()
    com.music.sale.domain.shipping.model.ShippingInfo.ShippingFee shippingFee, @org.jetbrains.annotations.NotNull()
    com.music.sale.domain.shipping.model.ShippingInfo.EstimatedDeliveryDays estimatedDays, @org.jetbrains.annotations.Nullable()
    java.math.BigDecimal freeShippingThreshold, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> restrictedAreas, boolean canPickup, boolean internationalShipping) {
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
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0005J\t\u0010\t\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\n\u001a\u00020\u0003H\u00c6\u0003J\u001d\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u000f\u001a\u00020\u0003H\u00d6\u0001J\b\u0010\u0010\u001a\u00020\u0011H\u0016R\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007\u00a8\u0006\u0012"}, d2 = {"Lcom/music/sale/domain/shipping/model/ShippingInfo$EstimatedDeliveryDays;", "", "min", "", "max", "(II)V", "getMax", "()I", "getMin", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "", "music"})
    public static final class EstimatedDeliveryDays {
        private final int min = 0;
        private final int max = 0;
        
        public EstimatedDeliveryDays(int min, int max) {
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
        public final com.music.sale.domain.shipping.model.ShippingInfo.EstimatedDeliveryDays copy(int min, int max) {
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
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\f\u001a\u00020\rH\u00d6\u0001J\u0006\u0010\u000e\u001a\u00020\nJ\t\u0010\u000f\u001a\u00020\u0010H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0011"}, d2 = {"Lcom/music/sale/domain/shipping/model/ShippingInfo$ShippingFee;", "", "value", "Ljava/math/BigDecimal;", "(Ljava/math/BigDecimal;)V", "getValue", "()Ljava/math/BigDecimal;", "component1", "copy", "equals", "", "other", "hashCode", "", "isFree", "toString", "", "music"})
    public static final class ShippingFee {
        @org.jetbrains.annotations.NotNull()
        private final java.math.BigDecimal value = null;
        
        public ShippingFee(@org.jetbrains.annotations.NotNull()
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
        public final com.music.sale.domain.shipping.model.ShippingInfo.ShippingFee copy(@org.jetbrains.annotations.NotNull()
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
}