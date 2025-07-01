// Copyright (C) 2024 Your Name or Company
package com.music.sale.domain.viewcount

import java.time.LocalDateTime

/**
 * 상품 조회수 도메인 모델
 */
data class ProductViewCount(
    val id: Long? = null,
    val productId: Long,
    val currentViewers: Int = 0,
    val totalViews: Int = 0,
    val lastUpdated: LocalDateTime = LocalDateTime.now(),
) {
    init {
        require(productId > 0) { "상품 ID는 0보다 커야 합니다" }
        require(currentViewers >= 0) { "현재 조회자는 0명 이상이어야 합니다" }
        require(totalViews >= 0) { "총 조회수는 0 이상이어야 합니다" }
    }

    fun incrementViewer(): ProductViewCount {
        return copy(
            currentViewers = currentViewers + 1,
            totalViews = totalViews + 1,
            lastUpdated = LocalDateTime.now()
        )
    }

    fun decrementViewer(): ProductViewCount {
        return copy(
            currentViewers = maxOf(0, currentViewers - 1),
            lastUpdated = LocalDateTime.now()
        )
    }

    fun addView(): ProductViewCount {
        return copy(
            totalViews = totalViews + 1,
            lastUpdated = LocalDateTime.now()
        )
    }
} 
