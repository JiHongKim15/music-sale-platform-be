package com.music.sale.adapter.web.product.request

import com.music.sale.application.product.port.`in`.CreateProductUseCaseDTO
import com.music.sale.application.product.port.`in`.SearchProductUseCaseDTO
import com.music.sale.application.product.port.`in`.UpdateProductUseCaseDTO

data class CreateProductRequest(
    val name: String,
    val description: String,
    val price: Long,
    val stock: Int
) {
    fun toProductUseCase() = CreateProductUseCaseDTO(
        name = name,
        description = description,
        price = price,
        stock = stock
    )
}

data class UpdateProductRequest(
    val name: String,
    val description: String,
    val price: Long,
    val stock: Int
) {
    fun toProductUseCase(id: Long) = UpdateProductUseCaseDTO(
        id = id,
        name = name,
        description = description,
        price = price,
        stock = stock
    )
}

data class SearchProductRequest(
    val keyword: String? = null,
    val minPrice: Long? = null,
    val maxPrice: Long? = null,
    val inStock: Boolean? = null
) {
    fun toProductUseCase() = SearchProductUseCaseDTO(
        keyword = keyword,
        minPrice = minPrice,
        maxPrice = maxPrice,
        inStock = inStock
    )
} 