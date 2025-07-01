// Copyright (C) 2024 Your Name or Company
package com.music.sale.application.viewcount.port.outport

import com.music.sale.application.viewcount.dto.ProductViewCountOutput
import com.music.sale.domain.viewcount.ProductViewCount

/**
 * 상품 조회수 Port 인터페이스
 */
interface ProductViewCountPort {
    /**
     * 상품 ID로 조회수 조회
     */
    fun findByProductId(productId: Long): ProductViewCountOutput?

    /**
     * 조회수 저장/업데이트
     */
    fun save(viewCount: ProductViewCount): ProductViewCountOutput
} 
