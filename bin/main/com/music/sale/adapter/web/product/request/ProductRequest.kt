package com.music.sale.adapter.web.product.request

import com.music.sale.domain.product.enum.ProductCondition
import com.music.sale.domain.product.enum.ProductConditionGrade
import com.music.sale.domain.product.enum.ProductStatus

data class CreateProductRequest(
    val name: String,
    val categoryId: Long,
    val price: Int,
    val sellerId: Long,
    val storeId: Long,
    val condition: ProductCondition,
    val conditionGrade: ProductConditionGrade,
    val stockQuantity: Int,
    val status: ProductStatus,
    val attributes: Map<String, Any>
)

data class UpdateProductRequest(
    val name: String,
    val categoryId: Long,
    val price: Int,
    val sellerId: Long,
    val storeId: Long,
    val condition: ProductCondition,
    val conditionGrade: ProductConditionGrade,
    val stockQuantity: Int,
    val status: ProductStatus,
    val attributes: Map<String, Any>
)

data class SearchProductRequest(
    val keyword: String?,               // "펜더", "텔레캐스터"
    val keywordType: SearchProductKeywordType?,
    val categoryId: Long,     // ProductCategory.GUITAR
    val location: String?,              // "서울"
    val condition: ProductCondition?,         // true: 새 제품, false: 중고
    val conditionGrade: ProductConditionGrade?,  // S, A, B
    val minPrice: Int?,                 // 최소 가격 필터
    val maxPrice: Int?,                 // 최대 가격 필터
    val status: ProductStatus?,
)

enum class SearchProductKeywordType {
    PRODUCT_NAME,               // 제품 이름
    SELLER_NAME,        // 판매자 닉네임
    STORE_NAME,         // 매장 이름
    ATTRIBUTE,          // 속성(브랜드, 재질 등)
}