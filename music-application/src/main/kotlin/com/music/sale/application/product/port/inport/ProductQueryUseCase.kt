package com.music.sale.application.product.port.inport

import com.music.sale.application.product.dto.ProductOutput
import com.music.sale.application.product.dto.SearchProductInput
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest

interface ProductQueryUseCase {
    fun getProducts(pageable: PageRequest): Page<ProductOutput>

    fun getProductById(id: Long): ProductOutput?

    fun searchProducts(
        input: SearchProductInput,
        pageable: PageRequest,
    ): Page<ProductOutput>
} 
