package com.music.sale.persistence.category

import com.music.sale.application.category.port.outport.CategoryQueryPort
import com.music.sale.domain.category.Category
import com.music.sale.domain.category.CategoryType
import com.music.sale.persistence.category.mapper.CategoryQueryPersistenceMapper
import com.music.sale.persistence.category.repository.CategoryQueryJpaRepository
import org.springframework.stereotype.Component

@Component
class CategoryQueryPersistenceAdapter(
    private val categoryQueryJpaRepository: CategoryQueryJpaRepository,
    private val categoryMapper: CategoryQueryPersistenceMapper,
) : CategoryQueryPort {
    override fun getCategoryById(id: Long): Category {
        return categoryQueryJpaRepository.findById(id)
            .orElseThrow { NoSuchElementException("Category not found with id: $id") }
            .let { categoryMapper.toDomain(it) }
    }

    override fun findAll(): List<Category> {
        return categoryQueryJpaRepository.findAll().map { categoryMapper.toDomain(it) }
    }

    override fun findById(id: Long): Category? {
        return categoryQueryJpaRepository.findById(id).map { categoryMapper.toDomain(it) }.orElse(null)
    }

    override fun findByType(type: CategoryType): List<Category> {
        return categoryQueryJpaRepository.findByType(type).map { categoryMapper.toDomain(it) }
    }

    override fun findRootCategories(): List<Category> {
        return categoryQueryJpaRepository.findByParentIsNull().map { categoryMapper.toDomain(it) }
    }

    override fun findByParentId(parentId: Long): List<Category> {
        return categoryQueryJpaRepository.findByParentId(parentId).map { categoryMapper.toDomain(it) }
    }

    override fun save(category: Category): Category {
        val entity = categoryMapper.toEntity(category)
        val savedEntity = categoryQueryJpaRepository.save(entity)
        return categoryMapper.toDomain(savedEntity)
    }

    override fun delete(id: Long) {
        categoryQueryJpaRepository.deleteById(id)
    }
}
