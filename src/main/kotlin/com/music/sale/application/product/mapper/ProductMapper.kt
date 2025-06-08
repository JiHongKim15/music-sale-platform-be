package com.music.sale.application.product.mapper

import com.music.sale.adapter.persistence.product.dto.SaveProductCondition
import com.music.sale.adapter.persistence.product.dto.SearchProductCondition
import com.music.sale.application.product.dto.CreateProductInput
import com.music.sale.application.product.dto.ProductOutput
import com.music.sale.application.product.dto.SearchProductInput
import com.music.sale.application.product.dto.UpdateProductInput
import com.music.sale.domain.product.Product
import com.music.sale.domain.product.enum.ProductConditionGrade
import com.music.sale.domain.store.model.Store
import org.springframework.stereotype.Component

@Component
class ProductMapper {
    fun toOutput(product: Product): ProductOutput {
        return ProductOutput(
            id = product.id,
            name = product.name(),
            catalog = product.category,
            price = product.price,
            seller = product.seller,
            store = product.store ?: Store.empty(),
            condition = product.condition,
            conditionGrade = product.conditionGrade ?: ProductConditionGrade.NONE,
            stockQuantity = product.stockQuantity,
            status = product.status,
            attributes = product.attributes()
        )
    }

    fun toSearchProductCondition(input: SearchProductInput): SearchProductCondition {
        return SearchProductCondition(
            keyword = input.keyword,
            keywordType = input.keywordType,
            categoryId = input.categoryId,
            location = input.location,
            condition = input.condition,
            conditionGrade = input.conditionGrade,
            minPrice = input.minPrice,
            maxPrice = input.maxPrice,
            status = input.status
        )
    }

    fun toSaveProductCondition(product: Product, input: UpdateProductInput): SaveProductCondition {
        return SaveProductCondition(
            id = product.id,
            name = input.name ?: product.name(),
            catalogId = input.catalogId ?: product.category.id,
            price = input.price ?: product.price,
            sellerId = input.sellerId ?: product.seller.id,
            storeId = input.storeId ?: product.store?.id,
            condition = input.condition ?: product.condition,
            conditionGrade = input.conditionGrade ?: product.conditionGrade,
            stockQuantity = input.stockQuantity ?: product.stockQuantity,
            status = input.status ?: product.status,
            attributes = input.attributes ?: product.attributes()
        )
    }

    fun toSaveProductCondition(input: CreateProductInput): SaveProductCondition {
        return SaveProductCondition(
            name = input.name,
            catalogId = input.catalogId,
            price = input.price,
            sellerId = input.sellerId,
            storeId = input.storeId,
            condition = input.condition,
            conditionGrade = input.conditionGrade,
            stockQuantity = input.stockQuantity,
            status = input.status,
            attributes = input.attributes
        )
    }

}