package com.music.sale.adapter.persistence.product.entity

import com.fasterxml.jackson.databind.ObjectMapper
import com.music.sale.adapter.persistence.common.BaseEntity
import com.music.sale.domain.category.Category
import com.music.sale.domain.category.CategoryType
import com.music.sale.domain.product.Product
import com.music.sale.domain.product.enum.ProductCondition
import jakarta.persistence.*

/**
 * 제품 카탈로그 JPA 엔티티
 * 제품의 기본 정보와 특성을 저장
 */
@Entity
@Table(name = "product_catalog")
class ProductCatalogEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(nullable = false)
    val name: String,

    @Column(name = "product_type_id", nullable = false)
    val productTypeId: Long,

    @Column(nullable = false, columnDefinition = "JSON")
    val attributes: String // JSON 형태로 저장된 속성
) : BaseEntity() {
    fun toDomain(): Product {
        val objectMapper = ObjectMapper()
        val attributesMap = objectMapper.readValue(attributes, Map::class.java) as Map<String, Any>
        
        return Product.create(
            name = name,
            category = Category(
                id = 0L,
                name = "Default Category",
                type = CategoryType.PRODUCT,
                parent = null,
                path = "/0",
                depth = 0
            ),
            price = 0,
            sellerId = 0L,
            storeId = 0L,
            condition = ProductCondition.NEW,
            conditionGrade = null,
            stockQuantity = 0,
            attributes = attributesMap
        )
    }

    companion object {
        fun fromDomain(product: Product): ProductCatalogEntity {
            val objectMapper = ObjectMapper()
            val attributesJson = objectMapper.writeValueAsString(product.attributes)
            
            return ProductCatalogEntity(
                id = product.id,
                name = product.name,
                productTypeId = 0L, // TODO: 제품 타입 ID 설정
                attributes = attributesJson
            )
        }
    }
} 