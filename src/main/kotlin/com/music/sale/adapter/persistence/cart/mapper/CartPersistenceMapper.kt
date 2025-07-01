// Copyright (C) 2024 Your Name or Company
package com.music.sale.adapter.persistence.cart.mapper

import com.music.sale.adapter.persistence.cart.entity.CartEntity
import com.music.sale.adapter.persistence.product.mapper.ProductPersistenceMapper
import com.music.sale.adapter.persistence.user.mapper.UserPersistenceMapper
import com.music.sale.application.cart.dto.CartOutput
import com.music.sale.domain.cart.Cart
import org.springframework.stereotype.Component

/**
 * 장바구니 영속성 매퍼
 */
@Component
class CartPersistenceMapper(
    private val userPersistenceMapper: UserPersistenceMapper,
    private val productPersistenceMapper: ProductPersistenceMapper,
) {
    
    fun toDomain(entity: CartEntity): Cart {
        return Cart(
            id = entity.id,
            user = userPersistenceMapper.toDomain(entity.user),
            product = productPersistenceMapper.toDomain(entity.product),
            quantity = entity.quantity,
            createdAt = entity.createdAt,
            updatedAt = entity.updatedAt,
        )
    }
    
    fun toEntity(cart: Cart): CartEntity {
        return CartEntity(
            id = cart.id,
            user = userPersistenceMapper.toEntity(cart.user),
            product = productPersistenceMapper.toEntity(
                cart.product,
                // 임시로 ProductCatalogEntity 생성 (실제로는 product에서 가져와야 함)
                com.music.sale.adapter.persistence.product.entity.ProductCatalogEntity(
                    id = cart.product.catalogId,
                    name = cart.product.name(),
                    category = com.music.sale.adapter.persistence.category.entity.CategoryEntity(
                        id = cart.product.category.id,
                        name = cart.product.category.name,
                        type = cart.product.category.type,
                        parent = null,
                        path = cart.product.category.path,
                        depth = cart.product.category.depth,
                        isActive = cart.product.category.isActive
                    ),
                    attributes = cart.product.attributes()
                )
            ),
            quantity = cart.quantity,
        )
    }
    
    fun toOutput(entity: CartEntity): CartOutput {
        return CartOutput(
            id = entity.id!!,
            userId = entity.user.id!!,
            product = productPersistenceMapper.toOutput(entity.product),
            quantity = entity.quantity,
            totalPrice = entity.product.price * entity.quantity,
            createdAt = entity.createdAt,
            updatedAt = entity.updatedAt,
        )
    }
} 
