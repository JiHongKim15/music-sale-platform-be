package com.music.sale.adapter.persistence.product

import com.music.sale.adapter.persistence.product.entity.ProductItemEntity
import com.music.sale.adapter.persistence.product.repository.ProductCatalogRepository
import com.music.sale.adapter.persistence.product.repository.ProductItemRepository
import com.music.sale.application.product.port.out.ProductPort
import com.music.sale.common.Pageable
import com.music.sale.domain.product.Product
import com.music.sale.domain.product.enum.ProductCondition
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
@Transactional
class ProductPersistenceAdapter(
    private val productCatalogRepository: ProductCatalogRepository,
    private val productItemRepository: ProductItemRepository
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
        return productItemRepository.findAll(springPageable).map { it.toDomain() }
    }

    override fun searchProducts(
        category: String?,
        keyword: String?,
        sellerId: Long?,
        condition: ProductCondition?,
        inStock: Boolean?,
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
        // category는 실제 Category 객체로 변환이 필요하지만, 임시로 무시
        return productItemRepository.findAll(springPageable).map { it.toDomain() }
    }

    override fun save(product: Product): Product {
        val entity = ProductItemEntity.fromDomain(product)
        val savedEntity = productItemRepository.save(entity)
        return savedEntity.toDomain()
    }

    override fun findById(id: Long): Product? {
        return productItemRepository.findById(id).map { it.toDomain() }.orElse(null)
    }

    override fun delete(product: Product) {
        val entity = ProductItemEntity.fromDomain(product)
        productItemRepository.delete(entity)
    }
}