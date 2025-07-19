package com.music.sale.persistence.category

import com.music.sale.application.category.port.outport.CategoryPort
import com.music.sale.domain.category.Category
import com.music.sale.domain.category.CategoryType
import com.music.sale.persistence.category.mapper.CategoryPersistenceMapper
import com.music.sale.persistence.category.repository.CategoryRepository
import org.springframework.stereotype.Repository

@Repository
open class CategoryPersistenceAdapter(
    private val categoryRepository: CategoryRepository,
    private val mapper: CategoryPersistenceMapper,
) : CategoryPort {
    override fun getCategoryById(id: Long): Category {
        return mapper.toDomain(categoryRepository.findById(id).get())
    }

    override fun findAll(): List<Category> {
        return categoryRepository.findAll().map { mapper.toDomain(it) }
    }

    override fun findById(id: Long): Category {
        return categoryRepository.findById(id).map { mapper.toDomain(it) }.orElse(null)
    }

    override fun findByType(type: CategoryType): List<Category> {
        return categoryRepository.findByType(type).map { mapper.toDomain(it) }
    }

    override fun findRootCategories(): List<Category> {
        return categoryRepository.findByParentIsNull().map { mapper.toDomain(it) }
    }

    override fun findByParentId(parentId: Long): List<Category> {
        return categoryRepository.findByParentId(parentId).map { mapper.toDomain(it) }
    }

    override fun save(category: Category): Category {
        val entity = mapper.toEntity(category)
        val savedEntity = categoryRepository.save(entity)
        return mapper.toDomain(savedEntity)
    }

    override fun getReferenceById(id: Long): Category {
        val entity = categoryRepository.getReferenceById(id)
        return mapper.toDomain(entity)
    }

    override fun delete(id: Long) {
        categoryRepository.deleteById(id)
    }
}
