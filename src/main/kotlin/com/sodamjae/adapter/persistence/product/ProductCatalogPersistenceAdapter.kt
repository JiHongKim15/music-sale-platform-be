package com.sodamjae.adapter.persistence.product

import com.sodamjae.adapter.persistence.product.mapper.ProductCatalogMapper
import com.sodamjae.adapter.persistence.product.repository.ProductCatalogRepository
import com.sodamjae.application.product.port.ProductCatalogPort
import com.sodamjae.domain.product.ProductCatalog
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Component

/**
 * 제품 카탈로그 도메인과 리포지토리 계층 사이의 어댑터
 * 도메인 모델과 영속성 계층을 완전히 분리
 */
@Component
class ProductCatalogPersistenceAdapter(
    private val productCatalogRepository: ProductCatalogRepository,
    private val productCatalogMapper: ProductCatalogMapper
) : ProductCatalogPort {

    override fun save(productCatalog: ProductCatalog): ProductCatalog {
        val entity = productCatalogMapper.toEntity(productCatalog)
        val savedEntity = productCatalogRepository.save(entity)
        return productCatalogMapper.toDomain(savedEntity)
    }

    override fun findById(id: Long): ProductCatalog? {
        return productCatalogRepository.findById(id)
            .map { productCatalogMapper.toDomain(it) }
            .orElse(null)
    }

    override fun findByProductType(productType: String, pageable: Pageable): Page<ProductCatalog> {
        return productCatalogRepository.findByProductType(productType, pageable)
            .map { productCatalogMapper.toDomain(it) }
    }

    override fun findByNameContaining(keyword: String, pageable: Pageable): Page<ProductCatalog> {
        return productCatalogRepository.findByNameContaining(keyword, pageable)
            .map { productCatalogMapper.toDomain(it) }
    }

    override fun searchCatalogs(
        productType: String?,
        keyword: String?,
        pageable: Pageable
    ): Page<ProductCatalog> {
        return productCatalogRepository.searchCatalogs(productType, keyword, pageable)
            .map { productCatalogMapper.toDomain(it) }
    }

    override fun delete(id: Long) {
        productCatalogRepository.deleteById(id)
    }
}