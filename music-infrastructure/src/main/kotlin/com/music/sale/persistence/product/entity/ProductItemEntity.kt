// Copyright (C) 2024 Your Name or Company
package com.music.sale.persistence.product.entity

import com.music.sale.domain.product.enum.ProductCondition
import com.music.sale.domain.product.enum.ProductConditionGrade
import com.music.sale.domain.product.enum.ProductStatus
import com.music.sale.persistence.common.BaseEntity
import com.music.sale.persistence.common.JsonConverter
import com.music.sale.persistence.store.entity.StoreEntity
import com.music.sale.persistence.user.entity.UserEntity
import jakarta.persistence.*

/** 제품 아이템 JPA 엔티티 실제 판매되는 상품 인스턴스를 표현 */
@Entity
@Table(name = "product_item")
class ProductItemEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long? = null,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "catalog_id", nullable = false, foreignKey = ForeignKey(name = "none", value = ConstraintMode.NO_CONSTRAINT))
    val catalog: ProductCatalogEntity,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seller_id", foreignKey = ForeignKey(name = "none", value = ConstraintMode.NO_CONSTRAINT))
    val seller: UserEntity? = null,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", foreignKey = ForeignKey(name = "none", value = ConstraintMode.NO_CONSTRAINT))
    val store: StoreEntity? = null,
    @Column(nullable = false) val price: Int,
    @Enumerated(EnumType.STRING) @Column(name = "`condition`", nullable = false) val condition: ProductCondition,
    @Enumerated(EnumType.STRING)
    @Column(name = "condition_grade")
    val conditionGrade: ProductConditionGrade? = null,
    @Column(name = "stock_quantity", nullable = false) val stockQuantity: Int,
    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    val status: ProductStatus,
    @Column(name = "custom_name") val customName: String? = null,
    @Column(name = "custom_attributes", columnDefinition = "text")
    @Convert(converter = JsonConverter::class)
    val customAttributes: Map<String, Any>? = null,
    @OneToMany(mappedBy = "productItem", cascade = [CascadeType.ALL], orphanRemoval = true)
    val images: MutableList<ProductImageEntity> = mutableListOf(),
) : BaseEntity()
