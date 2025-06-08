package com.music.sale.adapter.persistence.product

import com.music.sale.adapter.persistence.product.dto.SaveProductCondition
import com.music.sale.adapter.persistence.product.dto.SearchProductCondition
import com.music.sale.adapter.persistence.product.mapper.ProductPersistenceMapper
import com.music.sale.adapter.persistence.product.repository.ProductCatalogRepository
import com.music.sale.adapter.persistence.product.repository.ProductItemQueryDslRepository
import com.music.sale.adapter.persistence.product.repository.ProductItemRepository
import com.music.sale.application.product.port.out.ProductPort
import com.music.sale.common.Pageable
import com.music.sale.domain.product.Product
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
@Transactional
class ProductPersistenceAdapter(
    private val productCatalogRepository: ProductCatalogRepository,
    private val productItemRepository: ProductItemRepository,
    private val productItemQueryDslRepository: ProductItemQueryDslRepository,
    private val mapper: ProductPersistenceMapper,
) : ProductPort {
    override fun findAll(pageable: Pageable): Page<Product> {
        val springPageable = PageRequest.of(
            pageable.pageNumber,
            pageable.pageSize,
            Sort.by(
                Sort.Direction.valueOf(pageable.sortDirection.name),
                pageable.sort
            )
        )
        return productItemRepository.findAll(springPageable).map { mapper.toDomain(it) }
    }

    override fun searchProducts(
        searchCriteria: SearchProductCondition,
        pageable: Pageable
    ): Page<Product> {
        val springPageable = PageRequest.of(
            pageable.pageNumber,
            pageable.pageSize,
            Sort.by(
                Sort.Direction.valueOf(pageable.sortDirection.name),
                pageable.sort
            )
        )

        return productItemQueryDslRepository.searchProducts(searchCriteria, springPageable)
            .map { mapper.toDomain(it) }
    }

    override fun save(product: SaveProductCondition): Product {
        
        val savedEntity = productItemRepository.save(mapper.toEntity(product))
        return mapper.toDomain(savedEntity)
    }

    override fun findById(id: Long): Product? {
        return productItemRepository.findById(id).map { mapper.toDomain(it) }.orElse(null)
    }

    override fun delete(product: Product) {
        val entity = mapper.toEntity(product)
        productItemRepository.delete(entity)
    }
}