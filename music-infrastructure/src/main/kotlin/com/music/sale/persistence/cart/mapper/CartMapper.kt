package com.music.sale.persistence.cart.mapper

import com.music.sale.domain.cart.Cart
import com.music.sale.domain.product.Product
import com.music.sale.domain.user.User
import com.music.sale.persistence.cart.entity.CartEntity
import org.springframework.stereotype.Component

@Component
class CartMapper {
    fun toDomain(
        entity: CartEntity,
        user: User,
        product: Product,
    ): Cart {
        return Cart(
            id = entity.id,
            user = user,
            product = product,
            quantity = entity.quantity,
            createdAt = entity.createdAt,
            updatedAt = entity.updatedAt,
        )
    }

    fun toEntity(domain: Cart): CartEntity {
        return CartEntity(
            id = domain.id,
            userId = domain.user.id!!,
            productId = domain.product.id!!,
            quantity = domain.quantity,
        )
    }
}
