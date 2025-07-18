// Copyright (C) 2024 Your Name or Company
package com.music.sale.application.cart.port.outport

import com.music.sale.application.cart.dto.CartOutput
import com.music.sale.common.Pageable
import com.music.sale.domain.cart.Cart
import org.springframework.data.domain.Page

/**
 * 장바구니 Port 인터페이스
 */
interface CartPort {
    /**
     * 사용자의 장바구니 조회
     */
    fun findByUserId(
        userId: Long,
        pageable: Pageable,
    ): Page<CartOutput>

    /**
     * 장바구니 저장
     */
    fun save(cart: Cart): CartOutput

    /**
     * 장바구니에서 제거
     */
    fun deleteByUserIdAndProductId(
        userId: Long,
        productId: Long,
    )

    /**
     * 사용자와 상품으로 장바구니 조회
     */
    fun findByUserIdAndProductId(
        userId: Long,
        productId: Long,
    ): CartOutput?

    /**
     * 상품이 장바구니에 있는지 확인
     */
    fun existsByUserIdAndProductId(
        userId: Long,
        productId: Long,
    ): Boolean

    /**
     * 사용자의 장바구니 모두 삭제
     */
    fun deleteByUserId(userId: Long)

    /**
     * 사용자의 장바구니 총 금액 계산
     */
    fun getTotalAmountByUserId(userId: Long): Int
} 
