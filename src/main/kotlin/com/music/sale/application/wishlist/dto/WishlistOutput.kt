// Copyright (C) 2024 Your Name or Company
package com.music.sale.application.wishlist.dto

import com.music.sale.application.product.dto.ProductOutput
import java.time.LocalDateTime

/**
 * 찜 목록 출력 DTO
 */
data class WishlistOutput(
    val id: Long,
    val userId: Long,
    val product: ProductOutput,
    val createdAt: LocalDateTime,
) 
