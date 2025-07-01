// Copyright (C) 2024 Your Name or Company
package com.music.sale.domain.order

import com.music.sale.domain.order.enum.OrderStatus
import com.music.sale.domain.user.User
import java.time.LocalDateTime

/**
 * 주문 도메인 모델
 */
data class Order(
    val id: Long? = null,
    val orderNumber: String,
    val user: User,
    val orderItems: List<OrderItem>,
    val totalAmount: Int,
    val status: OrderStatus,
    val orderDate: LocalDateTime = LocalDateTime.now(),
    val deliveryAddress: DeliveryAddress,
    val paymentInfo: PaymentInfo? = null,
) {
    init {
        require(user.id != null) { "사용자 ID는 필수입니다" }
        require(orderItems.isNotEmpty()) { "주문 상품이 하나 이상 있어야 합니다" }
        require(totalAmount > 0) { "주문 금액은 0보다 커야 합니다" }
        require(orderNumber.isNotBlank()) { "주문 번호는 필수입니다" }
    }

    companion object {
        fun create(
            orderNumber: String,
            user: User,
            orderItems: List<OrderItem>,
            deliveryAddress: DeliveryAddress
        ): Order {
            val totalAmount = orderItems.sumOf { it.getTotalPrice() }
            
            return Order(
                orderNumber = orderNumber,
                user = user,
                orderItems = orderItems,
                totalAmount = totalAmount,
                status = OrderStatus.PENDING,
                deliveryAddress = deliveryAddress
            )
        }
    }

    fun updateStatus(newStatus: OrderStatus): Order {
        return copy(status = newStatus)
    }

    fun addPaymentInfo(paymentInfo: PaymentInfo): Order {
        return copy(paymentInfo = paymentInfo)
    }
}

/**
 * 주문 상품 도메인 모델
 */
data class OrderItem(
    val id: Long? = null,
    val productId: Long,
    val productName: String,
    val price: Int,
    val quantity: Int,
    val sellerId: Long,
) {
    init {
        require(productId > 0) { "상품 ID는 필수입니다" }
        require(productName.isNotBlank()) { "상품명은 필수입니다" }
        require(price > 0) { "가격은 0보다 커야 합니다" }
        require(quantity > 0) { "수량은 0보다 커야 합니다" }
        require(sellerId > 0) { "판매자 ID는 필수입니다" }
    }

    fun getTotalPrice(): Int {
        return price * quantity
    }
}

/**
 * 배송 주소 정보
 */
data class DeliveryAddress(
    val recipientName: String,
    val phone: String,
    val zipcode: String,
    val baseAddress: String,
    val detailAddress: String,
    val deliveryMessage: String? = null,
) {
    init {
        require(recipientName.isNotBlank()) { "수령인 이름은 필수입니다" }
        require(phone.isNotBlank()) { "전화번호는 필수입니다" }
        require(zipcode.isNotBlank()) { "우편번호는 필수입니다" }
        require(baseAddress.isNotBlank()) { "기본 주소는 필수입니다" }
        require(detailAddress.isNotBlank()) { "상세 주소는 필수입니다" }
    }
}

/**
 * 결제 정보
 */
data class PaymentInfo(
    val paymentMethod: String,
    val paymentAmount: Int,
    val paymentDate: LocalDateTime,
    val transactionId: String,
) {
    init {
        require(paymentMethod.isNotBlank()) { "결제 방법은 필수입니다" }
        require(paymentAmount > 0) { "결제 금액은 0보다 커야 합니다" }
        require(transactionId.isNotBlank()) { "거래 ID는 필수입니다" }
    }
}
