// Copyright (C) 2024 Your Name or Company
package com.music.sale.adapter.web.category.mapper

import com.music.sale.adapter.web.category.response.CategoryParentResponse
import com.music.sale.adapter.web.category.response.CategoryResponse
import com.music.sale.domain.category.Category
import org.springframework.stereotype.Component

@Component
class CategoryWebMapper {
    fun toResponse(category: Category): CategoryResponse {
        return CategoryResponse(
            id = category.id,
            name = category.name,
            type = category.type,
            parent = category.parent?.let { toParentResponse(it) },
            path = category.path,
            depth = category.depth,
            isActive = category.isActive,
            isRoot = category.isRoot(),
            isLeaf = category.isLeaf(),
        )
    }

    private fun toParentResponse(category: Category): CategoryParentResponse {
        return CategoryParentResponse(
            id = category.id,
            name = category.name,
            type = category.type,
        )
    }
} 
