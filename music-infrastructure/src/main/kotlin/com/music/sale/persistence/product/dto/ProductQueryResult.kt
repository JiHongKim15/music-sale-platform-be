package com.music.sale.persistence.product.dto

import com.music.sale.domain.category.CategoryType
import com.music.sale.domain.product.enum.ProductCondition
import com.music.sale.domain.product.enum.ProductConditionGrade
import com.music.sale.domain.product.enum.ProductStatus
import java.time.LocalDateTime

data class ProductQueryResult(
    val id: Long,
    val name: String,
    val price: Int,
    val stockQuantity: Int,
    val condition: ProductCondition,
    val conditionGrade: ProductConditionGrade,
    val status: ProductStatus,
    val catalogId: Long,
    val catalogName: String,
    val categoryId: Long,
    val categoryName: String,
    val categoryType: CategoryType,
    val categoryPath: String,
    val categoryDepth: Int,
    val sellerId: Long,
    val sellerName: String,
    val storeId: Long,
    val storeName: String,
    val customName: String?,
    val customAttributes: Map<String, Any>?,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,
) 
