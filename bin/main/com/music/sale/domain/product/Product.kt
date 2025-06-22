// Copyright (C) 2024 Your Name or Company
package com.music.sale.domain.product

import com.music.sale.domain.category.Category
import com.music.sale.domain.product.enum.ProductCondition
import com.music.sale.domain.product.enum.ProductConditionGrade
import com.music.sale.domain.product.enum.ProductStatus
import com.music.sale.domain.store.model.Store
import com.music.sale.domain.user.User

/**
 * 통합된 제품 도메인 모델
 * 제품의 모든 속성과 비즈니스 로직을 포함
 */
data class Product(
    val id: Long,
    // 카탈로그 속성
    val catalogId: Long,
    private val name: String,
    val category: Category,
    private val attributes: Map<String, Any>? = null,
    val price: Int,
    val seller: User?,
    val store: Store?,
    val condition: ProductCondition,
    val conditionGrade: ProductConditionGrade?,
    val stockQuantity: Int,
    val status: ProductStatus,
    private val customName: String? = null,
    private val customAttributes: Map<String, Any>? = null,
) {
    fun name(): String {
        return customName ?: name
    }

    fun attributes(): Map<String, Any>? {
        return customAttributes ?: attributes
    }

    fun isCustomName(): Boolean {
        return customName != null
    }

    fun isCustomAttributes(): Boolean {
        return customAttributes != null
    }
}
