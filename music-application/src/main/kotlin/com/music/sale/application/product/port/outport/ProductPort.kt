// Copyright (C) 2024 Your Name or Company
package com.music.sale.application.product.port.outport

import com.music.sale.application.product.dto.SaveProductItemCondition
import com.music.sale.application.product.dto.SearchProductCondition
import com.music.sale.common.Pageable
import com.music.sale.domain.product.Product
import org.springframework.data.domain.Page

interface ProductPort {
    fun findAll(pageable: Pageable): Page<Product>

    fun searchProducts(
        searchCondition: SearchProductCondition,
        pageable: Pageable,
    ): Page<Product>

    fun save(productCondition: SaveProductItemCondition): Product

    fun update(product: Product): Product

    fun findById(id: Long): Product?

    fun deleteById(id: Long)
}
