package com.music.sale.domain.order;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\u0018\u0000 \r2\u00020\u0001:\u0003\r\u000e\u000fB\u0019\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\f\u00a8\u0006\u0010"}, d2 = {"Lcom/music/sale/domain/order/Payment;", "", "method", "Lcom/music/sale/domain/order/Payment$PaymentMethod;", "status", "Lcom/music/sale/domain/order/Payment$PaymentStatus;", "(Lcom/music/sale/domain/order/Payment$PaymentMethod;Lcom/music/sale/domain/order/Payment$PaymentStatus;)V", "getMethod", "()Lcom/music/sale/domain/order/Payment$PaymentMethod;", "getStatus", "()Lcom/music/sale/domain/order/Payment$PaymentStatus;", "setStatus", "(Lcom/music/sale/domain/order/Payment$PaymentStatus;)V", "Companion", "PaymentMethod", "PaymentStatus", "music"})
public final class Payment {
    @org.jetbrains.annotations.NotNull()
    private final com.music.sale.domain.order.Payment.PaymentMethod method = null;
    @org.jetbrains.annotations.NotNull()
    private com.music.sale.domain.order.Payment.PaymentStatus status;
    @org.jetbrains.annotations.NotNull()
    public static final com.music.sale.domain.order.Payment.Companion Companion = null;
    
    private Payment(com.music.sale.domain.order.Payment.PaymentMethod method, com.music.sale.domain.order.Payment.PaymentStatus status) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.music.sale.domain.order.Payment.PaymentMethod getMethod() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.music.sale.domain.order.Payment.PaymentStatus getStatus() {
        return null;
    }
    
    public final void setStatus(@org.jetbrains.annotations.NotNull()
    com.music.sale.domain.order.Payment.PaymentStatus p0) {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006\u00a8\u0006\u0007"}, d2 = {"Lcom/music/sale/domain/order/Payment$Companion;", "", "()V", "create", "Lcom/music/sale/domain/order/Payment;", "method", "Lcom/music/sale/domain/order/Payment$PaymentMethod;", "music"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.music.sale.domain.order.Payment create(@org.jetbrains.annotations.NotNull()
        com.music.sale.domain.order.Payment.PaymentMethod method) {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006\u00a8\u0006\u0007"}, d2 = {"Lcom/music/sale/domain/order/Payment$PaymentMethod;", "", "(Ljava/lang/String;I)V", "CREDIT_CARD", "BANK_TRANSFER", "KAKAO_PAY", "NAVER_PAY", "music"})
    public static enum PaymentMethod {
        /*public static final*/ CREDIT_CARD /* = new CREDIT_CARD() */,
        /*public static final*/ BANK_TRANSFER /* = new BANK_TRANSFER() */,
        /*public static final*/ KAKAO_PAY /* = new KAKAO_PAY() */,
        /*public static final*/ NAVER_PAY /* = new NAVER_PAY() */;
        
        PaymentMethod() {
        }
        
        @org.jetbrains.annotations.NotNull()
        public static kotlin.enums.EnumEntries<com.music.sale.domain.order.Payment.PaymentMethod> getEntries() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006\u00a8\u0006\u0007"}, d2 = {"Lcom/music/sale/domain/order/Payment$PaymentStatus;", "", "(Ljava/lang/String;I)V", "PENDING", "COMPLETED", "FAILED", "REFUNDED", "music"})
    public static enum PaymentStatus {
        /*public static final*/ PENDING /* = new PENDING() */,
        /*public static final*/ COMPLETED /* = new COMPLETED() */,
        /*public static final*/ FAILED /* = new FAILED() */,
        /*public static final*/ REFUNDED /* = new REFUNDED() */;
        
        PaymentStatus() {
        }
        
        @org.jetbrains.annotations.NotNull()
        public static kotlin.enums.EnumEntries<com.music.sale.domain.order.Payment.PaymentStatus> getEntries() {
            return null;
        }
    }
}