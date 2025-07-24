package com.music.sale.web.product.request

import com.music.sale.common.DefaultPageable

data class SearchProductRequest(
    val keyword: String? = null,
    val minPrice: Long? = null,
    val maxPrice: Long? = null,
    val category: String? = null,
    val brand: String? = null,
    val onSale: Boolean? = null,
    val page: Int = 1,
    val size: Int = 10,
) {
    fun toPageable(): DefaultPageable {
        return DefaultPageable(page, size)
    }
}
