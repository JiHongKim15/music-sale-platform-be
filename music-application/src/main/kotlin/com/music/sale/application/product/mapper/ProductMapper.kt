// Copyright (C) 2024 Your Name or Company
package com.music.sale.application.product.mapper

import com.music.sale.application.product.dto.*
import com.music.sale.domain.product.Product
import com.music.sale.domain.store.Store
import com.music.sale.domain.user.User
import org.springframework.stereotype.Component

@Component
class ProductMapper {
    fun toOutput(product: Product): ProductOutput {
        return ProductOutput(
            id = product.id,
            catalog =
                ProductCatalog(
                    id = product.catalog.id,
                    name = product.catalog.name,
                    categories = listOf(product.catalog.category),
                    brand = product.catalog.brand ?: "",
                    attribute = product.catalog.attributes?.mapValues { it.value.toString() } ?: emptyMap(),
                ),
            name = product.name(),
            price = product.price,
            seller = product.seller,
            store = product.store,
            condition = product.condition,
            conditionGrade = product.conditionGrade,
            stockQuantity = product.stockQuantity,
            status = product.status,
            attributes = product.attributes(),
            images = product.images.toList(),
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
            status = input.status,
        )
    }

    fun toSaveProductCondition(
        input: CreateProductInput,
        seller: User?,
        store: Store?,
    ): SaveProductItemCondition {
        return SaveProductItemCondition(
            name = input.name,
            catalogId = input.catalogId,
            price = input.price,
            seller = seller,
            store = store,
            condition = input.condition,
            conditionGrade = input.conditionGrade,
            stockQuantity = input.stockQuantity,
            status = input.status,
            attributes = input.attributes,
        )
    }
}
