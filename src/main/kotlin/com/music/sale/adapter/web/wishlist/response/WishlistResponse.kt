// Copyright (C) 2024 Your Name or Company
package com.music.sale.adapter.web.wishlist.response

import com.music.sale.application.wishlist.dto.WishlistOutput
import java.time.LocalDateTime

/**
 * 찜 목록 응답
 */
data class WishlistResponse(
    val id: Long,
    val userId: Long,
    val product: ProductResponse,
    val createdAt: LocalDateTime,
) {
    companion object {
        fun from(wishlistOutput: WishlistOutput): WishlistResponse {
            return WishlistResponse(
                id = wishlistOutput.id,
                userId = wishlistOutput.userId,
                product = ProductResponse.from(wishlistOutput.product),
                createdAt = wishlistOutput.createdAt,
            )
        }
    }
}

/**
 * 상품 응답 (찜 목록용)
 */
data class ProductResponse(
    val id: Long,
    val name: String,
    val price: Int,
    val category: String,
    val condition: String,
    val status: String,
) {
    companion object {
        fun from(productOutput: com.music.sale.application.product.dto.ProductOutput): ProductResponse {
            return ProductResponse(
                id = productOutput.id,
                name = productOutput.name,
                price = productOutput.price,
                category = productOutput.catalog.category.name,
                condition = productOutput.condition.name,
                status = productOutput.status.name,
            )
        }
    }
} 
