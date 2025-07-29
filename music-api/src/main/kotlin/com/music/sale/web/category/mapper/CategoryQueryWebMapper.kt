package com.music.sale.web.category.mapper

import com.music.sale.domain.category.Category
import com.music.sale.web.category.response.CategoryQueryResponse
import org.springframework.stereotype.Component

@Component
class CategoryQueryWebMapper {
    fun toResponse(category: Category): CategoryQueryResponse {
        return CategoryQueryResponse(
            id = category.id,
            name = category.name,
            type = category.type,
            parent = category.parent,
            path = category.path,
            depth = category.depth,
            isActive = category.isActive,
        )
    }
}
