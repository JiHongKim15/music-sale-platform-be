// Copyright (C) 2024 Your Name or Company
package com.music.sale.adapter.persistence.cart.entity

import com.music.sale.adapter.persistence.common.BaseEntity
import com.music.sale.adapter.persistence.product.entity.ProductItemEntity
import com.music.sale.adapter.persistence.user.entity.UserEntity
import jakarta.persistence.*

/**
 * 장바구니 엔티티
 */
@Entity
@Table(
    name = "cart",
    uniqueConstraints = [
        UniqueConstraint(
            columnNames = ["user_id", "product_id"],
            name = "uk_cart_user_product"
        )
    ]
)
class CartEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    val user: UserEntity,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    val product: ProductItemEntity,

    @Column(nullable = false)
    val quantity: Int,
) : BaseEntity() 
