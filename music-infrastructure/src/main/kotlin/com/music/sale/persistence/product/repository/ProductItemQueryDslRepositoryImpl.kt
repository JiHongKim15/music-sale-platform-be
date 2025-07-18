package com.music.sale.persistence.product.repository

import com.music.sale.application.product.dto.SearchProductCondition
import com.music.sale.persistence.product.entity.ProductItemEntity
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Repository

@Repository
open class ProductItemQueryDslRepositoryImpl(
        private val queryFactory: JPAQueryFactory,
) : ProductItemQueryDslRepository {
    override fun searchProducts(
            searchCriteria: SearchProductCondition,
            pageable: Pageable,
    ): Page<ProductItemEntity> {
        // 임시로 빈 페이지 반환
        return PageImpl(emptyList(), pageable, 0)
    }
}
