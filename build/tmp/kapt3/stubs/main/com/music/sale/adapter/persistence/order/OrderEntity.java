package com.music.sale.adapter.persistence.order;

@jakarta.persistence.Entity()
@jakarta.persistence.Table(name = "orders")
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0017\u0018\u0000 !2\u00020\u0001:\u0001!BG\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u00a2\u0006\u0002\u0010\u0010J\b\u0010\u001f\u001a\u00020 H\u0016R\u0016\u0010\u0002\u001a\u00020\u00038\u0016X\u0097\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0016\u0010\n\u001a\u00020\u000b8\u0016X\u0097\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0016\u0010\f\u001a\u00020\r8\u0016X\u0097\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0016\u0010\u0005\u001a\u00020\u00038\u0016X\u0097\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0012R\u0016\u0010\u0006\u001a\u00020\u00078\u0016X\u0097\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0016\u0010\u000e\u001a\u00020\u000f8\u0016X\u0097\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0016\u0010\b\u001a\u00020\t8\u0016X\u0097\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0016\u0010\u0004\u001a\u00020\u00038\u0016X\u0097\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0012\u00a8\u0006\""}, d2 = {"Lcom/music/sale/adapter/persistence/order/OrderEntity;", "", "id", "", "userId", "productId", "quantity", "", "totalAmount", "Ljava/math/BigDecimal;", "orderStatus", "Lcom/music/sale/domain/order/Order$OrderStatus;", "payment", "Lcom/music/sale/adapter/persistence/order/PaymentEmbeddable;", "shipping", "Lcom/music/sale/adapter/persistence/order/ShippingEmbeddable;", "(JJJILjava/math/BigDecimal;Lcom/music/sale/domain/order/Order$OrderStatus;Lcom/music/sale/adapter/persistence/order/PaymentEmbeddable;Lcom/music/sale/adapter/persistence/order/ShippingEmbeddable;)V", "getId", "()J", "getOrderStatus", "()Lcom/music/sale/domain/order/Order$OrderStatus;", "getPayment", "()Lcom/music/sale/adapter/persistence/order/PaymentEmbeddable;", "getProductId", "getQuantity", "()I", "getShipping", "()Lcom/music/sale/adapter/persistence/order/ShippingEmbeddable;", "getTotalAmount", "()Ljava/math/BigDecimal;", "getUserId", "toDomain", "Lcom/music/sale/domain/order/Order;", "Companion", "music"})
public class OrderEntity {
    @jakarta.persistence.Id()
    @jakarta.persistence.GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private final long id = 0L;
    @jakarta.persistence.Column(nullable = false)
    private final long userId = 0L;
    @jakarta.persistence.Column(nullable = false)
    private final long productId = 0L;
    @jakarta.persistence.Column(nullable = false)
    private final int quantity = 0;
    @jakarta.persistence.Column(nullable = false)
    @org.jetbrains.annotations.NotNull()
    private final java.math.BigDecimal totalAmount = null;
    @jakarta.persistence.Enumerated(value = jakarta.persistence.EnumType.STRING)
    @jakarta.persistence.Column(nullable = false)
    @org.jetbrains.annotations.NotNull()
    private final com.music.sale.domain.order.Order.OrderStatus orderStatus = null;
    @jakarta.persistence.Embedded()
    @org.jetbrains.annotations.NotNull()
    private final com.music.sale.adapter.persistence.order.PaymentEmbeddable payment = null;
    @jakarta.persistence.Embedded()
    @org.jetbrains.annotations.NotNull()
    private final com.music.sale.adapter.persistence.order.ShippingEmbeddable shipping = null;
    @org.jetbrains.annotations.NotNull()
    public static final com.music.sale.adapter.persistence.order.OrderEntity.Companion Companion = null;
    
    public OrderEntity(long id, long userId, long productId, int quantity, @org.jetbrains.annotations.NotNull()
    java.math.BigDecimal totalAmount, @org.jetbrains.annotations.NotNull()
    com.music.sale.domain.order.Order.OrderStatus orderStatus, @org.jetbrains.annotations.NotNull()
    com.music.sale.adapter.persistence.order.PaymentEmbeddable payment, @org.jetbrains.annotations.NotNull()
    com.music.sale.adapter.persistence.order.ShippingEmbeddable shipping) {
        super();
    }
    
    public long getId() {
        return 0L;
    }
    
    public long getUserId() {
        return 0L;
    }
    
    public long getProductId() {
        return 0L;
    }
    
    public int getQuantity() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public java.math.BigDecimal getTotalAmount() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public com.music.sale.domain.order.Order.OrderStatus getOrderStatus() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public com.music.sale.adapter.persistence.order.PaymentEmbeddable getPayment() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public com.music.sale.adapter.persistence.order.ShippingEmbeddable getShipping() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public com.music.sale.domain.order.Order toDomain() {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006\u00a8\u0006\u0007"}, d2 = {"Lcom/music/sale/adapter/persistence/order/OrderEntity$Companion;", "", "()V", "fromDomain", "Lcom/music/sale/adapter/persistence/order/OrderEntity;", "order", "Lcom/music/sale/domain/order/Order;", "music"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.music.sale.adapter.persistence.order.OrderEntity fromDomain(@org.jetbrains.annotations.NotNull()
        com.music.sale.domain.order.Order order) {
            return null;
        }
    }
}