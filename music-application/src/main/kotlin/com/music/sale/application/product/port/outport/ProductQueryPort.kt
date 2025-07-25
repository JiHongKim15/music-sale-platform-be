package com.music.sale.application.product.port.outport

import com.music.sale.application.product.dto.SearchProductCondition
import com.music.sale.common.Pageable
import com.music.sale.domain.product.Product
import org.springframework.data.domain.Page

interface ProductQueryPort {
    fun findAll(pageable: Pageable): Page<Product>

    fun searchProducts(
        searchCondition: SearchProductCondition,
        pageable: Pageable,
    ): Page<Product>

    fun findById(id: Long): Product?
} 
