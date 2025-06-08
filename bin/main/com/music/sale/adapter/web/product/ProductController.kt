package com.music.sale.adapter.web.product

import com.music.sale.adapter.web.common.ApiResponse
import com.music.sale.adapter.web.product.mapper.ProductWebMapper
import com.music.sale.adapter.web.product.request.CreateProductRequest
import com.music.sale.adapter.web.product.request.SearchProductRequest
import com.music.sale.adapter.web.product.request.UpdateProductRequest
import com.music.sale.application.product.dto.ProductOutput
import com.music.sale.application.product.port.`in`.ProductUseCase
import com.music.sale.common.Pageable
import com.music.sale.common.ResponseCode
import org.springframework.data.domain.Page
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/products")
class ProductController(
    private val useCase: ProductUseCase,
    private val mapper: ProductWebMapper
) {
    @GetMapping
    fun getProducts(
        @ModelAttribute pageRequest: Pageable
    ): ResponseEntity<ApiResponse<Page<ProductOutput>>> {
        return ResponseEntity.ok(
            ApiResponse.success(
                data = useCase.getProducts(pageRequest)
            )
        )
    }

    @GetMapping("/search")
    fun searchProducts(
        @ModelAttribute request: SearchProductRequest,
        @ModelAttribute pageRequest: Pageable
    ): ResponseEntity<ApiResponse<Page<ProductOutput>>> {

        return ResponseEntity.ok(
            ApiResponse.success(
                data = useCase.searchProducts(mapper.toSearchCondition(request), pageRequest)
            )
        )
    }

    @PostMapping
    fun createProduct(@RequestBody request: CreateProductRequest): ResponseEntity<ApiResponse<ProductOutput>> {
        return ResponseEntity.ok(
            ApiResponse.success(
                data = useCase.createProduct(mapper.toCreateProductInput(request)),
                code = ResponseCode.PRODUCT_CREATED.code
            )
        )
    }

    @PutMapping("/{id}")
    fun updateProduct(
        @PathVariable id: Long,
        @RequestBody request: UpdateProductRequest
    ): ResponseEntity<ApiResponse<ProductOutput>> {
        return ResponseEntity.ok(
            ApiResponse.success(
                data = useCase.updateProduct(mapper.toUpdateProductInput(id, request)),
                code = ResponseCode.PRODUCT_UPDATED.code
            )
        )
    }

    @DeleteMapping("/{id}")
    fun deleteProduct(@PathVariable id: Long): ResponseEntity<ApiResponse<ProductOutput>> {
        return ResponseEntity.ok(
            ApiResponse.success(
                data = useCase.deleteProduct(id),
                code = ResponseCode.PRODUCT_DELETED.code
            )
        )
    }
}