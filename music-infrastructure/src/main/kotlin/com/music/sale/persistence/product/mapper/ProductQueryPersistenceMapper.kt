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
                    id = queryResult.catalogId,
                    name = queryResult.catalogName,
                    category =
                        Category(
                            id = queryResult.categoryId,
                            name = queryResult.categoryName,
                            type = queryResult.categoryType,
                            // 부모 카테고리 정보가 필요하면 추가 쿼리 필요
                            parent = null,
                            path = queryResult.categoryPath,
                            depth = queryResult.categoryDepth,
                        ),
                ),
            price = queryResult.price,
            seller =
                User(
                    id = queryResult.sellerId,
                    name = User.Name(queryResult.sellerName),
                ),
            store =
                Store(
                    id = queryResult.storeId,
                ),
            condition = queryResult.condition,
            conditionGrade = queryResult.conditionGrade,
            stockQuantity = queryResult.stockQuantity,
            status = queryResult.status,
            customName = queryResult.customName,
            customAttributes = queryResult.customAttributes,
        )
    }
} 
