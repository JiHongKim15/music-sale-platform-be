// Copyright (C) 2024 Your Name or Company
package com.music.sale.application.cart.port.inport

import com.music.sale.application.cart.dto.CartOutput
import com.music.sale.common.Pageable
import org.springframework.data.domain.Page

/**
 * 장바구니 UseCase 인터페이스
 */
interface CartUseCase {
    /**
     * 사용자의 장바구니 조회
     */
    fun getUserCart(
        userId: Long,
        pageable: Pageable,
    ): Page<CartOutput>

    /**
     * 장바구니에 상품 추가
     */
    fun addToCart(
        userId: Long,
        productId: Long,
        quantity: Int,
    ): CartOutput

    /**
     * 장바구니 상품 수량 변경
     */
    fun updateCartQuantity(
        userId: Long,
        productId: Long,
        quantity: Int,
    ): CartOutput

    /**
     * 장바구니에서 상품 제거
     */
    fun removeFromCart(
        userId: Long,
        productId: Long,
    )

    /**
     * 장바구니 비우기
     */
    fun clearCart(userId: Long)

    /**
     * 장바구니 총 금액 계산
     */
    fun getCartTotalAmount(userId: Long): Int
} 
