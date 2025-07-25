package com.music.sale.persistence.product.repository

import com.music.sale.application.product.dto.SearchProductCondition
import com.music.sale.persistence.product.dto.ProductQueryResult
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface ProductQueryJooqRepository {
    fun findAll(pageable: Pageable): Page<ProductQueryResult>

    fun findById(id: Long): ProductQueryResult?

    fun searchProducts(
        searchCondition: SearchProductCondition,
        pageable: Pageable,
    ): Page<ProductQueryResult>
} 
