package com.music.sale.adapter.persistence.product.repository

import com.music.sale.adapter.persistence.product.entity.ProductCatalogEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface ProductCatalogRepository : JpaRepository<ProductCatalogEntity, Long> {

    // 제품 타입으로 검색
    fun findByProductTypeId(productTypeId: Long, pageable: Pageable): Page<ProductCatalogEntity>

    // 이름으로 검색(부분 일치)
    fun findByNameContaining(name: String, pageable: Pageable): Page<ProductCatalogEntity>

    // 여러 조건으로 검색
    @Query(
        """
        SELECT pc FROM ProductCatalogEntity pc 
        WHERE (:productTypeId IS NULL OR pc.productTypeId = :productTypeId)
        AND (:keyword IS NULL OR pc.name LIKE %:keyword% OR pc.attributes LIKE %:keyword%)
    """
    )
    fun searchCatalogs(
        @Param("productTypeId") productTypeId: Long?,
        @Param("keyword") keyword: String?,
        pageable: Pageable
    ): Page<ProductCatalogEntity>
} 