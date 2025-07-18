// Copyright (C) 2024 Your Name or Company
package com.music.sale.application.category.service

import com.music.sale.application.category.port.inport.CategoryUseCase
import com.music.sale.application.category.port.outport.CategoryPort
import com.music.sale.domain.category.Category
import com.music.sale.domain.category.CategoryType
import org.springframework.stereotype.Service

@Service
class CategoryService(
    private val categoryPort: CategoryPort,
) : CategoryUseCase {
    override fun getCategoryById(id: Long): Category {
        return categoryPort.getCategoryById(id)
    }

    override fun getAllCategories(): List<Category> {
        return categoryPort.findAll()
    }

    override fun getCategoriesByType(type: CategoryType): List<Category> {
        return categoryPort.findByType(type)
    }

    override fun getRootCategories(): List<Category> {
        return categoryPort.findRootCategories()
    }

    override fun getCategoriesByParentId(parentId: Long): List<Category> {
        return categoryPort.findByParentId(parentId)
    }
}
