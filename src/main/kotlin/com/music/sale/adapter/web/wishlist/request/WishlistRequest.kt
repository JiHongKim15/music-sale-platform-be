// Copyright (C) 2024 Your Name or Company
package com.music.sale.adapter.web.wishlist.request

import com.music.sale.common.DefaultPageable
import com.music.sale.common.Pageable

/**
 * 찜 목록 조회 요청
 */
data class GetWishlistRequest(
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
 * 찜 추가 요청
 */
data class AddToWishlistRequest(
    val productId: Long,
)

/**
 * 찜 제거 요청
 */
data class RemoveFromWishlistRequest(
    val productId: Long,
) 
