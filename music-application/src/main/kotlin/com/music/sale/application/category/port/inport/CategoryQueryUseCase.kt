// Copyright (C) 2024 Your Name or Company
package com.music.sale.application.category.port.inport

import com.music.sale.domain.category.Category
import com.music.sale.domain.category.CategoryType

interface CategoryQueryUseCase {
    fun getCategoryById(id: Long): Category

    fun getAllCategories(): List<Category>

    fun getCategoriesByType(type: CategoryType): List<Category>

    fun getRootCategories(): List<Category>

    fun getCategoriesByParentId(parentId: Long): List<Category>
}
