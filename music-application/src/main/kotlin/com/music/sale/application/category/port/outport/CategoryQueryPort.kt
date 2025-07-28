// Copyright (C) 2024 Your Name or Company
package com.music.sale.application.category.port.outport

import com.music.sale.domain.category.Category
import com.music.sale.domain.category.CategoryType
import org.springframework.stereotype.Component

@Component
interface CategoryQueryPort {
    fun getCategoryById(id: Long): Category

    fun findAll(): List<Category>

    fun findById(id: Long): Category?

    fun findByType(type: CategoryType): List<Category>

    fun findRootCategories(): List<Category>

    fun findByParentId(parentId: Long): List<Category>

    fun save(category: Category): Category

    fun delete(id: Long)
}
