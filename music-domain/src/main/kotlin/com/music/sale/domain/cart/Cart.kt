// Copyright (C) 2024 Your Name or Company
package com.music.sale.domain.cart

import com.music.sale.domain.product.Product
import com.music.sale.domain.user.User
import java.time.LocalDateTime

/**
 * 장바구니 도메인 모델
 */
data class Cart(
    val id: Long? = null,
    val user: User,
    val product: Product,
    val quantity: Int,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val updatedAt: LocalDateTime = LocalDateTime.now(),
) {
    init {
        require(user.id != null) { "사용자 ID는 필수입니다" }
        require(product.id != null) { "상품 ID는 필수입니다" }
        require(quantity > 0) { "수량은 0보다 커야 합니다" }
        require(quantity <= product.stockQuantity) { "재고 수량을 초과할 수 없습니다" }
    }

    companion object {
        fun create(
            user: User,
            product: Product,
            quantity: Int,
        ): Cart {
            return Cart(
                user = user,
                product = product,
                quantity = quantity,
            )
        }
    }

    fun updateQuantity(newQuantity: Int): Cart {
        require(newQuantity > 0) { "수량은 0보다 커야 합니다" }
        require(newQuantity <= product.stockQuantity) { "재고 수량을 초과할 수 없습니다" }

        return copy(
            quantity = newQuantity,
            updatedAt = LocalDateTime.now(),
        )
    }

    fun getTotalPrice(): Int {
        return product.price * quantity
    }
} 
