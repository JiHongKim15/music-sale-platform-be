// Copyright (C) 2024 Your Name or Company
package com.music.sale.persistence.product

import com.music.sale.application.product.dto.SaveProductItemCondition
import com.music.sale.application.product.dto.SearchProductCondition
import com.music.sale.application.product.port.outport.ProductPort
import com.music.sale.common.Pageable
import com.music.sale.common.SortDirection
import com.music.sale.domain.product.Product
import com.music.sale.persistence.product.mapper.ProductPersistenceMapper
import com.music.sale.persistence.product.repository.ProductCatalogRepository
import com.music.sale.persistence.product.repository.ProductItemQueryDslRepository
import com.music.sale.persistence.product.repository.ProductItemRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
@Transactional
open class ProductPersistenceAdapter(
        private val productCatalogRepository: ProductCatalogRepository,
        private val productItemRepository: ProductItemRepository,
        private val productItemQueryDslRepository: ProductItemQueryDslRepository,
        private val mapper: ProductPersistenceMapper,
) : ProductPort {
    @Transactional(readOnly = true)
    override fun findAll(pageable: Pageable): Page<Product> {
        val sortDirection = pageable.sortDirection ?: SortDirection.DESC
        val sortProperty = pageable.sort ?: "createdAt"
        val pageNumber = if (pageable.pageNumber < 0) 0 else pageable.pageNumber
        val springPageable =
                PageRequest.of(
                        pageNumber,
                        pageable.pageSize,
                        Sort.by(Sort.Direction.valueOf(sortDirection.name), sortProperty),
                )
        return productItemRepository.findAll(springPageable).map { mapper.toDomain(it) }
    }

    @Transactional(readOnly = true)
    override fun searchProducts(
            searchCondition: SearchProductCondition,
            pageable: Pageable,
    ): Page<Product> {
        val sortDirection = pageable.sortDirection ?: SortDirection.DESC
        val sortProperty = pageable.sort ?: "createdAt"
        val pageNumber = if (pageable.pageNumber < 0) 0 else pageable.pageNumber
        val springPageable =
                PageRequest.of(
                        pageNumber,
                        pageable.pageSize,
                        Sort.by(Sort.Direction.valueOf(sortDirection.name), sortProperty),
                )

        return productItemQueryDslRepository.searchProducts(searchCondition, springPageable).map {
            mapper.toDomain(it)
        }
    }

    override fun save(productCondition: SaveProductItemCondition): Product {
        val catalogId = productCondition.catalogId
        val productCatalogEntity =
                productCatalogRepository.findById(catalogId).orElseThrow {
                    IllegalArgumentException("Product catalog not found with id: $catalogId")
                }

        val productItemEntity = mapper.toEntity(productCondition, productCatalogEntity)

        val savedEntity = productItemRepository.save(productItemEntity)
        return mapper.toDomain(savedEntity)
    }

    override fun update(product: Product): Product {
        val catalogEntity =
                productCatalogRepository.findById(product.catalogId).orElseThrow {
                    IllegalArgumentException(
                            "Product catalog not found with id: ${product.catalogId}",
                    )
                }
        val entity = mapper.toEntity(product, catalogEntity)
        val savedEntity = productItemRepository.save(entity)
        return mapper.toDomain(savedEntity)
    }

    override fun findById(id: Long): Product {
        return productItemRepository.findById(id).map { mapper.toDomain(it) }.orElse(null)
    }

    override fun deleteById(id: Long) {
        productItemRepository.deleteById(id)
    }
}
