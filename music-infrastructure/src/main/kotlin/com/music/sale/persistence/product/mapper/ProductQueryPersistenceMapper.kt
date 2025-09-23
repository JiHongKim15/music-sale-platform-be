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
                    category = queryResult.catalog.category,
                    brand = queryResult.catalog.brand,
                    attributes = queryResult.catalog.attribute,
                ),
            price = queryResult.price,
            seller = queryResult.seller,
            store = queryResult.store,
            condition = queryResult.condition,
            conditionGrade = queryResult.conditionGrade,
            stockQuantity = queryResult.stockQuantity,
            status = queryResult.status,
            customName = queryResult.name,
            customAttributes = queryResult.attributes,
            // 이미지는 쿼리 결과에서 가져오거나 빈 리스트로 초기화
            images = queryResult.images?.toMutableList() ?: mutableListOf(),
        )
    }
} 
