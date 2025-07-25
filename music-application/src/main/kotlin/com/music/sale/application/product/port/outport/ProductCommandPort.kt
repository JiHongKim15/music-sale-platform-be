package com.music.sale.application.product.port.outport

import com.music.sale.application.product.dto.SaveProductItemCondition
import com.music.sale.domain.product.Product

interface ProductCommandPort {
    fun save(productCondition: SaveProductItemCondition): Product

    fun update(product: Product): Product

    fun deleteById(id: Long)
} 
