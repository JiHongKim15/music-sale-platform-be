package com.music.sale.application.product.port.`in`

interface ProductUseCase {
    fun getProducts(pageable: Pageable): Page<ProductUseCaseDTO>
    fun createProduct(createProductUseCaseDTO: CreateProductUseCaseDTO): ProductUseCaseDTO
    fun updateProduct(updateProductUseCaseDTO: UpdateProductUseCaseDTO): ProductUseCaseDTO
    fun deleteProduct(id: Long)
}