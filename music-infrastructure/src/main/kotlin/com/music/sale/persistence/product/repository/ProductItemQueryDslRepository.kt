// Copyright (C) 2024 Your Name or Company
package com.music.sale.persistence.product.repository

import com.music.sale.application.product.dto.SearchProductCondition
import com.music.sale.persistence.product.entity.ProductItemEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface ProductItemQueryDslRepository {
    fun searchProducts(
        searchCriteria: SearchProductCondition,
        pageable: Pageable,
    ): Page<ProductItemEntity>
}
