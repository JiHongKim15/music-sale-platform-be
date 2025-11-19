package com.music.sale.persistence.product.repository

import com.music.sale.persistence.product.dto.ProductCatalogQueryResult
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface ProductCatalogQueryJooqRepository {
    fun findAll(pageable: Pageable): Page<ProductCatalogQueryResult>

    fun findById(id: Long): ProductCatalogQueryResult?

    fun findByCategoryId(
        categoryId: Long,
        pageable: Pageable,
    ): Page<ProductCatalogQueryResult>

    fun findByNameContaining(
        name: String,
        pageable: Pageable,
    ): Page<ProductCatalogQueryResult>
} 
