package com.music.sale.adapter.persistence.category

import com.music.sale.adapter.persistence.category.mapper.CategoryPersistenceMapper
import com.music.sale.adapter.persistence.category.repository.CategoryRepository
import com.music.sale.application.category.port.outport.CategoryPort
import com.music.sale.domain.category.Category
import com.music.sale.domain.category.CategoryType
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
@Transactional
class CategoryPersistenceAdapter(
    private val repository: CategoryRepository,
    private val mapper: CategoryPersistenceMapper,
) : CategoryPort {
    override fun getCategoryById(id: Long): Category {
        return mapper.toDomain(repository.findById(id).get())
    }

    override fun findAll(): List<Category> {
        return repository.findAll().map { mapper.toDomain(it) }
    }

    override fun findById(id: Long): Category? {
        return repository.findById(id).map { mapper.toDomain(it) }.orElse(null)
    }

    override fun findByType(type: CategoryType): List<Category> {
        return repository.findByType(type).map { mapper.toDomain(it) }
    }

    override fun findRootCategories(): List<Category> {
        return repository.findByParentIsNull().map { mapper.toDomain(it) }
    }

    override fun findByParentId(parentId: Long): List<Category> {
        return repository.findByParentId(parentId).map { mapper.toDomain(it) }
    }

    override fun save(category: Category): Category {
        val entity = mapper.toEntity(category)
        val savedEntity = repository.save(entity)
        return mapper.toDomain(savedEntity)
    }

    override fun getReferenceById(id: Long): Category {
        val entity = repository.getReferenceById(id)
        return mapper.toDomain(entity)
    }

    override fun delete(id: Long) {
        repository.deleteById(id)
    }
}
