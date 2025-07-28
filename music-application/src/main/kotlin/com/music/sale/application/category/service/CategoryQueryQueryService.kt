package com.music.sale.application.category.service

import com.music.sale.application.category.port.inport.CategoryQueryUseCase
import com.music.sale.application.category.port.outport.CategoryQueryPort
import com.music.sale.domain.category.Category
import com.music.sale.domain.category.CategoryType
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
open class CategoryQueryQueryService(
    private val categoryQueryPort: CategoryQueryPort,
) : CategoryQueryUseCase {
    override fun getCategoryById(id: Long): Category {
        return categoryQueryPort.getCategoryById(id)
    }

    override fun getAllCategories(): List<Category> {
        return categoryQueryPort.findAll()
    }

    override fun getCategoriesByType(type: CategoryType): List<Category> {
        return categoryQueryPort.findByType(type)
    }

    override fun getRootCategories(): List<Category> {
        return categoryQueryPort.findRootCategories()
    }

    override fun getCategoriesByParentId(parentId: Long): List<Category> {
        return categoryQueryPort.findByParentId(parentId)
    }
}
