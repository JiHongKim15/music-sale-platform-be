// Copyright (C) 2024 Your Name or Company
package com.music.sale.application.product.dto

import com.music.sale.domain.product.enum.ProductCondition
import com.music.sale.domain.product.enum.ProductConditionGrade
import com.music.sale.domain.product.enum.ProductStatus
import com.music.sale.domain.product.enum.SearchProductKeywordType
import com.music.sale.domain.store.Store
import com.music.sale.domain.user.User

data class SearchProductCondition(
    val keyword: String? = null,
    val keywordType: SearchProductKeywordType? = null,
    val categoryId: Long? = null,
    val location: String? = null,
    val condition: ProductCondition? = null,
    val conditionGrade: ProductConditionGrade? = null,
    val minPrice: Int? = null,
    val maxPrice: Int? = null,
    val status: ProductStatus? = null,
)

data class SaveProductItemCondition(
    val id: Long? = null,
    val catalogId: Long,
    val price: Int,
    val seller: User?,
    val store: Store?,
    val condition: ProductCondition,
    val conditionGrade: ProductConditionGrade? = null,
    val stockQuantity: Int,
    val status: ProductStatus,
    val name: String? = null,
    val attributes: Map<String, Any>,
)
