package com.sodamjae.domain.order

import com.sodamjae.domain.product.Product
import com.sodamjae.domain.user.User
import java.math.BigDecimal

class Order private constructor(
    val id: OrderId?,
    val user: User,
    val product: Product,
    private val quantity: Quantity,
    private val totalAmount: Money,
    private var orderStatus: OrderStatus,
    private var payment: Payment,
    private var shipping: Shipping
) {
    // Getters for persistence
    fun getQuantity(): Quantity = quantity
    fun getTotalAmount(): Money = totalAmount
    fun getOrderStatus(): OrderStatus = orderStatus
    fun getPayment(): Payment = payment
    fun getShipping(): Shipping = shipping

    companion object {
        fun create(
            user: User,
            product: Product,
            quantity: Quantity,
            payment: Payment,
            shipping: Shipping
        ): Order {
            val totalAmount = Money(product.getPrice().value * BigDecimal(quantity.value))
            return Order(
                id = null,
                user = user,
                product = product,
                quantity = quantity,
                totalAmount = totalAmount,
                orderStatus = OrderStatus.PENDING,
                payment = payment,
                shipping = shipping
            )
        }
    }

    fun updateOrderStatus(status: OrderStatus) {
        this.orderStatus = status
    }

    // Value Objects
    @JvmInline
    value class OrderId(val value: Long)

    @JvmInline
    value class Quantity(val value: Int) {
        init {
            require(value > 0) { "주문 수량은 0보다 커야 합니다" }
        }
    }

    @JvmInline
    value class Money(val value: BigDecimal) {
        init {
            require(value >= BigDecimal.ZERO) { "금액은 0 이상이어야 합니다" }
        }
    }

    enum class OrderStatus {
        PENDING, PAID, SHIPPING, COMPLETED, CANCELLED
    }
}

class Payment private constructor(
    private val method: PaymentMethod,
    private var status: PaymentStatus
) {
    // Getters for persistence
    fun getMethod(): PaymentMethod = method
    fun getStatus(): PaymentStatus = status

    companion object {
        fun create(method: PaymentMethod): Payment {
            return Payment(method, PaymentStatus.PENDING)
        }
    }

    fun complete() {
        this.status = PaymentStatus.COMPLETED
    }

    fun fail() {
        this.status = PaymentStatus.FAILED
    }

    enum class PaymentMethod {
        CARD, DIRECT
    }

    enum class PaymentStatus {
        PENDING, COMPLETED, FAILED
    }
}

class Shipping private constructor(
    private var address: Address,
    private var method: ShippingMethod,
    private var trackingNumber: TrackingNumber?
) {
    // Getters for persistence
    fun getAddress(): Address = address
    fun getMethod(): ShippingMethod = method
    fun getTrackingNumber(): TrackingNumber? = trackingNumber

    companion object {
        fun create(address: Address, method: ShippingMethod): Shipping {
            return Shipping(address, method, null)
        }
    }

    fun updateTrackingNumber(trackingNumber: TrackingNumber) {
        this.trackingNumber = trackingNumber
    }

    @JvmInline
    value class Address(val value: String) {
        init {
            require(value.isNotBlank()) { "배송 주소는 비어있을 수 없습니다" }
        }
    }

    @JvmInline
    value class TrackingNumber(val value: String) {
        init {
            require(value.isNotBlank()) { "운송장 번호는 비어있을 수 없습니다" }
        }
    }

    enum class ShippingMethod {
        DELIVERY, PICKUP
    }
} 