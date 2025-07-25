package com.music.sale.web.product.mapper

import com.music.sale.application.product.dto.SearchProductInput
import com.music.sale.web.product.request.SearchProductRequest
import org.springframework.stereotype.Component

@Component
class ProductQueryMapper {
    /**
     * Request DTO → Search Condition 변환
     * 검색 조건을 내부 검색 로직에 맞는 형태로 변환
     */
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
} 
