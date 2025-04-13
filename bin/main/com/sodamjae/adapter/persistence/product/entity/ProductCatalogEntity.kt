package com.sodamjae.adapter.persistence.product.entity

import com.sodamjae.adapter.persistence.common.BaseEntity
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

    @Column(name = "product_type", nullable = false)
    val productType: String,
    
    @Column(nullable = false, columnDefinition = "JSON")
    val attributes: String // JSON 형태로 저장된 속성
) : BaseEntity() 