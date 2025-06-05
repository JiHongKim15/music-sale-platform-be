package com.music.sale.application.product.mapper

import com.music.sale.application.product.dto.ProductOutput
import com.music.sale.domain.product.Product
import com.music.sale.domain.product.enum.ProductConditionGrade
import com.music.sale.domain.store.model.Store
import org.springframework.stereotype.Component

@Component
class ProductMapper {
    fun toOutput(it: Product): ProductOutput {
        return ProductOutput(
            id = it.id,
            name = it.name,
            category = it.category.name,
            price = it.price,
            seller = it.seller,
            store = it.store ?: Store.empty(),
            condition = it.condition,
            conditionGrade = it.conditionGrade ?: ProductConditionGrade.NONE,
            stockQuantity = it.stockQuantity,
            attributes = it.attributes

        )
    }

}