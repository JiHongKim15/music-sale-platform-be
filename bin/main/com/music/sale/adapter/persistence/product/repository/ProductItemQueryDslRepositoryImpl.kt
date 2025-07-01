package com.music.sale.adapter.persistence.product.repository

import com.music.sale.adapter.persistence.product.dto.SearchProductCondition
import com.music.sale.adapter.persistence.product.entity.ProductItemEntity
import com.music.sale.adapter.persistence.product.entity.QProductItemEntity
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Repository

@Repository
class ProductItemQueryDslRepositoryImpl(
    private val queryFactory: JPAQueryFactory,
) : ProductItemQueryDslRepository {
    override fun searchProducts(
        searchCriteria: SearchProductCondition,
        pageable: Pageable,
    ): Page<ProductItemEntity> {
        val productItem = QProductItemEntity.productItemEntity

        val query =
            queryFactory
                .selectFrom(productItem)
                .orderBy(productItem.createdAt.desc())

        val total = query.fetchCount()
        val content =
            query
                .offset(pageable.offset)
                .limit(pageable.pageSize.toLong())
                .fetch()

        return PageImpl(content, pageable, total)
    }
} 
