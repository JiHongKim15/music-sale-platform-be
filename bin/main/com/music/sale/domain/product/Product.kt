package com.music.sale.domain.product

import com.music.sale.domain.category.Category
import com.music.sale.domain.product.enum.ProductCondition
import com.music.sale.domain.product.enum.ProductConditionGrade
import com.music.sale.domain.store.model.Store
import com.music.sale.domain.user.User

/**
 * 통합된 제품 도메인 모델
 * 제품의 모든 속성과 비즈니스 로직을 포함
 */
class Product(
    val id: Long,
    val name: String,
    val category: Category,
    val price: Int,
    val seller: User,
    val store: Store?,
    val condition: ProductCondition,
    val conditionGrade: ProductConditionGrade?,
    val stockQuantity: Int,
    val attributes: Map<String, Any>,
    private val customName: String? = null,
    private val customAttributes: Map<String, Any>? = null
) {
    companion object {
        fun create(
            name: String,
            category: Category,
            price: Int,
            sellerId: Long,
            storeId: Long?,
            condition: ProductCondition,
            conditionGrade: ProductConditionGrade?,
            stockQuantity: Int,
            attributes: Map<String, Any>
        ): Product {
            return Product(
                id = 0L,
                name = name,
                category = category,
                price = price,
                seller = User(id = sellerId, email = null, name = null, role = null),
                store = storeId?.let { Store(id = it) },
                condition = condition,
                conditionGrade = conditionGrade,
                stockQuantity = stockQuantity,
                attributes = attributes
            )
        }

        fun update(
            id: Long,
            name: String,
            category: Category,
            price: Int,
            sellerId: Long,
            storeId: Long?,
            condition: ProductCondition,
            conditionGrade: ProductConditionGrade?,
            stockQuantity: Int,
            attributes: Map<String, Any>
        ): Product {
            return Product(
                id = id,
                name = name,
                category = category,
                price = price,
                seller = User(id = sellerId, email = null, name = null, role = null),
                store = storeId?.let { Store(id = it) },
                condition = condition,
                conditionGrade = conditionGrade,
                stockQuantity = stockQuantity,
                attributes = attributes
            )
        }
    }
}