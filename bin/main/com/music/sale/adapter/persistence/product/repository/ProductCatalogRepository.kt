// Copyright (C) 2024 Your Name or Company
package com.music.sale.adapter.persistence.product.repository

import com.music.sale.adapter.persistence.product.entity.ProductCatalogEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ProductCatalogRepository : JpaRepository<ProductCatalogEntity, Long> {
    // 제품 타입으로 검색
    fun findByCategoryId(
        categoryId: Long,
        pageable: Pageable,
    ): Page<ProductCatalogEntity>

    // 이름으로 검색(부분 일치)
    fun findByNameContaining(
        name: String,
        pageable: Pageable,
    ): Page<ProductCatalogEntity>
}
