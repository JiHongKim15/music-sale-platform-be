package com.music.sale.application.product.port.inport

import com.music.sale.application.product.dto.CreateProductInput
import com.music.sale.application.product.dto.ProductOutput
import com.music.sale.application.product.dto.UpdateProductInput

interface ProductCommandUseCase {
    fun createProduct(input: CreateProductInput): ProductOutput

    fun updateProduct(input: UpdateProductInput): ProductOutput

    fun deleteProduct(id: Long): ProductOutput
} 
