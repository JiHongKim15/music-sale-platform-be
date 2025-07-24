package com.music.sale.web.product.mapper

import com.music.sale.application.product.dto.CreateProductInput
import com.music.sale.application.product.dto.SearchProductInput
import com.music.sale.application.product.dto.UpdateProductInput
import com.music.sale.web.product.request.CreateProductRequest
import com.music.sale.web.product.request.SearchProductRequest
import com.music.sale.web.product.request.UpdateProductRequest
import org.springframework.stereotype.Component

@Component
class ProductRequestMapper {
    fun toSearchCondition(request: SearchProductRequest): SearchProductInput {
        return SearchProductInput(
            keyword = request.keyword,
            minPrice = request.minPrice,
            maxPrice = request.maxPrice,
            categoryId = request.categoryId,
            location = request.location,
            condition = request.condition,
            conditionGrade = request.conditionGrade,
            status = request.status,
            keywordType = request.keywordType,

            )
    }

    fun toCreateProductInput(request: CreateProductRequest): CreateProductInput {
        return CreateProductInput(
            sellerId = request.sellerId,
            storeId = request.storeId,
            name = request.name,
            price = request.price,
            stockQuantity = request.stockQuantity,
            catalogId = request.catalogId,
            condition = request.condition,
            conditionGrade = request.conditionGrade,
            status = request.status,
            attributes = request.attributes,
        )
    }

    fun toUpdateProductInput(
        id: Long,
        request: UpdateProductRequest,
    ): UpdateProductInput {
        return UpdateProductInput(
            id = id,
            name = request.name,
            price = request.price,
            stockQuantity = request.stockQuantity,
            catalogId = request.catalogId,
            condition = request.condition,
            conditionGrade = request.conditionGrade,
            status = request.status,
            attributes = request.attributes,
        )
    }
}
