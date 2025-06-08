package com.music.sale.adapter.persistence.product.mapper

import com.music.sale.adapter.persistence.category.mapper.CategoryPersistenceMapper
import com.music.sale.adapter.persistence.product.dto.SaveProductCondition
import com.music.sale.adapter.persistence.product.entity.ProductCatalogEntity
import com.music.sale.adapter.persistence.product.entity.ProductItemEntity
import com.music.sale.adapter.persistence.seller.entity.SellerEntity
import com.music.sale.adapter.persistence.store.entity.StoreEntity
import com.music.sale.domain.product.Product
import org.springframework.stereotype.Component

/**
 * 제품 아이템 도메인 모델과 엔티티 간의 변환을 담당하는 매퍼 클래스
 */
@Component
class ProductPersistenceMapper(
    private val categoryMapper: CategoryPersistenceMapper
) {
    fun toDomain(entity: ProductItemEntity): Product {
        return Product.create(
            name = entity.catalog.name,
            category = categoryMapper.toDomain(entity.catalog.category),
            price = entity.price,
            sellerId = entity.seller.id ?: 0L,
            storeId = entity.store.id,
            condition = entity.condition,
            conditionGrade = entity.conditionGrade,
            stockQuantity = entity.stockQuantity,
            status = entity.status,
            attributes = entity.customAttributes ?: emptyMap()
        )
    }

    fun toEntity(product: Product): ProductItemEntity {
        return ProductItemEntity(
            id = product.id,
            catalog = this.toCatalogEntity(product),
            seller = SellerEntity.fromId(product.seller.id ?: 0L),
            store = StoreEntity.fromId(product.store?.id ?: 0L),
            price = product.price,
            condition = product.condition,
            conditionGrade = product.conditionGrade,
            stockQuantity = product.stockQuantity,
            status = product.status,
            customName = null,
            customAttributes = product.attributes()
        )
    }

    fun toEntity(product: SaveProductCondition): ProductItemEntity {
        return ProductItemEntity(
            catalog = ProductCatalogEntity(id = product.catalogId),
            seller = SellerEntity(id = product.sellerId),        // seller도 같은 방식으로
            store = StoreEntity(id = product.storeId),          // store도 같은 방식으로
            price = product.price,
            condition = product.condition,
            conditionGrade = product.conditionGrade,
            stockQuantity = product.stockQuantity,
            status = product.status,
            customName = product.name,
            customAttributes = product.attributes
        )
    }

    fun toCatalogEntity(product: Product): ProductCatalogEntity {
        return ProductCatalogEntity(
            id = null,
            name = product.name(),
            category = categoryMapper.toEntity(product.category),
            attributes = product.attributes()
        )
    }
}