package com.music.sale.persistence.product.dto

import com.music.sale.domain.category.CategoryType
import java.time.LocalDateTime

data class ProductCatalogQueryResult(
    val id: Long,
    val name: String,
    val categoryId: Long,
    val categoryName: String,
    val categoryType: CategoryType,
    val categoryPath: String,
    val categoryDepth: Int,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,
) 
