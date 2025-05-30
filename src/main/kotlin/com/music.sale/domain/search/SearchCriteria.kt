package com.music.sale.domain.search

import com.music.sale.domain.product.Product
import java.math.BigDecimal

data class SearchCriteria(
    val keyword: String? = null,
    val category: Product.Category? = null,
    val minPrice: BigDecimal? = null,
    val maxPrice: BigDecimal? = null,
    val condition: Product.ProductCondition? = null,
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