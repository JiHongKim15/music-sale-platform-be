// Copyright (C) 2024 Your Name or Company
package com.music.sale.persistence.order

import com.music.sale.domain.order.enum.OrderStatus
import com.music.sale.persistence.common.BaseEntity
import com.music.sale.persistence.user.entity.UserEntity
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "orders")
class OrderEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long? = null,
    @Column(nullable = false, unique = true) val orderNumber: String,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    val user: UserEntity,
    @Column(nullable = false) val totalAmount: Int,
    @Enumerated(EnumType.STRING) @Column(nullable = false) val status: OrderStatus,
    @Column(nullable = false) val orderDate: LocalDateTime,
    @Embedded val deliveryAddress: DeliveryAddressEmbeddable,
    @Embedded val paymentInfo: PaymentInfoEmbeddable? = null,
    @OneToMany(mappedBy = "order", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    val orderItems: List<OrderItemEntity> = emptyList(),
) : BaseEntity()

@Entity
@Table(name = "order_items")
class OrderItemEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long? = null,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    val order: OrderEntity,
    @Column(nullable = false) val productId: Long,
    @Column(nullable = false) val productName: String,
    @Column(nullable = false) val price: Int,
    @Column(nullable = false) val quantity: Int,
    @Column(nullable = false) val sellerId: Long,
) : BaseEntity()

@Embeddable
data class DeliveryAddressEmbeddable(
    @Column(name = "recipient_name", nullable = false) val recipientName: String,
    @Column(name = "phone", nullable = false) val phone: String,
    @Column(name = "zipcode", nullable = false) val zipcode: String,
    @Column(name = "base_address", nullable = false) val baseAddress: String,
    @Column(name = "detail_address", nullable = false) val detailAddress: String,
    @Column(name = "delivery_message") val deliveryMessage: String? = null,
)

@Embeddable
data class PaymentInfoEmbeddable(
    @Column(name = "payment_method", nullable = false) val paymentMethod: String,
    @Column(name = "payment_amount", nullable = false) val paymentAmount: Int,
    @Column(name = "payment_date", nullable = false) val paymentDate: LocalDateTime,
    @Column(name = "transaction_id", nullable = false) val transactionId: String,
)
