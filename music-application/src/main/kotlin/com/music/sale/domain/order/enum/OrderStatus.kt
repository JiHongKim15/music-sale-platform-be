// Copyright (C) 2024 Your Name or Company
package com.music.sale.domain.order.enum

/**
 * 주문 상태
 */
enum class OrderStatus {
    PENDING, // 주문 대기
    CONFIRMED, // 주문 확인
    PAID, // 결제 완료
    PREPARING, // 배송 준비
    SHIPPED, // 배송 중
    DELIVERED, // 배송 완료
    CANCELLED, // 주문 취소
    REFUNDED, // 환불 완료
} 
