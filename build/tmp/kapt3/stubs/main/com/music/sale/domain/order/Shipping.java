package com.music.sale.domain.order;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u0002\n\u0002\b\u0006\u0018\u0000 \u00152\u00020\u0001:\u0004\u0014\u0015\u0016\u0017B#\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u00a2\u0006\u0002\u0010\bJ\u0018\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0006\u001a\u00020\u0007\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0013\u0010\u0010R\u0019\u0010\u0002\u001a\u00020\u0003\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\n\n\u0002\u0010\u000b\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR$\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0086\u000e\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0010\n\u0002\u0010\u000b\u001a\u0004\b\u000e\u0010\n\"\u0004\b\u000f\u0010\u0010\u0082\u0002\u000b\n\u0005\b\u00a1\u001e0\u0001\n\u0002\b!\u00a8\u0006\u0018"}, d2 = {"Lcom/music/sale/domain/order/Shipping;", "", "address", "Lcom/music/sale/domain/order/Shipping$Address;", "method", "Lcom/music/sale/domain/order/Shipping$ShippingMethod;", "trackingNumber", "Lcom/music/sale/domain/order/Shipping$TrackingNumber;", "(Ljava/lang/String;Lcom/music/sale/domain/order/Shipping$ShippingMethod;Ljava/lang/String;)V", "getAddress-XIOHmUo", "()Ljava/lang/String;", "Ljava/lang/String;", "getMethod", "()Lcom/music/sale/domain/order/Shipping$ShippingMethod;", "getTrackingNumber-dtT04ww", "setTrackingNumber-DzV53kQ", "(Ljava/lang/String;)V", "updateTrackingNumber", "", "updateTrackingNumber-J29_1HQ", "Address", "Companion", "ShippingMethod", "TrackingNumber", "music"})
public final class Shipping {
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String address = null;
    @org.jetbrains.annotations.NotNull()
    private final com.music.sale.domain.order.Shipping.ShippingMethod method = null;
    @org.jetbrains.annotations.Nullable()
    private java.lang.String trackingNumber;
    @org.jetbrains.annotations.NotNull()
    public static final com.music.sale.domain.order.Shipping.Companion Companion = null;
    
    private Shipping(java.lang.String address, com.music.sale.domain.order.Shipping.ShippingMethod method, java.lang.String trackingNumber) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.music.sale.domain.order.Shipping.ShippingMethod getMethod() {
        return null;
    }
    
    @kotlin.jvm.JvmInline()
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0005\b\u0087@\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0004\b\u0004\u0010\u0005J\u001a\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003\u00a2\u0006\u0004\b\u000b\u0010\fJ\u0010\u0010\r\u001a\u00020\u000eH\u00d6\u0001\u00a2\u0006\u0004\b\u000f\u0010\u0010J\u0010\u0010\u0011\u001a\u00020\u0003H\u00d6\u0001\u00a2\u0006\u0004\b\u0012\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007\u0088\u0001\u0002\u00a8\u0006\u0013"}, d2 = {"Lcom/music/sale/domain/order/Shipping$Address;", "", "value", "", "constructor-impl", "(Ljava/lang/String;)Ljava/lang/String;", "getValue", "()Ljava/lang/String;", "equals", "", "other", "equals-impl", "(Ljava/lang/String;Ljava/lang/Object;)Z", "hashCode", "", "hashCode-impl", "(Ljava/lang/String;)I", "toString", "toString-impl", "music"})
    public static final class Address {
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String value = null;
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getValue() {
            return null;
        }
        
        @java.lang.Override()
        public boolean equals(java.lang.Object other) {
            return false;
        }
        
        @java.lang.Override()
        public int hashCode() {
            return 0;
        }
        
        @java.lang.Override()
        public java.lang.String toString() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J \u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b\u00f8\u0001\u0000\u00a2\u0006\u0004\b\t\u0010\n\u0082\u0002\u0007\n\u0005\b\u00a1\u001e0\u0001\u00a8\u0006\u000b"}, d2 = {"Lcom/music/sale/domain/order/Shipping$Companion;", "", "()V", "create", "Lcom/music/sale/domain/order/Shipping;", "address", "Lcom/music/sale/domain/order/Shipping$Address;", "method", "Lcom/music/sale/domain/order/Shipping$ShippingMethod;", "create-hYDDeiQ", "(Ljava/lang/String;Lcom/music/sale/domain/order/Shipping$ShippingMethod;)Lcom/music/sale/domain/order/Shipping;", "music"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005\u00a8\u0006\u0006"}, d2 = {"Lcom/music/sale/domain/order/Shipping$ShippingMethod;", "", "(Ljava/lang/String;I)V", "GENERAL", "EXPRESS", "PICKUP", "music"})
    public static enum ShippingMethod {
        /*public static final*/ GENERAL /* = new GENERAL() */,
        /*public static final*/ EXPRESS /* = new EXPRESS() */,
        /*public static final*/ PICKUP /* = new PICKUP() */;
        
        ShippingMethod() {
        }
        
        @org.jetbrains.annotations.NotNull()
        public static kotlin.enums.EnumEntries<com.music.sale.domain.order.Shipping.ShippingMethod> getEntries() {
            return null;
        }
    }
    
    @kotlin.jvm.JvmInline()
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0005\b\u0087@\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0004\b\u0004\u0010\u0005J\u001a\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003\u00a2\u0006\u0004\b\u000b\u0010\fJ\u0010\u0010\r\u001a\u00020\u000eH\u00d6\u0001\u00a2\u0006\u0004\b\u000f\u0010\u0010J\u0010\u0010\u0011\u001a\u00020\u0003H\u00d6\u0001\u00a2\u0006\u0004\b\u0012\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007\u0088\u0001\u0002\u00a8\u0006\u0013"}, d2 = {"Lcom/music/sale/domain/order/Shipping$TrackingNumber;", "", "value", "", "constructor-impl", "(Ljava/lang/String;)Ljava/lang/String;", "getValue", "()Ljava/lang/String;", "equals", "", "other", "equals-impl", "(Ljava/lang/String;Ljava/lang/Object;)Z", "hashCode", "", "hashCode-impl", "(Ljava/lang/String;)I", "toString", "toString-impl", "music"})
    public static final class TrackingNumber {
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String value = null;
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getValue() {
            return null;
        }
        
        @java.lang.Override()
        public boolean equals(java.lang.Object other) {
            return false;
        }
        
        @java.lang.Override()
        public int hashCode() {
            return 0;
        }
        
        @java.lang.Override()
        public java.lang.String toString() {
            return null;
        }
    }
}