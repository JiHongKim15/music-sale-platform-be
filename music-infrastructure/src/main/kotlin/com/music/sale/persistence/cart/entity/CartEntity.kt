package com.music.sale.persistence.cart.entity

import com.music.sale.domain.cart.Cart
import com.music.sale.domain.product.Product
import com.music.sale.domain.user.User
import com.music.sale.persistence.common.BaseEntity
import jakarta.persistence.*

@Entity
@Table(name = "carts")
class CartEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long? = null,
    @Column(name = "user_id", nullable = false) val userId: Long,
    @Column(name = "product_id", nullable = false) val productId: Long,
    @Column(nullable = false) val quantity: Int,
) : BaseEntity() {
    fun toDomain(
        user: User,
        product: Product,
    ): Cart {
        return Cart(
            id = id,
            user = user,
            product = product,
            quantity = quantity,
            createdAt = createdAt,
            updatedAt = updatedAt,
        )
    }

    companion object {
        fun fromDomain(cart: Cart): CartEntity {
            return CartEntity(
                id = cart.id,
                userId = cart.user.id!!,
                productId = cart.product.id!!,
                quantity = cart.quantity,
            )
        }
    }
}
