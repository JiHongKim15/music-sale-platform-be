package com.music.sale.adapter.persistence.product.entity

import com.music.sale.adapter.persistence.category.entity.CategoryEntity
import com.music.sale.adapter.persistence.common.BaseEntity
import com.music.sale.adapter.persistence.common.JsonConverter
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    val category: CategoryEntity,

    @Column(name = "attributes", nullable = false, columnDefinition = "JSON")
    @Convert(converter = JsonConverter::class)
    val attributes: Map<String, Any>? = null
) : BaseEntity() {
    //ID만 가진 빈 객체 생성
    constructor(id: Long?) : this(id, "", CategoryEntity(), null)

}