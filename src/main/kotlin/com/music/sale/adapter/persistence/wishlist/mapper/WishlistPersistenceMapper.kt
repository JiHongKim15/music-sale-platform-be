// Copyright (C) 2024 Your Name or Company
package com.music.sale.adapter.persistence.wishlist.mapper

import com.music.sale.adapter.persistence.product.mapper.ProductPersistenceMapper
import com.music.sale.adapter.persistence.user.mapper.UserPersistenceMapper
import com.music.sale.adapter.persistence.wishlist.entity.WishlistEntity
import com.music.sale.application.wishlist.dto.WishlistOutput
import com.music.sale.domain.wishlist.Wishlist
import org.springframework.stereotype.Component

/**
 * 찜 목록 영속성 매퍼
 */
@Component
class WishlistPersistenceMapper(
    private val userPersistenceMapper: UserPersistenceMapper,
    private val productPersistenceMapper: ProductPersistenceMapper,
) {
    
    fun toDomain(entity: WishlistEntity): Wishlist {
        return Wishlist(
            id = entity.id,
            user = userPersistenceMapper.toDomain(entity.user),
            product = productPersistenceMapper.toDomain(entity.product),
            createdAt = entity.createdAt,
        )
    }
    
    fun toOutput(entity: WishlistEntity): WishlistOutput {
        return WishlistOutput(
            id = entity.id!!,
            userId = entity.user.id!!,
            product = productPersistenceMapper.toOutput(entity.product),
            createdAt = entity.createdAt,
        )
    }
} 
