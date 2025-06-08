package com.music.sale.application.product.port.out

import com.music.sale.adapter.persistence.product.dto.SaveProductCondition
import com.music.sale.adapter.persistence.product.dto.SearchProductCondition
import com.music.sale.common.Pageable
import com.music.sale.domain.product.Product
import org.springframework.data.domain.Page

interface ProductPort {
    fun findAll(pageable: Pageable): Page<Product>
    fun searchProducts(
        searchCriteria: SearchProductCondition,
        pageable: Pageable
    ): Page<Product>

    fun save(product: SaveProductCondition): Product
    fun findById(id: Long): Product?
    fun delete(product: Product)
}