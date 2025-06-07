package com.music.sale.application.product.dto

import com.music.sale.domain.product.enum.ProductCondition
import com.music.sale.domain.product.enum.ProductConditionGrade
import com.music.sale.domain.store.model.Store
import com.music.sale.domain.user.User

// UseCase의 출력을 나타내는 DTO
data class ProductOutput(
    val id: Long,
    val name: String,
    val category: String,
    val price: Int,
    val seller: User,
    val store: Store,
    val condition: ProductCondition,
    val conditionGrade: ProductConditionGrade,
    val stockQuantity: Int,
    val attributes: Map<String, Any>
)

// 상품 생성 입력 DTO
data class CreateProductInput(
    val name: String,
    val description: String,
    val price: Long,
    val stock: Int
)

// 상품 수정 입력 DTO
data class UpdateProductInput(
    val id: Long,
    val name: String,
    val description: String,
    val price: Long,
    val stock: Int
)

// 상품 검색 입력 DTO
data class SearchProductInput(
    val productType: String? = null,
    val keyword: String? = null,
    val sellerId: Long? = null,
    val condition: ProductCondition? = null,
    val inStock: Boolean? = null
) 