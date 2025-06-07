package com.music.sale.application.product.mapper

import com.music.sale.application.product.dto.CreateProductInput
import com.music.sale.application.product.dto.ProductOutput
import com.music.sale.application.product.dto.UpdateProductInput
import com.music.sale.domain.category.Category
import com.music.sale.domain.category.CategoryType
import com.music.sale.domain.product.Product
import com.music.sale.domain.product.enum.ProductCondition
import com.music.sale.domain.product.enum.ProductConditionGrade
import com.music.sale.domain.store.model.Store
import com.music.sale.domain.user.User
import org.springframework.stereotype.Component

@Component
class ProductMapper {
    fun toOutput(product: Product): ProductOutput {
        return ProductOutput(
            id = product.id,
            name = product.name,
            category = product.category.name,
            price = product.price,
            seller = product.seller,
            store = product.store ?: Store.empty(),
            condition = product.condition,
            conditionGrade = product.conditionGrade ?: ProductConditionGrade.NONE,
            stockQuantity = product.stockQuantity,
            attributes = product.attributes
        )
    }

    fun toDomain(input: CreateProductInput): Product {
        return Product.create(
            name = input.name,
            category = Category(0L, "임시카테고리", CategoryType.PRODUCT, null, "", 0, true),
            price = input.price.toInt(),
            sellerId = 0L,
            storeId = null,
            condition = ProductCondition.NEW,
            conditionGrade = ProductConditionGrade.NONE,
            stockQuantity = input.stock,
            attributes = emptyMap()
        )
    }

    fun toDomain(input: UpdateProductInput): Product {
        return Product.update(
            id = input.id,
            name = input.name,
            category = Category(0L, "임시카테고리", CategoryType.PRODUCT, null, "", 0, true),
            price = input.price.toInt(),
            sellerId = 0L,
            storeId = null,
            condition = ProductCondition.NEW,
            conditionGrade = ProductConditionGrade.NONE,
            stockQuantity = input.stock,
            attributes = emptyMap()
        )
    }
}