package com.music.sale.application.product.port.inport

import com.music.sale.application.product.dto.ProductOutput
import com.music.sale.application.product.dto.SearchProductInput
import com.music.sale.common.Pageable
import org.springframework.data.domain.Page

interface ProductQueryUseCase {
    fun getProducts(pageable: Pageable): Page<ProductOutput>

    fun getProductById(id: Long): ProductOutput?

    fun searchProducts(
        input: SearchProductInput,
        pageable: Pageable,
    ): Page<ProductOutput>
} 
