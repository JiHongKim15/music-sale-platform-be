// Copyright (C) 2024 Your Name or Company
package com.music.sale.adapter.persistence.product.repository

import com.music.sale.adapter.persistence.category.entity.QCategoryEntity
import com.music.sale.adapter.persistence.product.dto.SearchProductCondition
import com.music.sale.adapter.persistence.product.entity.ProductItemEntity
import com.music.sale.adapter.persistence.product.entity.QProductCatalogEntity
import com.music.sale.adapter.persistence.product.entity.QProductItemEntity
import com.music.sale.adapter.persistence.store.entity.QStoreEntity
import com.music.sale.adapter.persistence.user.entity.QUserEntity
import com.music.sale.adapter.web.product.request.SearchProductKeywordType
import com.music.sale.domain.product.enum.ProductCondition
import com.music.sale.domain.product.enum.ProductConditionGrade
import com.music.sale.domain.product.enum.ProductStatus
import com.querydsl.core.types.dsl.BooleanExpression
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Repository

@Repository
class ProductItemQueryDslRepositoryImpl(private val queryFactory: JPAQueryFactory) :
    ProductItemQueryDslRepository {
    override fun searchProducts(
        searchCriteria: SearchProductCondition,
        pageable: Pageable,
    ): Page<ProductItemEntity> {
        val productItem = QProductItemEntity.productItemEntity
        val catalog = QProductCatalogEntity.productCatalogEntity
        val category = QCategoryEntity.categoryEntity
        val seller = QUserEntity.userEntity
        val store = QStoreEntity.storeEntity

        val query =
            queryFactory
                .selectFrom(productItem)
                .leftJoin(productItem.catalog, catalog)
                .leftJoin(catalog.category, category)
                .leftJoin(productItem.seller, seller)
                .leftJoin(productItem.store, store)
                .where(
                    keywordCondition(
                        searchCriteria.keyword,
                        searchCriteria.keywordType,
                        productItem,
                        catalog,
                    ),
                    categoryIdCondition(searchCriteria.categoryId, category),
                    locationCondition(searchCriteria.location, store),
                    conditionCondition(searchCriteria.condition, productItem),
                    conditionGradeCondition(searchCriteria.conditionGrade, productItem),
                    priceRangeCondition(
                        searchCriteria.minPrice,
                        searchCriteria.maxPrice,
                        productItem,
                    ),
                    statusCondition(searchCriteria.status, productItem),
                )

        val total = query.fetchCount()
        val results = query.offset(pageable.offset).limit(pageable.pageSize.toLong()).fetch()

        return PageImpl(results, pageable, total)
    }

    private fun keywordCondition(
        keyword: String?,
        keywordType: SearchProductKeywordType?,
        productItem: QProductItemEntity,
        catalog: QProductCatalogEntity,
    ): BooleanExpression? {
        if (keyword.isNullOrBlank()) return null

        return when (keywordType) {
            null ->
                productItem
                    .customName
                    .contains(keyword)
                    .or(catalog.name.contains(keyword))
                    .or(productItem.seller.name.contains(keyword))
                    .or(productItem.store.name.contains(keyword))
                    .or(productItem.catalog.attributes.containsValue(keyword))
                    .or(productItem.customAttributes.containsValue(keyword))
            SearchProductKeywordType.PRODUCT_NAME -> catalog.name.contains(keyword)
            SearchProductKeywordType.SELLER_NAME -> productItem.seller.name.contains(keyword)
            SearchProductKeywordType.STORE_NAME -> productItem.store.name.contains(keyword)
            SearchProductKeywordType.ATTRIBUTE ->
                productItem
                    .catalog
                    .attributes
                    .containsValue(keyword)
                    .or(productItem.customAttributes.containsValue(keyword))
        }
    }

    private fun categoryIdCondition(
        categoryId: Long?,
        category: QCategoryEntity,
    ): BooleanExpression? {
        return categoryId?.let { category.id.eq(it) }
    }

    private fun locationCondition(
        location: String?,
        store: QStoreEntity,
    ): BooleanExpression? {
        return null
    }

    private fun conditionCondition(
        condition: ProductCondition?,
        productItem: QProductItemEntity,
    ): BooleanExpression? {
        return null
    }

    private fun conditionGradeCondition(
        conditionGrade: ProductConditionGrade?,
        productItem: QProductItemEntity,
    ): BooleanExpression? {
        return null
    }

    private fun priceRangeCondition(
        minPrice: Int?,
        maxPrice: Int?,
        productItem: QProductItemEntity,
    ): BooleanExpression? {
        return when {
            minPrice != null && maxPrice != null -> productItem.price.between(minPrice, maxPrice)
            minPrice != null -> productItem.price.goe(minPrice)
            maxPrice != null -> productItem.price.loe(maxPrice)
            else -> null
        }
    }

    private fun statusCondition(
        status: ProductStatus?,
        productItem: QProductItemEntity,
    ): BooleanExpression? {
        return null
    }
}
