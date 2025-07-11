// Copyright (C) 2024 Your Name or Company
package com.music.sale.application.product.port.inport

import com.music.sale.application.product.dto.CreateProductInput
import com.music.sale.application.product.dto.ProductOutput
import com.music.sale.application.product.dto.SearchProductInput
import com.music.sale.application.product.dto.UpdateProductInput
import com.music.sale.common.Pageable
import org.springframework.data.domain.Page

interface ProductUseCase {
    fun getProducts(pageable: Pageable): Page<ProductOutput>

    fun getProductById(id: Long): ProductOutput?

    fun searchProducts(
        input: SearchProductInput,
        pageable: Pageable,
    ): Page<ProductOutput>

    fun createProduct(input: CreateProductInput): ProductOutput

    fun updateProduct(input: UpdateProductInput): ProductOutput

    fun deleteProduct(id: Long): ProductOutput
}
