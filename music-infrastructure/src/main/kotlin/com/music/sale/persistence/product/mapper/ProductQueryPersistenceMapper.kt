package com.music.sale.persistence.product.mapper

import com.music.sale.domain.category.Category
import com.music.sale.domain.product.Product
import com.music.sale.domain.product.ProductCatalog
import com.music.sale.domain.store.Store
import com.music.sale.domain.user.User
import com.music.sale.persistence.product.dto.ProductQueryResult
import org.springframework.stereotype.Component

@Component
class ProductQueryPersistenceMapper {
    fun toDomain(queryResult: ProductQueryResult): Product {
        return Product(
            id = queryResult.id,
            catalog =
                ProductCatalog(
                    id = queryResult.catalog.id,
                    name = queryResult.catalog.name,
                    category =
                        Category(
                            id = queryResult.catalog.category.id!!,
                            name = queryResult.catalog.category.name,
                            type = queryResult.catalog.category.type,
                            // 부모 카테고리 정보가 필요하면 추가 쿼리 필요
                            parent = null,
                            path = queryResult.catalog.category.path,
                            depth = queryResult.catalog.category.depth,
                        ),
                    brand = queryResult.catalog.brand,
                    attributes = queryResult.catalog.attribute?.mapValues { it.value as Any },
                ),
            price = queryResult.price,
            seller = queryResult.seller,
            store = queryResult.store,
            condition = queryResult.condition,
            conditionGrade = queryResult.conditionGrade,
            stockQuantity = queryResult.stockQuantity,
            status = queryResult.status,
            customName = queryResult.name, // TODO: customName 필드가 없으므로 name 사용
            customAttributes = queryResult.attributes,
            images = queryResult.images?.toMutableList() ?: mutableListOf(),
        )
    }
} 
