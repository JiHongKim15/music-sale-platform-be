package com.music.sale.persistence.wishlist.entity

import com.music.sale.application.wishlist.dto.WishlistOutput
import com.music.sale.domain.category.Category
import com.music.sale.domain.category.CategoryType
import com.music.sale.domain.product.Product
import com.music.sale.domain.product.enum.ProductCondition
import com.music.sale.domain.product.enum.ProductConditionGrade
import com.music.sale.domain.product.enum.ProductStatus
import com.music.sale.domain.user.User
import com.music.sale.domain.wishlist.Wishlist
import com.music.sale.persistence.common.BaseEntity
import jakarta.persistence.*

@Entity
@Table(name = "wishlist")
class WishlistEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long? = null,
    @Column(name = "user_id", nullable = false) val userId: Long,
    @Column(name = "product_id", nullable = false) val productId: Long,
) : BaseEntity() {
    fun toDomain(): Wishlist {
        val dummyCategory =
            Category(
                id = 0,
                name = "",
                type = CategoryType.PRODUCT,
                parent = null,
                path = "",
                depth = 0,
                isActive = true,
            )
        val dummyUser = User(id = userId)
        val dummyCatalog =
            com.music.sale.domain.product.ProductCatalog(
                id = 0,
                name = "",
                category = dummyCategory,
                brand = null,
                attributes = emptyMap(),
            )
        return Wishlist(
            id = id,
            user = dummyUser,
            product =
                Product(
                    id = productId,
                    catalog = dummyCatalog,
                    price = 0,
                    seller = dummyUser,
                    store = null,
                    condition = ProductCondition.NEW,
                    conditionGrade = ProductConditionGrade.S,
                    stockQuantity = 0,
                    status = ProductStatus.SELLING,
                    customName = null,
                    customAttributes = null,
                    images = mutableListOf(),
                ),
        )
    }

    fun toOutput(): WishlistOutput {
        // 임시로 ProductOutput을 생성하지 않고 null을 반환하도록 수정
        // 실제로는 ProductService에서 조회해야 함
        throw UnsupportedOperationException("ProductOutput은 ProductService에서 조회해야 합니다")
    }

    companion object {
        fun fromDomain(wishlist: Wishlist): WishlistEntity {
            return WishlistEntity(
                id = wishlist.id,
                userId = wishlist.user.id!!,
                productId = wishlist.product.id!!,
            )
        }
    }
}
