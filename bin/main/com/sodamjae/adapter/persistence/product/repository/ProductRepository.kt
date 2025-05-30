package com.music.sale.adapter.persistence.product.repository

import com.music.sale.adapter.persistence.product.entity.ProductEntity
import com.music.sale.adapter.persistence.product.enum.ProductCategoryEntity
import com.music.sale.adapter.persistence.product.enum.ProductStatusEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface ProductRepository : JpaRepository<ProductEntity, Long> {

    // 판매자 ID로 상품 찾기
    fun findBySellerId(sellerId: Long, pageable: Pageable): Page<ProductEntity>

    // 상점 ID로 상품 찾기
    fun findByStoreId(storeId: Long, pageable: Pageable): Page<ProductEntity>

    // 카테고리별 상품 찾기
    fun findByCategory(category: ProductCategoryEntity, pageable: Pageable): Page<ProductEntity>

    // 상품명 검색 (부분 일치)
    fun findByNameContaining(name: String, pageable: Pageable): Page<ProductEntity>

    // 상품 가격 범위 검색
    @Query("SELECT p FROM ProductEntity p WHERE p.price BETWEEN :minPrice AND :maxPrice")
    fun findByPriceBetween(
        @Param("minPrice") minPrice: java.math.BigDecimal,
        @Param("maxPrice") maxPrice: java.math.BigDecimal,
        pageable: Pageable
    ): Page<ProductEntity>

    // 여러 조건으로 상품 검색
    @Query(
        """
        SELECT p FROM ProductEntity p 
        WHERE (:category IS NULL OR p.category = :category)
        AND (:minPrice IS NULL OR p.price >= :minPrice)
        AND (:maxPrice IS NULL OR p.price <= :maxPrice)
        AND (:keyword IS NULL OR p.name LIKE %:keyword% OR p.description LIKE %:keyword%)
        AND p.status = com.music.sale.adapter.persistence.product.enum.ProductStatusEntity.ACTIVE
    """
    )
    fun searchProducts(
        @Param("category") category: ProductCategoryEntity?,
        @Param("minPrice") minPrice: java.math.BigDecimal?,
        @Param("maxPrice") maxPrice: java.math.BigDecimal?,
        @Param("keyword") keyword: String?,
        pageable: Pageable
    ): Page<ProductEntity>

    // 특정 상태의 상품만 찾기
    fun findByStatus(status: ProductStatusEntity, pageable: Pageable): Page<ProductEntity>
} 