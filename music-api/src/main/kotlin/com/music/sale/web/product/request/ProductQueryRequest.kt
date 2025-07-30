package com.music.sale.web.product.request

import com.music.sale.application.product.enum.ProductSortableField
import com.music.sale.common.SortDirection
import com.music.sale.domain.product.enum.ProductCondition
import com.music.sale.domain.product.enum.ProductConditionGrade
import com.music.sale.domain.product.enum.ProductStatus
import com.music.sale.domain.product.enum.SearchProductKeywordType
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort

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
    fun toPageable(): PageRequest {
        val sortProperty: String = sort?.name ?: "id"

        val direction = when (sortDirection) {
            SortDirection.ASC -> Sort.Direction.ASC
            SortDirection.DESC -> Sort.Direction.DESC
            else -> Sort.Direction.ASC // Default to ASC if sortDirection is null
        }

        val actualPageNumber = if (pageNumber < 1) 0 else pageNumber - 1
        val actualPageSize = if (pageSize <= 0) 10 else pageSize

        return PageRequest.of(actualPageNumber, actualPageSize, Sort.by(direction, sortProperty))
    }
}

data class GetProductRequest(
    val pageNumber: Int = 1,
    val pageSize: Int = 10,
    val sort: ProductSortableField? = ProductSortableField.CREATED_AT,
    val sortDirection: SortDirection? = SortDirection.DESC,
) {
    fun toPageRequest(): PageRequest {
        val sortProperty: String = sort?.name ?: "id" // <--- **Crucial change here**

        val direction = when (sortDirection) {
            SortDirection.ASC -> Sort.Direction.ASC
            SortDirection.DESC -> Sort.Direction.DESC
            else -> Sort.Direction.ASC // Default to ASC if sortDirection is null
        }
        val actualPageNumber = if (pageNumber < 1) 0 else pageNumber - 1
        val actualPageSize = if (pageSize <= 0) 10 else pageSize

        return PageRequest.of(actualPageNumber, actualPageSize, Sort.by(direction, sortProperty))
    }
}
