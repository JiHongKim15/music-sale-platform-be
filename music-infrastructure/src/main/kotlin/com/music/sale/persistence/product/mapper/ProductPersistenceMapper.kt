// Copyright (C) 2024 Your Name or Company
package com.music.sale.persistence.product.mapper

import com.music.sale.application.product.dto.ProductOutput
import com.music.sale.application.product.dto.SaveProductItemCondition
import com.music.sale.domain.product.Product
import com.music.sale.persistence.category.mapper.CategoryPersistenceMapper
import com.music.sale.persistence.product.entity.ProductCatalogEntity
import com.music.sale.persistence.product.entity.ProductItemEntity
import com.music.sale.persistence.store.mapper.StorePersistenceMapper
import com.music.sale.persistence.user.UserPersistenceAdapter
import com.music.sale.persistence.user.mapper.UserPersistenceMapper
import org.springframework.stereotype.Component

/** 제품 아이템 도메인 모델과 엔티티 간의 변환을 담당하는 매퍼 클래스 */
@Component
class ProductPersistenceMapper(
    private val categoryMapper: CategoryPersistenceMapper,
    private val userPersistenceMapper: UserPersistenceMapper,
    private val storePersistenceMapper: StorePersistenceMapper,
    private val userPersistenceAdapter: UserPersistenceAdapter,
) {
    fun toDomain(entity: ProductItemEntity): Product {
        val catalog =
            com.music.sale.domain.product.ProductCatalog(
                id = entity.catalog.id ?: 0L,
                name = entity.catalog.name,
                category = categoryMapper.toDomain(entity.catalog.category),
                brand = entity.catalog.brand,
                attributes = entity.catalog.attributes,
            )

        return Product(
            id = entity.id ?: 0L,
            catalog = catalog,
            price = entity.price,
            // 임시로 null 처리
            seller = null,
            // 임시로 null 처리
            store = null,
            condition = entity.condition,
            conditionGrade = entity.conditionGrade,
            stockQuantity = entity.stockQuantity,
            status = entity.status,
            customName = entity.customName,
            customAttributes = entity.customAttributes,
            // 임시로 빈 리스트
            images = mutableListOf(),
        )
    }

    fun toOutput(entity: ProductItemEntity): ProductOutput {
        return ProductOutput(
            id = entity.id ?: 0L,
            name = entity.customName ?: entity.catalog.name,
            catalog =
                ProductOutput.ProductCatalog(
                    id = entity.catalog.id ?: 0L,
                    category = categoryMapper.toDomain(entity.catalog.category),
                ),
            price = entity.price,
            // 임시로 null 처리
            seller = null,
            // 임시로 null 처리
            store = null,
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
            customName = if (product.isCustomName()) product.name() else null,
            customAttributes = if (product.isCustomAttributes()) product.attributes() else null,
        )
    }
}
