package com.sodamjae.domain.product

/**
 * 제품 카탈로그 도메인 모델
 * 제품의 기본 정보 및 특성에 대한 정보만 포함
 *
 * 참고: 이 클래스는 영속성 계층과의 매핑을 위해 존재하며,
 * 실제 비즈니스 로직은 통합된 Product 도메인 모델에서 처리합니다.
 */
class ProductCatalog private constructor(
    val id: Long?,
    private val name: String,
    private val productType: String,
    private val attributes: Map<String, Any>
) {
    companion object {
        // 팩토리 메소드 - 새 카탈로그 생성
        fun create(
            name: String,
            productType: String,
            attributes: Map<String, Any>
        ): ProductCatalog {
            return ProductCatalog(
                id = null,
                name = name,
                productType = productType,
                attributes = attributes
            )
        }

        // 팩토리 메소드 - 기존 카탈로그 재구성
        fun reconstitute(
            id: Long,
            name: String,
            productType: String,
            attributes: Map<String, Any>
        ): ProductCatalog {
            return ProductCatalog(
                id = id,
                name = name,
                productType = productType,
                attributes = attributes
            )
        }
    }

    // 접근자 메소드
    fun getId(): Long? = id
    fun getName(): String = name
    fun getProductType(): String = productType
    fun getAttributes(): Map<String, Any> = attributes
} 