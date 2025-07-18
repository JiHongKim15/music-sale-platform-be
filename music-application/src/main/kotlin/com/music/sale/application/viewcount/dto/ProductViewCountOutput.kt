// Copyright (C) 2024 Your Name or Company
package com.music.sale.application.viewcount.dto

import java.time.LocalDateTime

/**
 * 상품 조회수 출력 DTO
 */
data class ProductViewCountOutput(
    val productId: Long,
    val currentViewers: Int,
    val totalViews: Int,
    val lastUpdated: LocalDateTime = LocalDateTime.now(),
) 
