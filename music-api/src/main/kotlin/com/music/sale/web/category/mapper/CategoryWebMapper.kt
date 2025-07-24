package com.music.sale.web.category.mapper

import com.music.sale.application.category.dto.CategoryCreationCommand
import com.music.sale.domain.category.Category
import com.music.sale.web.category.request.CreateCategoryRequest
import com.music.sale.web.category.response.CategoryResponse
import org.springframework.stereotype.Component

@Component
class CategoryWebMapper {

    fun toCommand(request: CreateCategoryRequest): CategoryCreationCommand {
        return CategoryCreationCommand(
            name = request.name,
            type = request.type,
            parentId = request.parentId,
        )
    }


    fun toResponse(category: Category): CategoryResponse {
        return CategoryResponse(
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
