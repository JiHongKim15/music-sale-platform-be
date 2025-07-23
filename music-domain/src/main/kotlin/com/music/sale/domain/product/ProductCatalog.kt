package com.music.sale.domain.product

import com.music.sale.domain.category.Category

/**
 * 제품의 공통적인 정의 (카탈로그) 도메인 모델
 * 제품의 기본 정보와 특성을 포함 (이미지 제외)
 */
data class ProductCatalog(
    val id: Long,
    val name: String,
    val category: Category,
    val brand: String? = null,
    val attributes: Map<String, Any>? = null,
)
