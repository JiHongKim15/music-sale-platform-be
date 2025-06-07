package com.music.sale.adapter.persistence.product.repository

import com.music.sale.adapter.persistence.product.entity.ProductItemEntity
import com.music.sale.domain.product.enum.ProductCondition
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface ProductItemRepository : JpaRepository<ProductItemEntity, Long> {

    // 카탈로그 ID로 아이템 검색
    fun findByCatalog_Id(catalogId: Long, pageable: Pageable): Page<ProductItemEntity>

    // 판매자 ID로 검색
    fun findBySellerId(sellerId: Long, pageable: Pageable): Page<ProductItemEntity>

    // 상점 ID로 검색
    fun findByStoreId(storeId: Long, pageable: Pageable): Page<ProductItemEntity>

    // 제품 상태(NEW/USED)로 검색
    fun findByCondition(condition: ProductCondition, pageable: Pageable): Page<ProductItemEntity>

    // 재고가 1개 이상인 제품 검색
    fun findByStockQuantityGreaterThan(quantity: Int, pageable: Pageable): Page<ProductItemEntity>

    // 여러 조건으로 검색
    @Query(
        """
        SELECT pi FROM ProductItemEntity pi 
        WHERE (:catalogId IS NULL OR pi.catalog.id = :catalogId)
        AND (:sellerId IS NULL OR pi.seller.id = :sellerId)
        AND (:condition IS NULL OR pi.condition = :condition)
        AND (:inStock IS NULL OR (:inStock = true AND pi.stockQuantity > 0) OR (:inStock = false))
    """
    )
    fun searchItems(
        @Param("catalogId") catalogId: Long?,
        @Param("sellerId") sellerId: Long?,
        @Param("condition") condition: ProductCondition?,
        @Param("inStock") inStock: Boolean?,
        pageable: Pageable
    ): Page<ProductItemEntity>
} 