package com.music.sale.web.product

import com.music.sale.application.product.dto.ProductOutput
import com.music.sale.application.product.port.inport.ProductUseCase
import com.music.sale.common.ResponseCode
import com.music.sale.web.common.ApiResponse
import com.music.sale.web.product.mapper.ProductWebMapper
import com.music.sale.web.product.request.CreateProductRequest
import com.music.sale.web.product.request.GetProductRequest
import com.music.sale.web.product.request.SearchProductRequest
import com.music.sale.web.product.request.UpdateProductRequest
import org.springframework.data.domain.Page
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/product")
class ProductController(private val useCase: ProductUseCase, private val mapper: ProductWebMapper) {
    @GetMapping
    fun getProducts(
        @ModelAttribute pageRequest: GetProductRequest,
    ): ResponseEntity<ApiResponse<Page<ProductOutput>>> {
        return ResponseEntity.ok(
            ApiResponse.success(data = useCase.getProducts(pageRequest.toPageable())),
        )
    }

    @GetMapping("/{id}")
    fun getProduct(
        @PathVariable id: Long,
    ): ResponseEntity<ApiResponse<ProductOutput>> {
        val product = useCase.getProductById(id) ?: return ResponseEntity.notFound().build()

        return ResponseEntity.ok(
            ApiResponse.success(data = product),
        )
    }

    @GetMapping("/search")
    fun searchProducts(
        @ModelAttribute request: SearchProductRequest,
    ): ResponseEntity<ApiResponse<Page<ProductOutput>>> {
        return ResponseEntity.ok(
            ApiResponse.success(
                data =
                    useCase.searchProducts(
                        mapper.toSearchCondition(request),
                        request.toPageable(),
                    ),
            ),
        )
    }

    @PostMapping
    fun createProduct(
        @RequestBody request: CreateProductRequest,
    ): ResponseEntity<ApiResponse<ProductOutput>> {
        return ResponseEntity.ok(
            ApiResponse.success(
                data = useCase.createProduct(mapper.toCreateProductInput(request)),
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
                data = useCase.updateProduct(mapper.toUpdateProductInput(id, request)),
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
                data = useCase.deleteProduct(id),
                code = ResponseCode.PRODUCT_DELETED.code,
            ),
        )
    }
}
