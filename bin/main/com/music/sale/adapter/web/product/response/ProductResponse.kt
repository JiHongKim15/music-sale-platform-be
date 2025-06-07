package com.music.sale.adapter.web.product.response

data class ProductResponse(
    val id: Long,
    val name: String,
    val description: String,
    val price: Long,
    val stock: Int,
    val updatedAt: String
)

data class CreateProductResponse(
    val id: Long,
    val name: String
)

data class UpdateProductResponse(
    val id: Long,
    val name: String,
    val updatedAt: String
) 