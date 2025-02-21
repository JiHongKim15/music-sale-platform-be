package com.sodamjae.adapter.persistence.order

import com.sodamjae.adapter.persistence.common.BaseEntity
import com.sodamjae.adapter.persistence.product.ProductEntity
import com.sodamjae.adapter.persistence.user.UserEntity
import com.sodamjae.domain.order.Order
import com.sodamjae.domain.order.Payment
import com.sodamjae.domain.order.Shipping
import jakarta.persistence.*
import java.math.BigDecimal

@Entity
@Table(name = "orders")
class OrderEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    val user: UserEntity,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    val product: ProductEntity,

    @Column(nullable = false)
    val quantity: Int,

    @Column(nullable = false)
    val totalAmount: BigDecimal,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    var orderStatus: Order.OrderStatus,

    @Embedded
    var payment: PaymentEmbeddable,

    @Embedded
    var shipping: ShippingEmbeddable,

) : BaseEntity() {
    fun toDomain(): Order {
        return Order.create(
            user = user.toDomain(),
            product = product.toDomain(),
            quantity = Order.Quantity(quantity),
            payment = payment.toDomain(),
            shipping = shipping.toDomain()
        )
    }

    companion object {
        fun fromDomain(
            order: Order,
            userEntity: UserEntity,
            productEntity: ProductEntity
        ): OrderEntity {
            return OrderEntity(
                id = order.id?.value,
                user = userEntity,
                product = productEntity,
                quantity = order.getQuantity().value,
                totalAmount = order.getTotalAmount().value,
                orderStatus = order.getOrderStatus(),
                payment = PaymentEmbeddable.fromDomain(order.getPayment()),
                shipping = ShippingEmbeddable.fromDomain(order.getShipping()),
            )
        }
    }
}

@Embeddable
class PaymentEmbeddable(
    @Enumerated(EnumType.STRING)
    @Column(name = "payment_method", nullable = false)
    val method: Payment.PaymentMethod,

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_status", nullable = false)
    var status: Payment.PaymentStatus
) {
    fun toDomain(): Payment {
        return Payment.create(method)
    }

    companion object {
        fun fromDomain(payment: Payment): PaymentEmbeddable {
            return PaymentEmbeddable(
                method = payment.getMethod(),
                status = payment.getStatus()
            )
        }
    }
}

@Embeddable
class ShippingEmbeddable(
    @Column(name = "shipping_address", nullable = false)
    var address: String,

    @Enumerated(EnumType.STRING)
    @Column(name = "shipping_method", nullable = false)
    var method: Shipping.ShippingMethod,

    @Column(name = "tracking_number")
    var trackingNumber: String?
) {
    fun toDomain(): Shipping {
        return Shipping.create(
            address = Shipping.Address(address),
            method = method
        ).apply {
            trackingNumber?.let { updateTrackingNumber(Shipping.TrackingNumber(it)) }
        }
    }

    companion object {
        fun fromDomain(shipping: Shipping): ShippingEmbeddable {
            return ShippingEmbeddable(
                address = shipping.getAddress().value,
                method = shipping.getMethod(),
                trackingNumber = shipping.getTrackingNumber()?.value
            )
        }
    }
} 