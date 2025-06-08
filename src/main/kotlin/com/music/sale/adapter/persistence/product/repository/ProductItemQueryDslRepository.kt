package com.music.sale.adapter.persistence.product.repository

import com.music.sale.adapter.persistence.product.dto.SearchProductCondition
import com.music.sale.adapter.persistence.product.entity.ProductItemEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface ProductItemQueryDslRepository {
    fun searchProducts(
        searchCriteria: SearchProductCondition,
        pageable: Pageable
    ): Page<ProductItemEntity>
} 