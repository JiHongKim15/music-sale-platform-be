// Copyright (C) 2024 Your Name or Company
package com.music.sale.domain.wishlist

import com.music.sale.domain.product.Product
import com.music.sale.domain.user.User
import java.time.LocalDateTime

/**
 * 찜 목록 도메인 모델
 */
data class Wishlist(
    val id: Long? = null,
    val user: User,
    val product: Product,
    val createdAt: LocalDateTime = LocalDateTime.now(),
) {
    init {
        require(user.id != null) { "사용자 ID는 필수입니다" }
        require(product.id != null) { "상품 ID는 필수입니다" }
    }

    companion object {
        fun create(user: User, product: Product): Wishlist {
            return Wishlist(
                user = user,
                product = product
            )
        }
    }
} 
