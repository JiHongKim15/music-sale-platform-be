package com.music.sale.application.category.service

import com.music.sale.application.category.port.`in`.CategoryUseCase
import com.music.sale.application.category.port.out.CategoryPort
import com.music.sale.domain.category.Category
import org.springframework.stereotype.Service

@Service
class CategoryService(
    private val categoryPort: CategoryPort
) : CategoryUseCase {
    override fun getCategoryById(id: Long): Category {
        return categoryPort.getCategoryById(id)
    }
}