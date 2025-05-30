package com.music.sale.adapter.web.product

import com.music.sale.application.product.port.`in`.ProductUseCase
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/products")
class ProductController(
    private val productUseCase: ProductUseCase
) {
    //상품 조회
    @GetMapping
    fun getProducts(): ResponseEntity<List<ProductResponse>> {
        return productUseCase.getProducts()
    }


    // 상품 등록
    @PostMapping
    fun createProduct(@RequestBody request: CreateProductRequest): ResponseEntity<ProductResponse> {
        productUseCase.createProduct(request.toProductUseCase())

    }

    // 상품 삭제
    @DeleteMapping("/{id}")
    fun deleteProduct(@PathVariable id: Long): ResponseEntity<Unit> {
        productUseCase.deleteProduct(id)
    }

    // 상품 업데이트
    @PutMapping("/{id}")
    fun updateProduct(
        @PathVariable id: Long,
        @RequestBody request: UpdateProductRequest
    ): ResponseEntity<ProductResponse> {
        return productUseCase.updateProduct(request.toProductUseCase(id))
    }
}