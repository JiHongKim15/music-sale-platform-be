package com.music.sale.adapter.persistence.order

import com.music.sale.domain.category.Category
import com.music.sale.domain.category.CategoryType
import com.music.sale.domain.order.Order
import com.music.sale.domain.order.Payment
import com.music.sale.domain.order.Shipping
import com.music.sale.domain.product.Product
import com.music.sale.domain.product.enum.ProductCondition
import com.music.sale.domain.product.enum.ProductStatus
import com.music.sale.domain.user.User
import jakarta.persistence.*
import java.math.BigDecimal

@Entity
@Table(name = "orders")
class OrderEntity(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long = 0L,
        @Column(nullable = false) val userId: Long,
        @Column(nullable = false) val productId: Long,
        @Column(nullable = false) val quantity: Int,
        @Column(nullable = false) val totalAmount: BigDecimal,
        @Enumerated(EnumType.STRING) @Column(nullable = false) val orderStatus: Order.OrderStatus,
        @Embedded val payment: PaymentEmbeddable,
        @Embedded val shipping: ShippingEmbeddable
) {
    companion object {
        fun fromDomain(order: Order): OrderEntity {
            return OrderEntity(
                    id = order.id ?: 0L,
                    userId = order.user.id ?: 0L,
                    productId = order.product.id,
                    quantity = order.quantity.value,
                    totalAmount = order.totalAmount.value,
                    orderStatus = order.orderStatus,
                    payment =
                            PaymentEmbeddable(
                                    method = order.payment.method.name,
                                    status = order.payment.status.name
                            ),
                    shipping =
                            ShippingEmbeddable(
                                    address = order.shipping.address.value,
                                    method = order.shipping.method.name,
                                    trackingNumber = order.shipping.trackingNumber?.value
                            )
            )
        }
    }

    fun toDomain(): Order {
        return Order.create(
                user = User(id = userId),
                product =
                        Product(
                                id = productId,
                                name = "",
                                category =
                                        Category(
                                                id = 0L,
                                                name = "",
                                                type = CategoryType.PRODUCT,
                                                parent = null,
                                                path = "",
                                                depth = 0,
                                                isActive = true
                                        ),
                                price = 0,
                                seller = User(id = 0),
                                store = null,
                                condition = ProductCondition.NEW,
                                conditionGrade = null,
                                stockQuantity = 0,
                                status = ProductStatus.SOLD,
                                attributes = emptyMap(),
                                catalogId = TODO(),
                                customName = TODO(),
                                customAttributes = TODO()
                        ),
                quantity = quantity,
                payment = Payment.create(Payment.PaymentMethod.valueOf(payment.method)),
                shipping =
                        Shipping.create(
                                        address = Shipping.Address(shipping.address),
                                        method = Shipping.ShippingMethod.valueOf(shipping.method)
                                )
                                .apply {
                                    shipping.trackingNumber?.let {
                                        updateTrackingNumber(Shipping.TrackingNumber(it))
                                    }
                                }
        )
    }
}

@Embeddable
data class PaymentEmbeddable(
        @Column(name = "payment_method", nullable = false) val method: String,
        @Column(nullable = false) val status: String
)

@Embeddable
data class ShippingEmbeddable(
        @Column(nullable = false) val address: String,
        @Column(name = "shipping_method", nullable = false) val method: String,
        @Column val trackingNumber: String?
)
