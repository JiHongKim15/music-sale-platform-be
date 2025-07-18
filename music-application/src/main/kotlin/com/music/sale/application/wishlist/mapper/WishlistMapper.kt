// Copyright (C) 2024 Your Name or Company
package com.music.sale.application.wishlist.mapper

import com.music.sale.application.wishlist.dto.WishlistOutput
import com.music.sale.domain.wishlist.Wishlist
import org.springframework.stereotype.Component

/**
 * 찜 목록 매퍼
 */
@Component
class WishlistMapper {
    fun toOutput(wishlist: Wishlist): WishlistOutput {
        return WishlistOutput(
            id = wishlist.id!!,
            userId = wishlist.user.id!!,
            product = com.music.sale.application.product.mapper.ProductMapper().toOutput(wishlist.product),
            createdAt = wishlist.createdAt,
        )
    }
} 
