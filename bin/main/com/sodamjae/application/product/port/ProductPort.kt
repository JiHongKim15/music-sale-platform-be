package com.sodamjae.application.product.port

import com.sodamjae.domain.product.Product
import com.sodamjae.domain.product.enum.ProductCondition
import com.sodamjae.domain.product.enum.ProductStatus
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import java.math.BigDecimal

/**
 * 통합된 상품 도메인 모델과 외부 세계(인프라, 리포지토리 등)를 연결하는 포트 인터페이스
 * 헥사고날 아키텍처에서 Secondary/Driven 포트에 해당
 */
interface ProductPort {

    /**
     * 상품을 저장하거나 업데이트
     */
    fun save(product: Product): Product

    /**
     * ID로 상품 조회
     */
    fun findById(id: Long): Product?

    /**
     * 카탈로그 ID로 상품 목록 조회
     */
    fun findByCatalogId(catalogId: Long, pageable: Pageable): Page<Product>

    /**
     * 판매자 ID로 상품 목록 조회
     */
    fun findBySellerId(sellerId: Long, pageable: Pageable): Page<Product>

    /**
     * 상점 ID로 상품 목록 조회
     */
    fun findByStoreId(storeId: Long, pageable: Pageable): Page<Product>

    /**
     * 제품 타입으로 상품 목록 조회
     */
    fun findByProductType(productType: String, pageable: Pageable): Page<Product>

    /**
     * 상품명으로 검색 (부분 일치)
     */
    fun findByNameContaining(keyword: String, pageable: Pageable): Page<Product>

    /**
     * 가격 범위로 상품 검색
     */
    fun findByPriceRange(
        minPrice: BigDecimal,
        maxPrice: BigDecimal,
        pageable: Pageable
    ): Page<Product>

    /**
     * 상태(NEW/USED)로 상품 검색
     */
    fun findByCondition(condition: ProductCondition, pageable: Pageable): Page<Product>

    /**
     * 판매 상태별 조회
     */
    fun findByStatus(status: ProductStatus, pageable: Pageable): Page<Product>

    /**
     * 재고가 있는 상품만 조회
     */
    fun findInStock(pageable: Pageable): Page<Product>

    /**
     * 다양한 조건으로 상품 검색
     */
    fun searchProducts(
        productType: String?,
        catalogId: Long?,
        sellerId: Long?,
        condition: ProductCondition?,
        minPrice: BigDecimal?,
        maxPrice: BigDecimal?,
        keyword: String?,
        inStock: Boolean?,
        pageable: Pageable
    ): Page<Product>

    /**
     * 상품 삭제
     */
    fun delete(id: Long)
} 