package com.music.sale.application.product.port.outport

import com.music.sale.application.product.dto.SearchProductCondition
import com.music.sale.domain.product.Product
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest

interface ProductQueryPort {
    fun findAll(pageable: PageRequest): Page<Product>

    fun searchProducts(
        searchCondition: SearchProductCondition,
        pageable: PageRequest,
    ): Page<Product>

    fun findById(id: Long): Product?
} 
