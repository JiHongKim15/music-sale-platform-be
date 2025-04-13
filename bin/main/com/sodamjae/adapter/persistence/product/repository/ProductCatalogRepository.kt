package com.sodamjae.adapter.persistence.product.repository

import com.sodamjae.adapter.persistence.product.entity.ProductCatalogEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface ProductCatalogRepository : JpaRepository<ProductCatalogEntity, Long> {
    
    // 제품 타입으로 검색
    fun findByProductType(productType: String, pageable: Pageable): Page<ProductCatalogEntity>
    
    // 이름으로 검색(부분 일치)
    fun findByNameContaining(name: String, pageable: Pageable): Page<ProductCatalogEntity>
    
    // 여러 조건으로 검색
    @Query("""
        SELECT pc FROM ProductCatalogEntity pc 
        WHERE (:productType IS NULL OR pc.productType = :productType)
        AND (:keyword IS NULL OR pc.name LIKE %:keyword% OR pc.attributes LIKE %:keyword%)
    """)
    fun searchCatalogs(
        @Param("productType") productType: String?,
        @Param("keyword") keyword: String?,
        pageable: Pageable
    ): Page<ProductCatalogEntity>
} 