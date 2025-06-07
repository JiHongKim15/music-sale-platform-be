package com.music.sale.adapter.web.product.request

data class CreateProductRequest(
    val name: String,
    val description: String,
    val price: Long,
    val stock: Int
)

data class UpdateProductRequest(
    val name: String,
    val description: String,
    val price: Long,
    val stock: Int
)

data class SearchProductRequest(
    val keyword: String? = null,
    val minPrice: Long? = null,
    val maxPrice: Long? = null,
    val inStock: Boolean? = null
) 