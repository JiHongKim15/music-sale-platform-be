package com.sodamjae.application.product.port.out

import com.sodamjae.domain.product.enum.ProductCondition
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

/**
 * 제품 아이템 도메인과 외부 세계를 연결하는 포트 인터페이스
 * 영속성 계층과의 매핑을 위해 존재하며, 비즈니스 로직은 최소화되어 있음
 */
interface ProductItemPort {

    /**
     * 아이템 저장
     */
    fun save(productInfo: ProductInfo): ProductInfo

    /**
     * ID로 아이템 조회
     */
    fun findById(id: Long): ProductInfo?

    /**
     * 카탈로그 ID로 아이템 조회
     */
    fun findByProductCatalogId(
        catalogId: Long,
        pageable: Pageable
    ): Page<ProductInfo>

    /**
     * 판매자 ID로 아이템 조회
     */
    fun findBySellerId(sellerId: Long, pageable: Pageable): Page<ProductInfo>

    /**
     * 상점 ID로 아이템 조회
     */
    fun findByStoreId(storeId: Long, pageable: Pageable): Page<ProductInfo>

    /**
     * 상태(NEW/USED)로 아이템 조회
     */
    fun findByCondition(condition: ProductCondition, pageable: Pageable): Page<ProductInfo>

    /**
     * 다양한 조건으로 아이템 검색
     */
    fun searchItems(
        catalogId: Long?,
        sellerId: Long?,
        condition: ProductCondition?,
        inStock: Boolean?,
        pageable: Pageable
    ): Page<ProductInfo>

    /**
     * 아이템 삭제
     */
    fun delete(id: Long)
} 