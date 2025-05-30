package com.music.sale.application.product.mapper

import com.music.sale.application.product.dto.ProductOutput
import com.music.sale.domain.product.Product
import org.springframework.stereotype.Component

@Component
class ProductMapper {
    fun toOutput(product: Product): ProductOutput {
        return ProductOutput(
            id = product.id,
            name = product.getCatalogInfo().getName(),
            description = product.getCatalogInfo().getAttributes()["description"] as? String ?: "",
            price = product.getPrice().toLong(),
            stock = product.getStockQuantity(),
            updatedAt = java.time.LocalDateTime.now().toString()
        )
    }
}