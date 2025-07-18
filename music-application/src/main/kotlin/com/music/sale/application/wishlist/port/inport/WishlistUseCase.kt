// Copyright (C) 2024 Your Name or Company
package com.music.sale.application.wishlist.port.inport

import com.music.sale.application.wishlist.dto.WishlistOutput
import com.music.sale.common.Pageable
import org.springframework.data.domain.Page

/**
 * 찜 기능 UseCase 인터페이스
 */
interface WishlistUseCase {
    /**
     * 사용자의 찜 목록 조회
     */
    fun getUserWishlist(
        userId: Long,
        pageable: Pageable,
    ): Page<WishlistOutput>

    /**
     * 상품 찜하기
     */
    fun addToWishlist(
        userId: Long,
        productId: Long,
    ): WishlistOutput

    /**
     * 찜 목록에서 제거
     */
    fun removeFromWishlist(
        userId: Long,
        productId: Long,
    )

    /**
     * 상품이 찜 목록에 있는지 확인
     */
    fun isProductInWishlist(
        userId: Long,
        productId: Long,
    ): Boolean
} 
