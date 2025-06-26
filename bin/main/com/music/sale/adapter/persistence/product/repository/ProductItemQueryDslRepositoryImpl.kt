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
            null -> {
                // 기본 검색: customName과 catalog.name만 검색
                productItem.customName.contains(keyword).or(catalog.name.contains(keyword))
            }
            SearchProductKeywordType.PRODUCT_NAME -> catalog.name.contains(keyword)
            SearchProductKeywordType.SELLER_NAME -> null // seller가 null이므로 제외
            SearchProductKeywordType.STORE_NAME -> null // store가 null이므로 제외
            SearchProductKeywordType.ATTRIBUTE -> null // JSON 필드 검색은 복잡하므로 일단 제외
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
        return location?.let { store.baseAddress.contains(it) }
    }

    private fun conditionCondition(
        condition: ProductCondition?,
        productItem: QProductItemEntity,
    ): BooleanExpression? {
        // TODO: Q클래스에 condition 필드가 없음 - QueryDSL 설정 확인 필요
        return null
    }

    private fun conditionGradeCondition(
        conditionGrade: ProductConditionGrade?,
        productItem: QProductItemEntity,
    ): BooleanExpression? {
        // TODO: Q클래스에 conditionGrade 필드가 없음 - QueryDSL 설정 확인 필요
        return null
    }

    private fun priceRangeCondition(
        minPrice: Int?,
        maxPrice: Int?,
        productItem: QProductItemEntity,
    ): BooleanExpression? {
        return when {
            minPrice != null && maxPrice != null -> productItem.price.between(minPrice.toLong(), maxPrice.toLong())
            minPrice != null -> productItem.price.goe(minPrice.toLong())
            maxPrice != null -> productItem.price.loe(maxPrice.toLong())
            else -> null
        }
    }

    private fun statusCondition(
        status: ProductStatus?,
        productItem: QProductItemEntity,
    ): BooleanExpression? {
        // TODO: Q클래스에 status 필드가 없음 - QueryDSL 설정 확인 필요
        return null
    }
}
