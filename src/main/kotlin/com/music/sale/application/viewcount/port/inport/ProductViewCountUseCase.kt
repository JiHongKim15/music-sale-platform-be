// Copyright (C) 2024 Your Name or Company
package com.music.sale.application.viewcount.port.inport

import com.music.sale.application.viewcount.dto.ProductViewCountOutput

/**
 * 상품 조회수 UseCase 인터페이스
 */
interface ProductViewCountUseCase {
    /**
     * 상품 조회수 조회
     */
    fun getProductViewCount(productId: Long): ProductViewCountOutput

    /**
     * 현재 조회자 수 증가 (페이지 진입)
     */
    fun incrementViewer(productId: Long): ProductViewCountOutput

    /**
     * 현재 조회자 수 감소 (페이지 이탈)
     */
    fun decrementViewer(productId: Long): ProductViewCountOutput

    /**
     * 총 조회수 증가 (페이지 조회)
     */
    fun addView(productId: Long): ProductViewCountOutput
} 
