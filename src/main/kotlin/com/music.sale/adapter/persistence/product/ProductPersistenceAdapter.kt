package com.music.sale.adapter.persistence.product

import com.music.sale.adapter.persistence.product.repository.ProductCatalogRepository
import com.music.sale.adapter.persistence.product.repository.ProductItemRepository
import com.music.sale.application.product.port.out.ProductCatalogPort
import com.music.sale.application.product.port.out.ProductItemPort
import com.music.sale.domain.product.ProductCatalog
import com.music.sale.domain.product.ProductInfo
import com.music.sale.domain.product.enum.ProductCondition
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
@Transactional
class ProductPersistenceAdapter(
    private val productCatalogRepository: ProductCatalogRepository,
    private val productItemRepository: ProductItemRepository
) : ProductCatalogPort, ProductItemPort {

    // ProductCatalogPort 구현
    override fun save(productCatalog: ProductCatalog): ProductCatalog {
        return productCatalogRepository.save(productCatalog)
    }

    override fun findById(id: Long): ProductCatalog? {
        return productCatalogRepository.findById(id).orElse(null)
    }

    override fun findByProductType(productType: String, pageable: Pageable): Page<ProductCatalog> {
        return productCatalogRepository.findByProductType(productType, pageable)
    }

    override fun findByNameContaining(keyword: String, pageable: Pageable): Page<ProductCatalog> {
        return productCatalogRepository.findByNameContaining(keyword, pageable)
    }

    override fun searchCatalogs(
        productType: String?,
        keyword: String?,
        pageable: Pageable
    ): Page<ProductCatalog> {
        return productCatalogRepository.searchCatalogs(
            productType = productType,
            keyword = keyword,
            pageable = pageable
        )
    }

    override fun delete(id: Long) {
        productCatalogRepository.deleteById(id)
    }

    // ProductItemPort 구현
    override fun save(productInfo: ProductInfo): ProductInfo {
        return productItemRepository.save(productInfo)
    }

    override fun findById(id: Long): ProductInfo? {
        return productItemRepository.findById(id).orElse(null)
    }

    override fun findByProductCatalogId(catalogId: Long, pageable: Pageable): Page<ProductInfo> {
        return productItemRepository.findByProductCatalogId(catalogId, pageable)
    }

    override fun findBySellerId(sellerId: Long, pageable: Pageable): Page<ProductInfo> {
        return productItemRepository.findBySellerId(sellerId, pageable)
    }

    override fun findByStoreId(storeId: Long, pageable: Pageable): Page<ProductInfo> {
        return productItemRepository.findByStoreId(storeId, pageable)
    }

    override fun findByCondition(condition: ProductCondition, pageable: Pageable): Page<ProductInfo> {
        return productItemRepository.findByCondition(condition, pageable)
    }

    override fun searchItems(
        catalogId: Long?,
        sellerId: Long?,
        condition: ProductCondition?,
        inStock: Boolean?,
        pageable: Pageable
    ): Page<ProductInfo> {
        return productItemRepository.searchItems(
            catalogId = catalogId,
            sellerId = sellerId,
            condition = condition,
            inStock = inStock,
            pageable = pageable
        )
    }

    override fun delete(id: Long) {
        productItemRepository.deleteById(id)
    }
} 