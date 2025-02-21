package com.sodamjae.adapter.persistence.product

import com.sodamjae.adapter.persistence.common.BaseEntity
import com.sodamjae.adapter.persistence.shop.ShopEntity
import com.sodamjae.domain.product.Product
import jakarta.persistence.*
import java.math.BigDecimal

@Entity
@Table(name = "products")
class ProductEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(nullable = false)
    var name: String,

    @Column(nullable = false)
    var description: String,

    @Column(nullable = false)
    var price: BigDecimal,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    var category: Product.Category,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    var condition: Product.ProductCondition,

    @Column(nullable = false)
    var quantity: Int,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shop_id")
    val shop: ShopEntity
) : BaseEntity() {
    fun toDomain(): Product {
        return Product.create(
            name = Product.Name(name),
            description = Product.Description(description),
            price = Product.Price(price),
            category = category,
            condition = condition,
            quantity = Product.Quantity(quantity),
            shop = shop.toDomain()
        )
    }

    companion object {
        fun fromDomain(product: Product, shopEntity: ShopEntity): ProductEntity {
            return ProductEntity(
                id = product.id?.value,
                name = product.getName().value,
                description = product.getDescription().value,
                price = product.getPrice().value,
                category = product.getCategory(),
                condition = product.getCondition(),
                quantity = product.getQuantity().value,
                shop = shopEntity
            )
        }
    }
} 