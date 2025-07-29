package com.music.sale.persistence.category

import com.music.sale.application.category.port.outport.CategoryQueryPort
import com.music.sale.domain.category.Category
import com.music.sale.domain.category.CategoryType
import com.music.sale.persistence.category.mapper.CategoryQueryPersistenceMapper
import com.music.sale.persistence.category.repository.CategoryQueryJpaRepository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class CategoryQueryPersistenceAdapter(
    private val categoryQueryJpaRepository: CategoryQueryJpaRepository,
    private val categoryMapper: CategoryQueryPersistenceMapper,
) : CategoryQueryPort {

    private val log = LoggerFactory.getLogger(javaClass)

    override fun getCategoryById(id: Long): Category {
        return categoryQueryJpaRepository.findById(id)
            .orElseThrow { NoSuchElementException("Category not found with id: $id") }
            .let { categoryMapper.toDomain(it) }
    }

    override fun findAll(): List<Category> {
        val entities = categoryQueryJpaRepository.findAll()
        log.debug("findAll: Fetched {} CategoryEntities from repository.", entities.size)
        if (entities.isEmpty()) {
            log.debug("findAll: No CategoryEntities found in repository.")
            return emptyList()
        }

        val domains = entities.map { entity ->
            try {
                val domain = categoryMapper.toDomain(entity)
                log.debug("findAll: Mapped entity ID {} to domain.", entity.id)
                domain
            } catch (e: Exception) {
                log.error("findAll: Error mapping entity ID {} to domain: {}", entity.id, e.message, e)
                null
            }
        }.filterNotNull()

        log.debug("findAll: Mapped {} Category domains.", domains.size)
        return domains
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
