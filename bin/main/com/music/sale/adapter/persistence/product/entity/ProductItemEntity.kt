package com.music.sale.adapter.persistence.product.entity

import com.music.sale.adapter.persistence.category.entity.CategoryEntity
import com.music.sale.adapter.persistence.common.BaseEntity
import com.music.sale.adapter.persistence.common.JsonConverter
import com.music.sale.adapter.persistence.seller.entity.SellerEntity
import com.music.sale.adapter.persistence.store.entity.StoreEntity
import com.music.sale.domain.product.Product
import com.music.sale.domain.product.enum.ProductCondition
import com.music.sale.domain.product.enum.ProductConditionGrade
import jakarta.persistence.*
import org.hibernate.annotations.Type

/**
 * 제품 아이템 JPA 엔티티
 * 실제 판매되는 상품 인스턴스를 표현
 */
@Entity
@Table(name = "product_item")
class ProductItemEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    val category: CategoryEntity,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "catalog_id", nullable = false)
    val catalog: ProductCatalogEntity,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seller_id", nullable = false)
    val seller: SellerEntity,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", nullable = false)
    val store: StoreEntity,

    @Column(nullable = false)
    val price: Int,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    val condition: ProductCondition,

    @Enumerated(EnumType.STRING)
    @Column(name = "condition_grade")
    val conditionGrade: ProductConditionGrade? = null,

    @Column(name = "stock_quantity", nullable = false)
    val stockQuantity: Int,

    @Column(name = "custom_name")
    val customName: String? = null,

    @Column(name = "custom_attributes", columnDefinition = "text")
    @Convert(converter = JsonConverter::class)
    val customAttributes: Map<String, Any>? = null

) : BaseEntity() {
    fun toDomain(): Product {
        return Product.create(
            name = catalog.name,
            category = category.toDomain(),
            price = price,
            sellerId = seller.id ?: 0L,
            storeId = store.id,
            condition = condition,
            conditionGrade = conditionGrade,
            stockQuantity = stockQuantity,
            attributes = customAttributes ?: emptyMap()
        )
    }

    companion object {
        fun fromDomain(product: Product): ProductItemEntity {
            return ProductItemEntity(
                id = product.id,
                category = CategoryEntity.fromDomain(product.category),
                catalog = ProductCatalogEntity.fromDomain(product),
                seller = SellerEntity.fromId(product.seller.id ?: 0L),
                store = StoreEntity.fromId(product.store?.id ?: 0L),
                price = product.price,
                condition = product.condition,
                conditionGrade = product.conditionGrade,
                stockQuantity = product.stockQuantity,
                customName = null,
                customAttributes = product.attributes
            )
        }
    }
}
