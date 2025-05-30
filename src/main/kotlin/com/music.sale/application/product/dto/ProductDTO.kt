package com.music.sale.application.product.dto

import com.music.sale.domain.product.Product
import com.music.sale.domain.product.enum.ProductCondition

// UseCase의 출력을 나타내는 DTO
data class ProductOutput(
    val id: Long,
    val name: String,
    val description: String,
    val price: Long,
    val stock: Int,
    val updatedAt: String
)

// 상품 생성 입력 DTO
data class CreateProductInput(
    val name: String,
    val description: String,
    val price: Long,
    val stock: Int
) {
    fun toDomain(): Product {
        return Product.create(
            name = name,
            description = description,
            price = price.toInt(),
            stock = stock
        )
    }
}

// 상품 수정 입력 DTO
data class UpdateProductInput(
    val id: Long,
    val name: String,
    val description: String,
    val price: Long,
    val stock: Int
) {
    fun toDomain(): Product {
        return Product.create(
            id = id,
            name = name,
            description = description,
            price = price.toInt(),
            stock = stock
        )
    }
}

// 상품 검색 입력 DTO
data class SearchProductInput(
    val productType: String? = null,
    val keyword: String? = null,
    val sellerId: Long? = null,
    val condition: ProductCondition? = null,
    val inStock: Boolean? = null
) 