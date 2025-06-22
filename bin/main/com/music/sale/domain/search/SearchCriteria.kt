// Copyright (C) 2024 Your Name or Company
package com.music.sale.domain.search

import com.music.sale.domain.product.enum.ProductCondition
import java.math.BigDecimal

data class SearchCriteria(
    val keyword: String? = null,
    val category: String? = null,
    val minPrice: BigDecimal? = null,
    val maxPrice: BigDecimal? = null,
    val condition: ProductCondition? = null,
    val location: String? = null,
    val page: Int = 0,
    val size: Int = 20,
)

data class SearchResult<T>(
    val content: List<T>,
    val totalElements: Long,
    val totalPages: Int,
    val currentPage: Int,
    val size: Int,
)
