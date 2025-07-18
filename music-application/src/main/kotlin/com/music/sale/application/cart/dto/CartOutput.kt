// Copyright (C) 2024 Your Name or Company
package com.music.sale.application.cart.dto

import com.music.sale.application.product.dto.ProductOutput
import java.time.LocalDateTime

/**
 * 장바구니 출력 DTO
 */
data class CartOutput(
    val id: Long,
    val userId: Long,
    val product: ProductOutput,
    val quantity: Int,
    val totalPrice: Int,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,
) 
