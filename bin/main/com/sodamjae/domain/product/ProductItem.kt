package com.sodamjae.domain.product

import com.sodamjae.domain.product.enum.ProductCondition

/**
 * 제품 아이템 도메인 모델
 * 실제 판매되는 상품 인스턴스에 대한 정보만 포함
 *
 * 참고: 이 클래스는 영속성 계층과의 매핑을 위해 존재하며,
 * 실제 비즈니스 로직은 통합된 Product 도메인 모델에서 처리합니다.
 */
class ProductItem private constructor(
    val id: Long?,
    private val productCatalogId: Long,
    private val sellerId: Long,
    private val storeId: Long,
    private val condition: ProductCondition,
    private val stockQuantity: Int,
    private val attributes: Map<String, Any>
) {
    companion object {
        // 팩토리 메소드 - 새 아이템 생성
        fun create(
            productCatalogId: Long,
            sellerId: Long,
            storeId: Long,
            condition: ProductCondition,
            stockQuantity: Int,
            attributes: Map<String, Any>
        ): ProductItem {
            return ProductItem(
                id = null,
                productCatalogId = productCatalogId,
                sellerId = sellerId,
                storeId = storeId,
                condition = condition,
                stockQuantity = stockQuantity,
                attributes = attributes
            )
        }

        // 팩토리 메소드 - 기존 아이템 재구성
        fun reconstitute(
            id: Long,
            productCatalogId: Long,
            sellerId: Long,
            storeId: Long,
            condition: ProductCondition,
            stockQuantity: Int,
            attributes: Map<String, Any>
        ): ProductItem {
            return ProductItem(
                id = id,
                productCatalogId = productCatalogId,
                sellerId = sellerId,
                storeId = storeId,
                condition = condition,
                stockQuantity = stockQuantity,
                attributes = attributes
            )
        }
    }

    // 접근자 메소드
    fun getId(): Long? = id
    fun getProductCatalogId(): Long = productCatalogId
    fun getSellerId(): Long = sellerId
    fun getStoreId(): Long = storeId
    fun getCondition(): ProductCondition = condition
    fun getStockQuantity(): Int = stockQuantity
    fun getAttributes(): Map<String, Any> = attributes
} 