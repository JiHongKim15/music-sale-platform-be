// Copyright (C) 2024 Your Name or Company
package com.music.sale.application.wishlist.port.outport

import com.music.sale.application.wishlist.dto.WishlistOutput
import com.music.sale.common.Pageable
import org.springframework.data.domain.Page

/**
 * 찜 기능 Port 인터페이스
 */
interface WishlistPort {
    /**
     * 사용자의 찜 목록 조회
     */
    fun findByUserId(
        userId: Long,
        pageable: Pageable,
    ): Page<WishlistOutput>

    /**
     * 찜 목록 저장
     */
    fun save(
        userId: Long,
        productId: Long,
    ): WishlistOutput

    /**
     * 찜 목록에서 제거
     */
    fun deleteByUserIdAndProductId(
        userId: Long,
        productId: Long,
    )

    /**
     * 사용자와 상품으로 찜 목록 조회
     */
    fun findByUserIdAndProductId(
        userId: Long,
        productId: Long,
    ): WishlistOutput?

    /**
     * 상품이 찜 목록에 있는지 확인
     */
    fun existsByUserIdAndProductId(
        userId: Long,
        productId: Long,
    ): Boolean
} 
