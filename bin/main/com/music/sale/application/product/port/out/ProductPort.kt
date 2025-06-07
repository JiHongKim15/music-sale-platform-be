package com.music.sale.application.product.port.out

import com.music.sale.common.Pageable
import com.music.sale.domain.category.Category
import com.music.sale.domain.product.Product
import com.music.sale.domain.product.enum.ProductCondition
import org.springframework.data.domain.Page

interface ProductPort {
    fun findAll(pageable: Pageable): Page<Product>
    fun searchProducts(
        category: String?,
        keyword: String?,
        sellerId: Long?,
        condition: ProductCondition?,
        inStock: Boolean?,
        pageable: Pageable
    ): Page<Product>

    fun save(product: Product): Product
    fun findById(id: Long): Product?
    fun delete(product: Product)
}