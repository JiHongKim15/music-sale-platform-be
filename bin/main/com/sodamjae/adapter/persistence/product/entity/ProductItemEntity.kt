package com.music.sale.adapter.persistence.product.entity

import com.music.sale.adapter.persistence.common.BaseEntity
import com.music.sale.adapter.persistence.product.enum.ProductConditionEntity
import jakarta.persistence.*

/**
 * 제품 아이템 JPA 엔티티
 * 실제 판매되는 상품 인스턴스를 표현
 */
@Entity
@Table(name = "product_item")
class ProductItemEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,


    @Column(name = "price", nullable = false)
    val price: Long,

    @Column(name = "product_catalog_id", nullable = false)
    val productCatalogId: Long,


    @Column(name = "seller_id", nullable = false)
    val sellerId: Long,

    @Column(name = "store_id", nullable = false)
    val storeId: Long,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    val condition: ProductConditionEntity,

    @Column(name = "stock_quantity", nullable = false)
    val stockQuantity: Int,

    @Column(name = "itemAttributes", nullable = false)
    val itemAttributes: String,
) : BaseEntity() 