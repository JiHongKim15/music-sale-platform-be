package com.music.sale.web.product.mapper

import com.music.sale.application.product.dto.CreateProductInput
import com.music.sale.application.product.dto.SearchProductInput
import com.music.sale.application.product.dto.UpdateProductInput
import com.music.sale.web.product.request.CreateProductRequest
import com.music.sale.web.product.request.SearchProductRequest
import com.music.sale.web.product.request.UpdateProductRequest
import org.springframework.stereotype.Component

@Component
class ProductWebMapper {
    fun toCreateProductInput(request: CreateProductRequest): CreateProductInput {
        return CreateProductInput(
            name = request.name,
            catalogId = request.catalogId,
            price = request.price,
            sellerId = request.sellerId,
            storeId = request.storeId,
            condition = request.condition,
            conditionGrade = request.conditionGrade,
            stockQuantity = request.stockQuantity,
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
            catalogId = request.catalogId,
            price = request.price,
            sellerId = request.sellerId,
            storeId = request.storeId,
            condition = request.condition,
            conditionGrade = request.conditionGrade,
            stockQuantity = request.stockQuantity,
            status = request.status,
            attributes = request.attributes,
        )
    }

    fun toSearchCondition(request: SearchProductRequest): SearchProductInput {
        return SearchProductInput(
            keyword = request.keyword,
            keywordType = request.keywordType,
            categoryId = request.categoryId,
            location = request.location,
            condition = request.condition,
            conditionGrade = request.conditionGrade,
            minPrice = request.minPrice,
            maxPrice = request.maxPrice,
            status = request.status,
        )
    }
}
