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
import org.springframework.data.domain.PageImpl
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
        // 임시로 빈 리스트 반환
        return ResponseEntity.ok(ApiResponse.success(PageImpl(emptyList())))
    }

    //상품 검색
    @GetMapping("/search")
    fun searchProducts(
        @ModelAttribute request: SearchProductRequest,
        @ModelAttribute pageRequest: Pageable
    ): ResponseEntity<ApiResponse<Page<ProductResponse>>> {
        // 임시로 빈 리스트 반환
        return ResponseEntity.ok(ApiResponse.success(PageImpl(emptyList())))
    }

    // 상품 등록
    @PostMapping
    fun createProduct(@RequestBody request: CreateProductRequest): ResponseEntity<ApiResponse<CreateProductResponse>> {
        // 임시로 요청값 반환
        return ResponseEntity.ok(
            ApiResponse.success(
                data = CreateProductResponse(0L, request.name),
                code = ResponseCode.PRODUCT_CREATED.code
            )
        )
    }

    // 상품 삭제
    @DeleteMapping("/{id}")
    fun deleteProduct(@PathVariable id: Long): ResponseEntity<ApiResponse<Unit>> {
        // 임시로 성공 반환
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
        // 임시로 요청값 반환
        return ResponseEntity.ok(
            ApiResponse.success(
                data = UpdateProductResponse(id, request.name, ""),
                code = ResponseCode.PRODUCT_UPDATED.code
            )
        )
    }
}