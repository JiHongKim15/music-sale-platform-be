package com.sodamjae.adapter.persistence.product

import com.sodamjae.adapter.persistence.product.enum.ProductConditionEntity
import com.sodamjae.adapter.persistence.product.mapper.ProductItemMapper
import com.sodamjae.adapter.persistence.product.repository.ProductItemRepository
import com.sodamjae.application.product.port.out.ProductItemPort
import com.sodamjae.domain.product.enum.ProductCondition
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Component

/**
 * 제품 아이템 도메인과 리포지토리 계층 사이의 어댑터
 * 도메인 모델과 영속성 계층을 완전히 분리
 */
@Component
class ProductItemPersistenceAdapter(
    private val productItemRepository: ProductItemRepository,
    private val productItemMapper: ProductItemMapper
) : ProductItemPort {

    override fun save(productInfo: ProductInfo): ProductInfo {
        val entity = productItemMapper.toEntity(productInfo)
        val savedEntity = productItemRepository.save(entity)
        return productItemMapper.toDomain(savedEntity)
    }

    override fun findById(id: Long): ProductInfo? {
        return productItemRepository.findById(id)
            .map { productItemMapper.toDomain(it) }
            .orElse(null)
    }

    override fun findByProductCatalogId(
        catalogId: Long,
        pageable: Pageable
    ): Page<ProductInfo> {
        return productItemRepository.findByProductCatalogId(catalogId, pageable)
            .map { productItemMapper.toDomain(it) }
    }

    override fun findBySellerId(sellerId: Long, pageable: Pageable): Page<ProductInfo> {
        return productItemRepository.findBySellerId(sellerId, pageable)
            .map { productItemMapper.toDomain(it) }
    }

    override fun findByStoreId(storeId: Long, pageable: Pageable): Page<ProductInfo> {
        return productItemRepository.findByStoreId(storeId, pageable)
            .map { productItemMapper.toDomain(it) }
    }

    override fun findByCondition(condition: ProductCondition, pageable: Pageable): Page<ProductInfo> {
        val entityCondition = ProductConditionEntity.valueOf(condition.name)
        return productItemRepository.findByCondition(entityCondition, pageable)
            .map { productItemMapper.toDomain(it) }
    }

    override fun searchItems(
        catalogId: Long?,
        sellerId: Long?,
        condition: ProductCondition?,
        inStock: Boolean?,
        pageable: Pageable
    ): Page<ProductInfo> {
        val entityCondition = condition?.let {
            ProductConditionEntity.valueOf(it.name)
        }

        return productItemRepository.searchItems(catalogId, sellerId, entityCondition, inStock, pageable)
            .map { productItemMapper.toDomain(it) }
    }

    override fun delete(id: Long) {
        productItemRepository.deleteById(id)
    }
} 