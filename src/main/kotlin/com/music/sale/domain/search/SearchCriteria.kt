package com.music.sale.domain.search

import com.music.sale.domain.product.ProductItem
import java.math.BigDecimal

data class SearchCriteria(
    val keyword: String? = null,
    val category: ProductItem.Category? = null,
    val minPrice: BigDecimal? = null,
    val maxPrice: BigDecimal? = null,
    val condition: ProductItem.ProductCondition? = null,
    val location: String? = null,
    val page: Int = 0,
    val size: Int = 20
)

data class SearchResult<T>(
    val content: List<T>,
    val totalElements: Long,
    val totalPages: Int,
    val currentPage: Int,
    val size: Int
) 