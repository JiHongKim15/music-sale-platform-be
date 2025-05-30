package com.music.sale.adapter.web.product

import com.music.sale.adapter.web.common.ApiResponse
import com.music.sale.adapter.web.common.ResponseCode
import com.music.sale.adapter.web.product.request.CreateProductRequest
import com.music.sale.adapter.web.product.request.SearchProductRequest
import com.music.sale.adapter.web.product.request.UpdateProductRequest
import com.music.sale.adapter.web.product.response.CreateProductResponse
import com.music.sale.adapter.web.product.response.ProductResponse
import com.music.sale.adapter.web.product.response.UpdateProductResponse
import com.music.sale.application.product.port.`in`.ProductUseCase
import com.music.sale.common.Pageable
import org.springframework.data.domain.Page
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/products")
class ProductController(
    private val productUseCase: ProductUseCase
) {
    //상품 조회
    @GetMapping
    fun getProducts(
        @ModelAttribute pageRequest: Pageable
    ): ResponseEntity<ApiResponse<Page<ProductResponse>>> {
        val result = productUseCase.getProducts(pageable = pageRequest)
        return ResponseEntity.ok(ApiResponse.success(result.map { ProductResponse.from(it) }))
    }

    //상품 검색
    @GetMapping("/search")
    fun searchProducts(
        @ModelAttribute request: SearchProductRequest,
        @ModelAttribute pageRequest: Pageable
    ): ResponseEntity<ApiResponse<Page<ProductResponse>>> {
        val result = productUseCase.searchProducts(
            searchProductUseCaseDTO = request.toProductUseCase(),
            pageable = pageRequest
        )
        return ResponseEntity.ok(ApiResponse.success(result.map { ProductResponse.from(it) }))
    }

    // 상품 등록
    @PostMapping
    fun createProduct(@RequestBody request: CreateProductRequest): ResponseEntity<ApiResponse<CreateProductResponse>> {
        val result = productUseCase.createProduct(request.toProductUseCase())
        return ResponseEntity.ok(
            ApiResponse.success(
                data = CreateProductResponse.from(result),
                code = ResponseCode.PRODUCT_CREATED.code
            )
        )
    }

    // 상품 삭제
    @DeleteMapping("/{id}")
    fun deleteProduct(@PathVariable id: Long): ResponseEntity<ApiResponse<Unit>> {
        productUseCase.deleteProduct(id)
        return ResponseEntity.ok(
            ApiResponse.success(
                data = Unit,
                code = ResponseCode.PRODUCT_DELETED.code
            )
        )
    }

    // 상품 업데이트
    @PutMapping("/{id}")
    fun updateProduct(
        @PathVariable id: Long,
        @RequestBody request: UpdateProductRequest
    ): ResponseEntity<ApiResponse<UpdateProductResponse>> {
        val result = productUseCase.updateProduct(request.toProductUseCase(id))
        return ResponseEntity.ok(
            ApiResponse.success(
                data = UpdateProductResponse.from(result),
                code = ResponseCode.PRODUCT_UPDATED.code
            )
        )
    }
}