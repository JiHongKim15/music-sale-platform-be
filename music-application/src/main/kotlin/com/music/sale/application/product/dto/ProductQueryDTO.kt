// Copyright (C) 2024 Your Name or Company
package com.music.sale.application.product.dto

import com.music.sale.domain.category.Category
import com.music.sale.domain.product.ProductImage
import com.music.sale.domain.product.enum.ProductCondition
import com.music.sale.domain.product.enum.ProductConditionGrade
import com.music.sale.domain.product.enum.ProductStatus
import com.music.sale.domain.product.enum.SearchProductKeywordType
import com.music.sale.domain.store.Store
import com.music.sale.domain.user.User
import lombok.NoArgsConstructor

data class ProductOutput(
    val id: Long,
    val name: String,
    val catalog: ProductCatalog,
    val price: Int,
    val seller: User?,
    val store: Store?,
    val condition: ProductCondition,
    val conditionGrade: ProductConditionGrade?,
    val stockQuantity: Int,
    val status: ProductStatus,
    val attributes: Map<String, Any>?,
    val images: List<ProductImage>?
)

data class ProductCatalog(
    val id: Long,
    val name: String,
    val categories: List<Category>,
    val brand: String,
    val attribute: Map<String, String>,
)

data class CreateProductInput(
    val name: String,
    val catalogId: Long,
    val price: Int,
    val sellerId: Long,
    val storeId: Long,
    val condition: ProductCondition,
    val conditionGrade: ProductConditionGrade,
    val stockQuantity: Int,
    val status: ProductStatus,
    val attributes: Map<String, Any>,
)

data class UpdateProductInput(
    val id: Long,
    val name: String? = null,
    val catalogId: Long? = null,
    val price: Int? = null,
    val sellerId: Long? = null,
    val storeId: Long? = null,
    val condition: ProductCondition? = null,
    val conditionGrade: ProductConditionGrade? = null,
    val stockQuantity: Int? = null,
    val status: ProductStatus? = null,
    val attributes: Map<String, Any>? = null,
)

@NoArgsConstructor
data class SearchProductInput(
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
