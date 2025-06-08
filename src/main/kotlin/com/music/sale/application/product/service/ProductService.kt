package com.music.sale.application.product.service

import com.music.sale.application.category.service.CategoryService
import com.music.sale.application.product.dto.CreateProductInput
import com.music.sale.application.product.dto.ProductOutput
import com.music.sale.application.product.dto.SearchProductInput
import com.music.sale.application.product.dto.UpdateProductInput
import com.music.sale.application.product.mapper.ProductMapper
import com.music.sale.application.product.port.`in`.ProductUseCase
import com.music.sale.application.product.port.out.ProductPort
import com.music.sale.common.Pageable
import org.springframework.data.domain.Page
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class ProductService(
    private val port: ProductPort,
    private val mapper: ProductMapper,
    private val categoryService: CategoryService
) : ProductUseCase {
    /**
     * 상품 관련 비즈니스 로직을 처리하는 서비스 클래스
     * ProductPort를 통해 영속성 계층과 상호작용
     */
    override fun getProducts(pageable: Pageable): Page<ProductOutput> {
        return port.findAll(pageable).map {
            mapper.toOutput(it)
        }
    }

    override fun searchProducts(
        input: SearchProductInput,
        pageable: Pageable
    ): Page<ProductOutput> {
        return port.searchProducts(
            searchCriteria = mapper.toSearchProductCondition(input),
            pageable = pageable
        ).map {
            mapper.toOutput(it)
        }
    }

    override fun createProduct(input: CreateProductInput): ProductOutput {
        categoryService.getCategoryById(input.catalogId)
        return mapper.toOutput(port.save(mapper.toSaveProductCondition(input)))
    }

    override fun updateProduct(input: UpdateProductInput): ProductOutput {
        val product = port.findById(input.id) ?: throw IllegalArgumentException("Product not found")
        input.catalogId?.let { categoryService.getCategoryById(it) }
            ?: throw IllegalArgumentException("Category not found")

        return mapper.toOutput(port.save(mapper.toSaveProductCondition(product, input)))
    }

    override fun deleteProduct(id: Long): ProductOutput {
        val product = port.findById(id) ?: throw IllegalArgumentException("Product not found")
        port.delete(product)
        return mapper.toOutput(product)
    }

}