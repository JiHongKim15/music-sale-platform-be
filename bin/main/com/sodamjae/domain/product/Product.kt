package com.sodamjae.domain.product

import com.sodamjae.domain.product.enum.ProductCondition
import com.sodamjae.domain.product.enum.ProductStatus
import com.sodamjae.domain.shipping.model.ShippingInfo
import java.math.BigDecimal

/**
 * 통합된 상품 도메인 모델
 * 내부적으로는 카탈로그 정보와 아이템 정보를 모두 포함하지만
 * 사용자에게는 하나의 상품으로 표현됩니다.
 */
class Product private constructor(
    // 제품 ID (아이템 ID)
    val id: Long?,

    // 카탈로그 정보
    private val catalogId: Long?,
    private val name: ProductName,
    private val productType: String,
    private val attributes: Map<String, Any>,

    // 아이템 정보 
    private val sellerId: Long,
    private val storeId: Long,
    private val condition: ProductCondition,
    private val stockQuantity: Int,

    // 상태 정보
    private val status: ProductStatus,

    // 배송 정보
    private val shippingInfo: ShippingInfo
) {
    companion object {
        // 팩토리 메소드 - 새 상품 생성
        fun create(
            name: String,
            productType: String,
            attributes: Map<String, Any>,
            sellerId: Long,
            storeId: Long,
            condition: ProductCondition,
            stockQuantity: Int,
            shippingInfo: ShippingInfo
        ): Product {
            return Product(
                id = null,
                catalogId = null,
                name = ProductName(name),
                productType = productType,
                attributes = attributes,
                sellerId = sellerId,
                storeId = storeId,
                condition = condition,
                stockQuantity = stockQuantity,
                status = ProductStatus.DRAFT,
                shippingInfo = shippingInfo
            )
        }

        // 팩토리 메소드 - 기존 상품 재구성
        fun reconstitute(
            id: Long,
            catalogId: Long,
            name: String,
            productType: String,
            attributes: Map<String, Any>,
            sellerId: Long,
            storeId: Long,
            condition: ProductCondition,
            stockQuantity: Int,
            status: ProductStatus,
            shippingInfo: ShippingInfo
        ): Product {
            return Product(
                id = id,
                catalogId = catalogId,
                name = ProductName(name),
                productType = productType,
                attributes = attributes,
                sellerId = sellerId,
                storeId = storeId,
                condition = condition,
                stockQuantity = stockQuantity,
                status = status,
                shippingInfo = shippingInfo
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

    // 접근자 메소드
    fun getId(): Long? = id
    fun getCatalogId(): Long? = catalogId
    fun getName(): ProductName = name
    fun getProductType(): String = productType
    fun getAttributes(): Map<String, Any> = attributes
    fun getSellerId(): Long = sellerId
    fun getStoreId(): Long = storeId
    fun getCondition(): ProductCondition = condition
    fun getStockQuantity(): Int = stockQuantity
    fun getStatus(): ProductStatus = status
    fun getShippingInfo(): ShippingInfo = shippingInfo

    // 편의 메소드 - 속성 접근을 위한 헬퍼 함수들
    fun getPrice(): BigDecimal? = attributes["price"] as? BigDecimal
    fun getDescription(): String? = attributes["description"] as? String
    fun getFeatures(): List<String> = (attributes["features"] as? List<*>)?.filterIsInstance<String>() ?: emptyList()
    fun getSpecifications(): Map<String, String> {
        val specs = attributes["specifications"] as? Map<*, *> ?: return emptyMap()
        return specs.entries.associate {
            it.key.toString() to (it.value?.toString() ?: "")
        }
    }

    fun getImageUrls(): List<String> = (attributes["imageUrls"] as? List<*>)?.filterIsInstance<String>() ?: emptyList()

    // 비즈니스 로직 메소드
    fun activate(): Product {
        require(status != ProductStatus.ACTIVE) { "이미 판매 중인 상품입니다." }
        return copy(status = ProductStatus.ACTIVE)
    }

    fun markAsSoldOut(): Product {
        require(status == ProductStatus.ACTIVE) { "판매 중인 상품만 품절 처리할 수 있습니다." }
        return copy(status = ProductStatus.SOLD_OUT)
    }

    fun deactivate(): Product {
        require(status != ProductStatus.INACTIVE) { "이미 판매 중지된 상품입니다." }
        return copy(status = ProductStatus.INACTIVE)
    }

    fun updatePrice(newPrice: BigDecimal): Product {
        val updatedAttributes = attributes.toMutableMap()
        updatedAttributes["price"] = newPrice
        return copy(attributes = updatedAttributes)
    }

    fun updateStockQuantity(quantity: Int): Product {
        require(quantity >= 0) { "재고 수량은 0 이상이어야 합니다." }
        return copy(stockQuantity = quantity)
    }

    fun updateShippingInfo(newShippingInfo: ShippingInfo): Product {
        return copy(shippingInfo = newShippingInfo)
    }

    // 상품 속성 업데이트
    fun updateAttributes(newAttributes: Map<String, Any>): Product {
        return copy(attributes = newAttributes)
    }

    // 특정 속성 업데이트
    fun updateAttribute(key: String, value: Any): Product {
        val updatedAttributes = attributes.toMutableMap()
        updatedAttributes[key] = value
        return copy(attributes = updatedAttributes)
    }

    // 내부용 복사 함수
    private fun copy(
        id: Long? = this.id,
        catalogId: Long? = this.catalogId,
        name: ProductName = this.name,
        productType: String = this.productType,
        attributes: Map<String, Any> = this.attributes,
        sellerId: Long = this.sellerId,
        storeId: Long = this.storeId,
        condition: ProductCondition = this.condition,
        stockQuantity: Int = this.stockQuantity,
        status: ProductStatus = this.status,
        shippingInfo: ShippingInfo = this.shippingInfo
    ): Product {
        return Product(
            id = id,
            catalogId = catalogId,
            name = name,
            productType = productType,
            attributes = attributes,
            sellerId = sellerId,
            storeId = storeId,
            condition = condition,
            stockQuantity = stockQuantity,
            status = status,
            shippingInfo = shippingInfo
        )
    }
} 