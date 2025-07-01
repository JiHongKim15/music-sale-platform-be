// Copyright (C) 2024 Your Name or Company
package com.music.sale.adapter.persistence.wishlist.entity

import com.music.sale.adapter.persistence.common.BaseEntity
import com.music.sale.adapter.persistence.product.entity.ProductItemEntity
import com.music.sale.adapter.persistence.user.entity.UserEntity
import jakarta.persistence.*

/**
 * 찜 목록 엔티티
 */
@Entity
@Table(
    name = "wishlist",
    uniqueConstraints = [
        UniqueConstraint(
            columnNames = ["user_id", "product_id"],
            name = "uk_wishlist_user_product"
        )
    ]
)
class WishlistEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    val user: UserEntity,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    val product: ProductItemEntity,
) : BaseEntity() 
