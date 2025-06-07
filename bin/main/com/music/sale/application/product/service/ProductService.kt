package com.music.sale.application.product.service

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
    private val productPort: ProductPort,
    private val productMapper: ProductMapper
) : ProductUseCase {
    /**
     * 상품 관련 비즈니스 로직을 처리하는 서비스 클래스
     * ProductPort를 통해 영속성 계층과 상호작용
     */
    override fun getProducts(pageable: Pageable): Page<ProductOutput> {
        return productPort.findAll(pageable).map {
            productMapper.toOutput(it)
        }
    }

    override fun searchProducts(
        input: SearchProductInput,
        pageable: Pageable
    ): Page<ProductOutput> {
        return productPort.searchProducts(
            category = input.productType,
            keyword = input.keyword,
            sellerId = input.sellerId,
            condition = input.condition,
            inStock = input.inStock,
            pageable = pageable
        ).map {
            productMapper.toOutput(it)
        }
    }

    override fun createProduct(input: CreateProductInput): ProductOutput {
        val product = productMapper.toDomain(input)
        return productMapper.toOutput(productPort.save(product))
    }

    override fun updateProduct(input: UpdateProductInput): ProductOutput {
        val product = productMapper.toDomain(input)
        return productMapper.toOutput(productPort.save(product))
    }

    override fun deleteProduct(id: Long): ProductOutput {
        val product = productPort.findById(id) ?: throw IllegalArgumentException("Product not found")
        productPort.delete(product)
        return productMapper.toOutput(product)
    }

}