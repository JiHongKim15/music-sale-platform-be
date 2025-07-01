// Copyright (C) 2024 Your Name or Company
package com.music.sale.adapter.persistence.product.mapper

import com.music.sale.adapter.persistence.category.mapper.CategoryPersistenceMapper
import com.music.sale.adapter.persistence.product.dto.SaveProductItemCondition
import com.music.sale.adapter.persistence.product.entity.ProductCatalogEntity
import com.music.sale.adapter.persistence.product.entity.ProductItemEntity
import com.music.sale.adapter.persistence.store.mapper.StorePersistenceMapper
import com.music.sale.adapter.persistence.user.UserPersistenceAdapter
import com.music.sale.adapter.persistence.user.mapper.UserPersistenceMapper
import com.music.sale.application.product.dto.ProductOutput
import com.music.sale.domain.product.Product
import org.springframework.stereotype.Component

/**
 * 제품 아이템 도메인 모델과 엔티티 간의 변환을 담당하는 매퍼 클래스
 */
@Component
class ProductPersistenceMapper(
    private val categoryMapper: CategoryPersistenceMapper,
    private val userPersistenceMapper: UserPersistenceMapper,
    private val storePersistenceMapper: StorePersistenceMapper,
    private val userPersistenceAdapter: UserPersistenceAdapter,
) {
    fun toDomain(entity: ProductItemEntity): Product {
        return Product(
            id = entity.id ?: 0L,
            catalogId = entity.catalog.id ?: 0L,
            category = categoryMapper.toDomain(entity.catalog.category),
            // 임시로 null 처리
            seller = null,
            // 임시로 null 처리
            store = null,
            price = entity.price,
            condition = entity.condition,
            conditionGrade = entity.conditionGrade,
            stockQuantity = entity.stockQuantity,
            status = entity.status,
            name = entity.customName ?: entity.catalog.name,
            attributes = entity.customAttributes ?: entity.catalog.attributes,
        )
    }

    fun toOutput(entity: ProductItemEntity): ProductOutput {
        return ProductOutput(
            id = entity.id ?: 0L,
            name = entity.customName ?: entity.catalog.name,
            catalog = ProductOutput.ProductCatalog(
                id = entity.catalog.id ?: 0L,
                category = categoryMapper.toDomain(entity.catalog.category)
            ),
            price = entity.price,
            seller = null, // 임시로 null 처리
            store = null, // 임시로 null 처리
            condition = entity.condition,
            conditionGrade = entity.conditionGrade,
            stockQuantity = entity.stockQuantity,
            status = entity.status,
            attributes = entity.customAttributes ?: entity.catalog.attributes,
        )
    }

    fun toEntity(
        saveCondition: SaveProductItemCondition,
        catalogEntity: ProductCatalogEntity,
    ): ProductItemEntity {
        val sellerEntity = saveCondition.seller?.let { userPersistenceMapper.toEntity(it) }
        val storeEntity = saveCondition.store?.let { storePersistenceMapper.toEntity(it) }

        return ProductItemEntity(
            id = null,
            catalog = catalogEntity,
            seller = sellerEntity,
            store = storeEntity,
            price = saveCondition.price,
            condition = saveCondition.condition,
            conditionGrade = saveCondition.conditionGrade,
            stockQuantity = saveCondition.stockQuantity,
            status = saveCondition.status,
            customName = saveCondition.name,
            customAttributes = saveCondition.attributes,
        )
    }

    fun toEntity(
        product: Product,
        catalogEntity: ProductCatalogEntity,
    ): ProductItemEntity {
        val sellerEntity = product.seller?.let { userPersistenceMapper.toEntity(it) }
        val storeEntity = product.store?.let { storePersistenceMapper.toEntity(it) }

        return ProductItemEntity(
            id = product.id,
            catalog = catalogEntity,
            seller = sellerEntity,
            store = storeEntity,
            price = product.price,
            condition = product.condition,
            conditionGrade = product.conditionGrade,
            stockQuantity = product.stockQuantity,
            status = product.status,
            customName =
                product.isCustomName().let {
                    if (it) product.name() else null
                },
            customAttributes =
                product.isCustomAttributes().let {
                    if (it) product.attributes() else null
                },
        )
    }
}
