package com.music.sale.persistence.category

import com.music.sale.application.category.port.outport.CategoryPort
import com.music.sale.domain.category.Category
import com.music.sale.domain.category.CategoryType
import com.music.sale.persistence.category.mapper.CategoryPersistenceMapper
import com.music.sale.persistence.category.repository.CategoryRepository
import org.springframework.stereotype.Component

@Component
class CategoryPersistenceAdapter(
    private val categoryRepository: CategoryRepository,
    private val categoryMapper: CategoryPersistenceMapper,
) : CategoryPort {
    override fun getCategoryById(id: Long): Category {
        return categoryRepository.findById(id).orElseThrow { NoSuchElementException("Category not found with id: $id") }
            .let { categoryMapper.toDomain(it) }
    }

    override fun findAll(): List<Category> {
        return categoryRepository.findAll().map { categoryMapper.toDomain(it) }
    }

    override fun findById(id: Long): Category? {
        return categoryRepository.findById(id).map { categoryMapper.toDomain(it) }.orElse(null)
    }

    override fun findByType(type: CategoryType): List<Category> {
        return categoryRepository.findByType(type).map { categoryMapper.toDomain(it) }
    }

    override fun findRootCategories(): List<Category> {
        return categoryRepository.findByParentIsNull().map { categoryMapper.toDomain(it) }
    }

    override fun findByParentId(parentId: Long): List<Category> {
        return categoryRepository.findByParentId(parentId).map { categoryMapper.toDomain(it) }
    }

    override fun save(category: Category): Category {
        val entity = categoryMapper.toEntity(category)
        val savedEntity = categoryRepository.save(entity)
        return categoryMapper.toDomain(savedEntity)
    }

    override fun delete(id: Long) {
        categoryRepository.deleteById(id)
    }
}
