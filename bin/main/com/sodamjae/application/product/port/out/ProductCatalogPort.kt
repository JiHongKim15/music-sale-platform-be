package com.sodamjae.application.product.port.out

import com.sodamjae.domain.product.ProductCatalog
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

/**
 * 제품 카탈로그 도메인과 외부 세계를 연결하는 포트 인터페이스
 * 영속성 계층과의 매핑을 위해 존재하며, 비즈니스 로직은 최소화되어 있음
 */
interface ProductCatalogPort {

    /**
     * 카탈로그 저장
     */
    fun save(productCatalog: ProductCatalog): ProductCatalog

    /**
     * ID로 카탈로그 조회
     */
    fun findById(id: Long): ProductCatalog?

    /**
     * 제품 타입으로 카탈로그 검색
     */
    fun findByProductType(productType: String, pageable: Pageable): Page<ProductCatalog>

    /**
     * 카탈로그명으로 검색 (부분 일치)
     */
    fun findByNameContaining(keyword: String, pageable: Pageable): Page<ProductCatalog>

    /**
     * 다양한 조건으로 카탈로그 검색
     */
    fun searchCatalogs(
        productType: String?,
        keyword: String?,
        pageable: Pageable
    ): Page<ProductCatalog>

    /**
     * 카탈로그 삭제
     */
    fun delete(id: Long)
} 