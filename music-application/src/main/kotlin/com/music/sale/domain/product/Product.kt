// Copyright (C) 2024 Your Name or Company
package com.music.sale.domain.product

import com.music.sale.domain.product.enum.ProductCondition
import com.music.sale.domain.product.enum.ProductConditionGrade
import com.music.sale.domain.product.enum.ProductStatus
import com.music.sale.domain.store.Store
import com.music.sale.domain.user.User

/**
 * 실제 판매되는 상품 인스턴스 도메인 모델
 * 제품 카탈로그 정보와 아이템별 속성, 그리고 고유 이미지를 포함
 */
data class Product(
    val id: Long, // ProductItem의 ID
    val catalog: ProductCatalog, // ProductCatalog 도메인 객체 참조
    val price: Int,
    val seller: User?,
    val store: Store?,
    val condition: ProductCondition,
    val conditionGrade: ProductConditionGrade?,
    val stockQuantity: Int,
    val status: ProductStatus,
    val customName: String? = null,
    val customAttributes: Map<String, Any>? = null,
    val images: MutableList<ProductImage> = mutableListOf() // 이미지는 Product 인스턴스에 직접 속함
) {
    // 커스터마이징되지 않았다면 카탈로그의 이름과 속성을 사용
    fun name(): String {
        return customName ?: catalog.name
    }

    fun attributes(): Map<String, Any>? {
        return customAttributes ?: catalog.attributes
    }

    fun isCustomName(): Boolean {
        return customName != null
    }

    fun isCustomAttributes(): Boolean {
        return customAttributes != null
    }
}
