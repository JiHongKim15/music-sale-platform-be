package com.music.sale.web.product

import com.music.sale.application.product.dto.ProductOutput
import com.music.sale.application.product.port.inport.ProductQueryUseCase
import com.music.sale.common.ApiResponse
import com.music.sale.web.product.mapper.ProductQueryMapper
import com.music.sale.web.product.request.GetProductRequest
import com.music.sale.web.product.request.SearchProductRequest
import org.springframework.data.domain.Page
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/product")
class ProductQueryController(
    private val queryUseCase: ProductQueryUseCase,
    private val mapper: ProductQueryMapper,
) {
    @GetMapping
    fun getProducts(
        @ModelAttribute pageRequest: GetProductRequest,
    ): ResponseEntity<ApiResponse<Page<ProductOutput>>> {
        return ResponseEntity.ok(
            ApiResponse.success(data = queryUseCase.getProducts(pageRequest.toPageable())),
        )
    }

    @GetMapping("/{id}")
    fun getProduct(
        @PathVariable id: Long,
    ): ResponseEntity<ApiResponse<ProductOutput>> {
        val product = queryUseCase.getProductById(id) ?: return ResponseEntity.notFound().build()

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
                    queryUseCase.searchProducts(
                        mapper.toSearchCondition(request),
                        request.toPageable(),
                    ),
            ),
        )
    }
}
