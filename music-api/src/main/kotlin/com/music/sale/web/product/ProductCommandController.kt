package com.music.sale.web.product

import com.music.sale.application.product.dto.ProductOutput
import com.music.sale.application.product.port.inport.ProductCommandUseCase
import com.music.sale.common.ApiResponse
import com.music.sale.common.ResponseCode
import com.music.sale.web.product.mapper.ProductCommandWebMapper
import com.music.sale.web.product.request.CreateProductRequest
import com.music.sale.web.product.request.UpdateProductRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/products")
class ProductCommandController(
    private val commandUseCase: ProductCommandUseCase,
    private val mapper: ProductCommandWebMapper,
) {
    @PostMapping
    fun createProduct(
        @RequestBody request: CreateProductRequest,
    ): ResponseEntity<ApiResponse<ProductOutput>> {
        return ResponseEntity.ok(
            ApiResponse.success(
                data = commandUseCase.createProduct(mapper.toCreateProductInput(request)),
                code = ResponseCode.PRODUCT_CREATED.code,
            ),
        )
    }

    @PutMapping("/{id}")
    fun updateProduct(
        @PathVariable id: Long,
        @RequestBody request: UpdateProductRequest,
    ): ResponseEntity<ApiResponse<ProductOutput>> {
        return ResponseEntity.ok(
            ApiResponse.success(
                data = commandUseCase.updateProduct(mapper.toUpdateProductInput(id, request)),
                code = ResponseCode.PRODUCT_UPDATED.code,
            ),
        )
    }

    @DeleteMapping("/{id}")
    fun deleteProduct(
        @PathVariable id: Long,
    ): ResponseEntity<ApiResponse<ProductOutput>> {
        return ResponseEntity.ok(
            ApiResponse.success(
                data = commandUseCase.deleteProduct(id),
                code = ResponseCode.PRODUCT_DELETED.code,
            ),
        )
    }
}
