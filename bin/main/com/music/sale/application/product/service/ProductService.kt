// Copyright (C) 2024 Your Name or Company
package com.music.sale.application.product.service

import com.music.sale.application.category.service.CategoryService
import com.music.sale.application.product.dto.CreateProductInput
import com.music.sale.application.product.dto.ProductOutput
import com.music.sale.application.product.dto.SearchProductInput
import com.music.sale.application.product.dto.UpdateProductInput
import com.music.sale.application.product.mapper.ProductMapper
import com.music.sale.application.product.port.inport.ProductUseCase
import com.music.sale.application.product.port.outport.ProductPort
import com.music.sale.application.seller.port.outport.SellerPort
import com.music.sale.common.Pageable
import com.music.sale.domain.store.model.Store
import org.springframework.data.domain.Page
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class ProductService(
    private val port: ProductPort,
    private val mapper: ProductMapper,
    private val categoryService: CategoryService,
    private val sellerPort: SellerPort,
) : ProductUseCase {
    /** 상품 관련 비즈니스 로직을 처리하는 서비스 클래스 ProductPort를 통해 영속성 계층과 상호작용 */
    override fun getProducts(pageable: Pageable): Page<ProductOutput> {
        return port.findAll(pageable).map { mapper.toOutput(it) }
    }

    override fun searchProducts(
        input: SearchProductInput,
        pageable: Pageable,
    ): Page<ProductOutput> {
        return port.searchProducts(
            searchCondition = mapper.toSearchProductCondition(input),
            pageable = pageable,
        )
            .map { mapper.toOutput(it) }
    }

    override fun createProduct(input: CreateProductInput): ProductOutput {
        val seller = sellerPort.findById(input.sellerId)
        val store = Store(input.storeId) // TODO
        val saveCondition = mapper.toSaveProductCondition(input, seller, store)

        val savedProduct = port.save(saveCondition)

        return mapper.toOutput(savedProduct)
    }

    override fun updateProduct(input: UpdateProductInput): ProductOutput {
        val product = port.findById(input.id) ?: throw IllegalArgumentException("Product not found")

        val categoryId = input.catalogId ?: throw IllegalArgumentException("catalogId is required")

        val category = categoryService.getCategoryById(categoryId)

        val updatedProduct =
            product.copy(
                catalogId = input.catalogId,
                name = input.name ?: product.name(),
                category = category,
                price = input.price ?: product.price,
                condition = input.condition ?: product.condition,
                conditionGrade = input.conditionGrade ?: product.conditionGrade,
                stockQuantity = input.stockQuantity ?: product.stockQuantity,
                status = input.status ?: product.status,
                attributes = input.attributes ?: product.attributes(),
            )

        return mapper.toOutput(port.update(updatedProduct))
    }

    override fun deleteProduct(id: Long): ProductOutput {
        val product = port.findById(id) ?: throw IllegalArgumentException("Product not found")
        port.deleteById(id)
        return mapper.toOutput(product)
    }
}
