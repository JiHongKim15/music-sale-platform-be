package com.music.sale.adapter.web.product.response

import com.music.sale.application.product.port.`in`.ProductUseCaseDTO

data class ProductResponse(
    val id: Long,
    val name: String,
    val description: String,
    val price: Long,
    val stock: Int,
    val updatedAt: String
) {
    companion object {
        fun from(useCaseDTO: ProductUseCaseDTO) = ProductResponse(
            id = useCaseDTO.id,
            name = useCaseDTO.name,
            description = useCaseDTO.description,
            price = useCaseDTO.price,
            stock = useCaseDTO.stock,
            updatedAt = useCaseDTO.updatedAt
        )
    }
}

data class CreateProductResponse(
    val id: Long,
    val name: String
) {
    companion object {
        fun from(productUseCaseDTO: ProductUseCaseDTO) = CreateProductResponse(
            id = productUseCaseDTO.id,
            name = productUseCaseDTO.name
        )
    }
}

data class UpdateProductResponse(
    val id: Long,
    val name: String,
    val updatedAt: String
) {
    companion object {
        fun from(productUseCaseDTO: ProductUseCaseDTO) = UpdateProductResponse(
            id = productUseCaseDTO.id,
            name = productUseCaseDTO.name,
            updatedAt = productUseCaseDTO.updatedAt.toString()
        )
    }
} 