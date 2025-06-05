package com.music.sale.adapter.persistence.product

import com.music.sale.adapter.persistence.product.repository.ProductCatalogRepository
import com.music.sale.adapter.persistence.product.repository.ProductItemRepository
import com.music.sale.application.product.port.out.ProductPort
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
@Transactional
class ProductPersistenceAdapter(
    private val productCatalogRepository: ProductCatalogRepository,
    private val productItemRepository: ProductItemRepository,
    private val productCategoryRepository: ProductCategoryRepository
) : ProductPort