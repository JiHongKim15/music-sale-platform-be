// Copyright (C) 2024 Your Name or Company
package com.music.sale.web.product.request

import com.music.sale.application.product.enum.ProductSortableField
import com.music.sale.common.DefaultPageable
import com.music.sale.common.Pageable
import com.music.sale.common.SortDirection
import com.music.sale.domain.product.enum.ProductCondition
import com.music.sale.domain.product.enum.ProductConditionGrade
import com.music.sale.domain.product.enum.ProductStatus
import com.music.sale.domain.product.enum.SearchProductKeywordType

data class CreateProductRequest(
    val name: String,
    val catalogId: Long,
    val price: Int,
    val sellerId: Long,
    val storeId: Long,
    val condition: ProductCondition,
    val conditionGrade: ProductConditionGrade,
    val stockQuantity: Int,
    val status: ProductStatus,
    val attributes: Map<String, Any>,
)

data class UpdateProductRequest(
    val name: String,
    val catalogId: Long,
    val price: Int,
    val sellerId: Long,
    val storeId: Long,
    val condition: ProductCondition,
    val conditionGrade: ProductConditionGrade,
    val stockQuantity: Int,
    val status: ProductStatus,
    val attributes: Map<String, Any>,
)

data class SearchProductRequest(
    // "펜더", "텔레캐스터"
    val keyword: String? = null,
    val keywordType: SearchProductKeywordType? = null,
    // ProductCategory.GUITAR
    val categoryId: Long? = null,
    // "서울"
    val location: String? = null,
    // true: 새 제품, false: 중고
    val condition: ProductCondition? = null,
    // S, A, B
    val conditionGrade: ProductConditionGrade? = null,
    // 최소 가격 필터
    val minPrice: Int? = null,
    // 최대 가격 필터
    val maxPrice: Int? = null,
    val status: ProductStatus? = null,
    val pageNumber: Int = 1,
    val pageSize: Int = 10,
    val sort: ProductSortableField? = ProductSortableField.CREATED_AT,
    val sortDirection: SortDirection? = SortDirection.DESC,
) {
    fun toPageable(): Pageable {
        // 1-based를 0-based로 변환
        return DefaultPageable(
            pageNumber = pageNumber - 1,
            pageSize = pageSize,
            sort = sort?.fieldName,
            sortDirection = sortDirection,
        )
    }
}

data class GetProductRequest(
    val pageNumber: Int = 1,
    val pageSize: Int = 10,
    val sort: ProductSortableField? = ProductSortableField.CREATED_AT,
    val sortDirection: SortDirection? = SortDirection.DESC,
) {
    fun toPageable(): Pageable {
        // 1-based를 0-based로 변환
        return DefaultPageable(
            pageNumber = pageNumber - 1,
            pageSize = pageSize,
            sort = sort?.fieldName,
            sortDirection = sortDirection,
        )
    }
}
