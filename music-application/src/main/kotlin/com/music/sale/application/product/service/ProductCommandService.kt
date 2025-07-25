package com.music.sale.application.product.service

import com.music.sale.application.category.service.CategoryService
import com.music.sale.application.product.dto.CreateProductInput
import com.music.sale.application.product.dto.ProductOutput
import com.music.sale.application.product.dto.UpdateProductInput
import com.music.sale.application.product.mapper.ProductMapper
import com.music.sale.application.product.port.inport.ProductCommandUseCase
import com.music.sale.application.product.port.outport.ProductCommandPort
import com.music.sale.application.product.port.outport.ProductQueryPort
import com.music.sale.application.user.port.outport.UserPort
import com.music.sale.domain.store.Store
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
open class ProductCommandService(
    private val commandPort: ProductCommandPort,
    private val queryPort: ProductQueryPort,
    private val mapper: ProductMapper,
    private val categoryService: CategoryService,
    private val userPort: UserPort,
) : ProductCommandUseCase {
    override fun createProduct(input: CreateProductInput): ProductOutput {
        val seller =
            userPort.findById(input.sellerId)
                ?: throw IllegalArgumentException(
                    "Seller not found with id: ${input.sellerId}",
                )

        // TODO: Store 정보도 실제로 조회해야 함
        val store = Store(input.storeId)

        val saveCondition = mapper.toSaveProductCondition(input, seller, store)
        val savedProduct = commandPort.save(saveCondition)

        return mapper.toOutput(savedProduct)
    }

    override fun updateProduct(input: UpdateProductInput): ProductOutput {
        val product = queryPort.findById(input.id) ?: throw IllegalArgumentException("Product not found")

        val updatedProduct =
            product.copy(
                price = input.price ?: product.price,
                condition = input.condition ?: product.condition,
                conditionGrade = input.conditionGrade ?: product.conditionGrade,
                stockQuantity = input.stockQuantity ?: product.stockQuantity,
                status = input.status ?: product.status,
                customName = input.name ?: product.customName,
                customAttributes = input.attributes ?: product.customAttributes,
            )

        return mapper.toOutput(commandPort.update(updatedProduct))
    }

    override fun deleteProduct(id: Long): ProductOutput {
        val product = queryPort.findById(id) ?: throw IllegalArgumentException("Product not found")
        commandPort.deleteById(id)
        return mapper.toOutput(product)
    }
} 
