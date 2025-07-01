// Copyright (C) 2024 Your Name or Company
package com.music.sale.adapter.web.cart.request

import com.music.sale.common.DefaultPageable
import com.music.sale.common.Pageable

/**
 * 장바구니 조회 요청
 */
data class GetCartRequest(
    val page: Int = 0,
    val size: Int = 10,
    val sort: String? = null,
    val direction: String? = null,
) {
    fun toPageable(): Pageable {
        return DefaultPageable(
            pageNumber = page,
            pageSize = size,
            sort = sort,
            sortDirection = direction?.let { com.music.sale.common.SortDirection.valueOf(it.uppercase()) }
        )
    }
}

/**
 * 장바구니 추가 요청
 */
data class AddToCartRequest(
    val productId: Long,
    val quantity: Int = 1,
)

/**
 * 장바구니 수량 변경 요청
 */
data class UpdateCartQuantityRequest(
    val productId: Long,
    val quantity: Int,
)

/**
 * 장바구니 제거 요청
 */
data class RemoveFromCartRequest(
    val productId: Long,
) 
