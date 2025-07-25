package com.music.sale.persistence.product

import com.music.sale.application.product.dto.SaveProductItemCondition
import com.music.sale.application.product.port.outport.ProductCommandPort
import com.music.sale.domain.product.Product
import com.music.sale.persistence.product.mapper.ProductCommandPersistenceMapper
import com.music.sale.persistence.product.repository.ProductCatalogCommandJpaRepository
import com.music.sale.persistence.product.repository.ProductCatalogQueryJooqRepository
import com.music.sale.persistence.product.repository.ProductItemCommandJpaRepository
import org.springframework.stereotype.Component
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
@Component
@Transactional
open class ProductCommandPersistenceAdapter(
    private val productCatalogCommandJpaRepository: ProductCatalogCommandJpaRepository,
    private val productCatalogQueryJooqRepository: ProductCatalogQueryJooqRepository,
    private val productItemCommandJpaRepository: ProductItemCommandJpaRepository,
    private val mapper: ProductCommandPersistenceMapper,
) : ProductCommandPort {
    override fun save(productCondition: SaveProductItemCondition): Product {
        val catalogId = productCondition.catalogId
        val productCatalogQueryResult =
            productCatalogQueryJooqRepository.findById(
                catalogId,
            ) ?: throw IllegalArgumentException("Product catalog not found with id: $catalogId")

        val productItemEntity = mapper.toEntity(productCondition, productCatalogQueryResult)

        val savedEntity = productItemCommandJpaRepository.save(productItemEntity)
        return mapper.toDomain(savedEntity)
    }

    override fun update(product: Product): Product {
        val catalogQueryResult =
            productCatalogQueryJooqRepository.findById(product.catalog.id) ?: throw IllegalArgumentException(
                "Product catalog not found with id: ${product.catalog.id}",
            )
        val entity = mapper.toEntity(product, catalogQueryResult)
        val savedEntity = productItemCommandJpaRepository.save(entity)
        return mapper.toDomain(savedEntity)
    }

    override fun deleteById(id: Long) {
        productItemCommandJpaRepository.deleteById(id)
    }
} 
