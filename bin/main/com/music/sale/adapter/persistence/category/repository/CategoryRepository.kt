package com.music.sale.adapter.persistence.category.repository

import com.music.sale.adapter.persistence.category.entity.CategoryEntity
import com.music.sale.domain.category.CategoryType
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface CategoryRepository : JpaRepository<CategoryEntity, Long> {
    
    fun findByType(type: CategoryType): List<CategoryEntity>
    
    fun findByTypeAndIsActiveTrue(type: CategoryType): List<CategoryEntity>
    
    fun findByParentId(parentId: Long): List<CategoryEntity>
    
    fun findByPathStartingWith(path: String): List<CategoryEntity>
    
    @Query("SELECT c FROM CategoryEntity c WHERE c.type = :type AND c.depth = 0")
    fun findRootCategoriesByType(@Param("type") type: CategoryType): List<CategoryEntity>
    
    @Query("SELECT c FROM CategoryEntity c WHERE c.type = :type AND c.isActive = true AND c.depth = 0")
    fun findActiveRootCategoriesByType(@Param("type") type: CategoryType): List<CategoryEntity>
    
    fun existsByNameAndType(name: String, type: CategoryType): Boolean
    
    fun findByNameAndType(name: String, type: CategoryType): CategoryEntity?
} 