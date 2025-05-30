package com.music.sale.domain.product

import com.music.sale.domain.product.enum.ProductCondition
import com.music.sale.domain.product.enum.ProductStatus

/**
 * 통합된 상품 도메인 모델
 * 내부적으로는 카탈로그 정보와 아이템 정보를 모두 포함하지만
 * 사용자에게는 하나의 상품으로 표현됩니다.
 */
class Product private constructor(
    val id: Long,
    private val price: Int,
    private val sellerId: Long,
    private val storeId: Long?,
    private val condition: ProductCondition,
    private val stockQuantity: Int,
    private val attributes: Map<String, Any>,
    private val status: ProductStatus,
    private val catalogInfo: ProductCatalog,
) {
    companion object {
        // 팩토리 메소드 - 상품 생성
        fun create(
            price: Int,
            sellerId: Long,
            storeId: Long,
            condition: ProductCondition,
            stockQuantity: Int,
            attributes: Map<String, Any>,
            status: ProductStatus,
            catalogInfo: ProductCatalog
        ): Product {
            return Product(
                id = 0,
                price = price,
                sellerId = sellerId,
                storeId = storeId,
                condition = condition,
                stockQuantity = stockQuantity,
                attributes = attributes,
                status = status,
                catalogInfo = catalogInfo
            )
        }
    }

    // Value Objects
    data class ProductName(val value: String) {
        init {
            require(value.isNotBlank()) { "상품명은 비어있을 수 없습니다." }
            require(value.length <= 100) { "상품명은 100자를 초과할 수 없습니다." }
        }
    }

} 