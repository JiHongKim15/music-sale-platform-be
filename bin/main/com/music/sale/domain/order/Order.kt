package com.music.sale.domain.order

import com.music.sale.domain.product.Product
import com.music.sale.domain.user.User
import java.math.BigDecimal

class Order private constructor(
    val id: Long?,
    val user: User,
    val product: Product,
    val quantity: Quantity,
    val totalAmount: Money,
    val orderStatus: OrderStatus,
    val payment: Payment,
    val shipping: Shipping
) {
    enum class OrderStatus {
        PENDING, PAID, SHIPPED, DELIVERED, CANCELLED
    }

    @JvmInline
    value class Quantity(val value: Int) {
        init {
            require(value > 0) { "수량은 0보다 커야 합니다" }
        }
    }

    @JvmInline
    value class Money(val value: BigDecimal) {
        init {
            require(value >= BigDecimal.ZERO) { "금액은 0 이상이어야 합니다" }
        }
    }

    companion object {
        fun create(
            user: User,
            product: Product,
            quantity: Int,
            payment: Payment,
            shipping: Shipping
        ): Order {
            return Order(
                id = null,
                user = user,
                product = product,
                quantity = Quantity(quantity),
                totalAmount = Money(product.price.toBigDecimal().multiply(quantity.toBigDecimal())),
                orderStatus = OrderStatus.PENDING,
                payment = payment,
                shipping = shipping
            )
        }
    }
}
