package com.music.sale.web.product.mapper

import com.music.sale.application.product.dto.CreateProductInput
import com.music.sale.application.product.dto.UpdateProductInput
import com.music.sale.web.product.request.CreateProductRequest
import com.music.sale.web.product.request.UpdateProductRequest
import org.springframework.stereotype.Component

@Component
class ProductCommandWebMapper {
    /**
     * Request DTO → Domain Entity 변환
     * 외부 입력을 내부 비즈니스 로직 수행을 위한 도메인 모델로 변환
     */
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

    /**
     * Request DTO → Domain Entity 변환
     * 외부 입력을 내부 비즈니스 로직 수행을 위한 도메인 모델로 변환
     */
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
