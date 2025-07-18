// Copyright (C) 2024 Your Name or Company
package com.music.sale.persistence.category.mapper

import com.music.sale.domain.category.Category
import com.music.sale.persistence.category.entity.CategoryEntity
import org.springframework.stereotype.Component

@Component
class CategoryPersistenceMapper {
    fun toDomain(entity: CategoryEntity): Category {
        return Category(
            id = entity.id ?: 0L,
            name = entity.name,
            type = entity.type,
            parent = entity.parent?.let { toDomain(it) },
            path = entity.path,
            depth = entity.depth,
            isActive = entity.isActive,
        )
    }

    fun toEntity(category: Category): CategoryEntity {
        return CategoryEntity(
            id = category.id,
            name = category.name,
            type = category.type,
            parent = category.parent?.let { toEntity(it) },
            path = category.path,
            depth = category.depth,
            isActive = category.isActive,
        )
    }
}
