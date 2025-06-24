package com.music.sale.domain.order;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0019\u0018\u0000 &2\u00020\u0001:\u0004&\'()BI\b\u0002\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\u0006\u0010\u0010\u001a\u00020\u0011\u00a2\u0006\u0002\u0010\u0012R\u0015\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\n\n\u0002\u0010\u0015\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\f\u001a\u00020\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\u000e\u001a\u00020\u000f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0019\u0010\b\u001a\u00020\t\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\n\n\u0002\u0010\u001e\u001a\u0004\b\u001c\u0010\u001dR\u0011\u0010\u0010\u001a\u00020\u0011\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u0019\u0010\n\u001a\u00020\u000b\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\n\n\u0002\u0010#\u001a\u0004\b!\u0010\"R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b$\u0010%\u0082\u0002\u000b\n\u0005\b\u00a1\u001e0\u0001\n\u0002\b!\u00a8\u0006*"}, d2 = {"Lcom/music/sale/domain/order/Order;", "", "id", "", "user", "Lcom/music/sale/domain/user/User;", "product", "Lcom/music/sale/domain/product/Product;", "quantity", "Lcom/music/sale/domain/order/Order$Quantity;", "totalAmount", "Lcom/music/sale/domain/order/Order$Money;", "orderStatus", "Lcom/music/sale/domain/order/Order$OrderStatus;", "payment", "Lcom/music/sale/domain/order/Payment;", "shipping", "Lcom/music/sale/domain/order/Shipping;", "(Ljava/lang/Long;Lcom/music/sale/domain/user/User;Lcom/music/sale/domain/product/Product;ILjava/math/BigDecimal;Lcom/music/sale/domain/order/Order$OrderStatus;Lcom/music/sale/domain/order/Payment;Lcom/music/sale/domain/order/Shipping;)V", "getId", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getOrderStatus", "()Lcom/music/sale/domain/order/Order$OrderStatus;", "getPayment", "()Lcom/music/sale/domain/order/Payment;", "getProduct", "()Lcom/music/sale/domain/product/Product;", "getQuantity-xeojoWA", "()I", "I", "getShipping", "()Lcom/music/sale/domain/order/Shipping;", "getTotalAmount-Nll6680", "()Ljava/math/BigDecimal;", "Ljava/math/BigDecimal;", "getUser", "()Lcom/music/sale/domain/user/User;", "Companion", "Money", "OrderStatus", "Quantity", "music"})
public final class Order {
    @org.jetbrains.annotations.Nullable()
    private final java.lang.Long id = null;
    @org.jetbrains.annotations.NotNull()
    private final com.music.sale.domain.user.User user = null;
    @org.jetbrains.annotations.NotNull()
    private final com.music.sale.domain.product.Product product = null;
    private final int quantity = 0;
    @org.jetbrains.annotations.NotNull()
    private final java.math.BigDecimal totalAmount = null;
    @org.jetbrains.annotations.NotNull()
    private final com.music.sale.domain.order.Order.OrderStatus orderStatus = null;
    @org.jetbrains.annotations.NotNull()
    private final com.music.sale.domain.order.Payment payment = null;
    @org.jetbrains.annotations.NotNull()
    private final com.music.sale.domain.order.Shipping shipping = null;
    @org.jetbrains.annotations.NotNull()
    public static final com.music.sale.domain.order.Order.Companion Companion = null;
    
    private Order(java.lang.Long id, com.music.sale.domain.user.User user, com.music.sale.domain.product.Product product, int quantity, java.math.BigDecimal totalAmount, com.music.sale.domain.order.Order.OrderStatus orderStatus, com.music.sale.domain.order.Payment payment, com.music.sale.domain.order.Shipping shipping) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Long getId() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.music.sale.domain.user.User getUser() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.music.sale.domain.product.Product getProduct() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.music.sale.domain.order.Order.OrderStatus getOrderStatus() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.music.sale.domain.order.Payment getPayment() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.music.sale.domain.order.Shipping getShipping() {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J.\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e\u00a8\u0006\u000f"}, d2 = {"Lcom/music/sale/domain/order/Order$Companion;", "", "()V", "create", "Lcom/music/sale/domain/order/Order;", "user", "Lcom/music/sale/domain/user/User;", "product", "Lcom/music/sale/domain/product/Product;", "quantity", "", "payment", "Lcom/music/sale/domain/order/Payment;", "shipping", "Lcom/music/sale/domain/order/Shipping;", "music"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.music.sale.domain.order.Order create(@org.jetbrains.annotations.NotNull()
        com.music.sale.domain.user.User user, @org.jetbrains.annotations.NotNull()
        com.music.sale.domain.product.Product product, int quantity, @org.jetbrains.annotations.NotNull()
        com.music.sale.domain.order.Payment payment, @org.jetbrains.annotations.NotNull()
        com.music.sale.domain.order.Shipping shipping) {
            return null;
        }
    }
    
    @kotlin.jvm.JvmInline()
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0087@\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0004\b\u0004\u0010\u0005J\u001a\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003\u00a2\u0006\u0004\b\u000b\u0010\fJ\u0010\u0010\r\u001a\u00020\u000eH\u00d6\u0001\u00a2\u0006\u0004\b\u000f\u0010\u0010J\u0010\u0010\u0011\u001a\u00020\u0012H\u00d6\u0001\u00a2\u0006\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007\u0088\u0001\u0002\u00a8\u0006\u0015"}, d2 = {"Lcom/music/sale/domain/order/Order$Money;", "", "value", "Ljava/math/BigDecimal;", "constructor-impl", "(Ljava/math/BigDecimal;)Ljava/math/BigDecimal;", "getValue", "()Ljava/math/BigDecimal;", "equals", "", "other", "equals-impl", "(Ljava/math/BigDecimal;Ljava/lang/Object;)Z", "hashCode", "", "hashCode-impl", "(Ljava/math/BigDecimal;)I", "toString", "", "toString-impl", "(Ljava/math/BigDecimal;)Ljava/lang/String;", "music"})
    public static final class Money {
        @org.jetbrains.annotations.NotNull()
        private final java.math.BigDecimal value = null;
        
        @org.jetbrains.annotations.NotNull()
        public final java.math.BigDecimal getValue() {
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
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0007\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007\u00a8\u0006\b"}, d2 = {"Lcom/music/sale/domain/order/Order$OrderStatus;", "", "(Ljava/lang/String;I)V", "PENDING", "PAID", "SHIPPED", "DELIVERED", "CANCELLED", "music"})
    public static enum OrderStatus {
        /*public static final*/ PENDING /* = new PENDING() */,
        /*public static final*/ PAID /* = new PAID() */,
        /*public static final*/ SHIPPED /* = new SHIPPED() */,
        /*public static final*/ DELIVERED /* = new DELIVERED() */,
        /*public static final*/ CANCELLED /* = new CANCELLED() */;
        
        OrderStatus() {
        }
        
        @org.jetbrains.annotations.NotNull()
        public static kotlin.enums.EnumEntries<com.music.sale.domain.order.Order.OrderStatus> getEntries() {
            return null;
        }
    }
    
    @kotlin.jvm.JvmInline()
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0087@\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0004\b\u0004\u0010\u0005J\u001a\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003\u00a2\u0006\u0004\b\u000b\u0010\fJ\u0010\u0010\r\u001a\u00020\u0003H\u00d6\u0001\u00a2\u0006\u0004\b\u000e\u0010\u0005J\u0010\u0010\u000f\u001a\u00020\u0010H\u00d6\u0001\u00a2\u0006\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007\u0088\u0001\u0002\u00a8\u0006\u0013"}, d2 = {"Lcom/music/sale/domain/order/Order$Quantity;", "", "value", "", "constructor-impl", "(I)I", "getValue", "()I", "equals", "", "other", "equals-impl", "(ILjava/lang/Object;)Z", "hashCode", "hashCode-impl", "toString", "", "toString-impl", "(I)Ljava/lang/String;", "music"})
    public static final class Quantity {
        private final int value = 0;
        
        public final int getValue() {
            return 0;
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