package com.music.sale.application.product.port.out

import com.music.sale.common.Pageable
import com.music.sale.domain.product.Product
import com.music.sale.domain.product.enum.ProductCondition
import org.springframework.data.domain.Page

interface ProductPort {
    /**
     * 제품 저장
     */
    fun save(productItem: Product): Product

    fun findAll(pageable: Pageable): Page<Product>

    /**
     * ID로 제품 조회
     */
    fun findById(id: Long): Product?

    /**
     * 제품 타입으로 검색
     */
    fun findByProductType(productType: String, pageable: Pageable): Page<Product>

    /**
     * 제품명으로 검색 (부분 일치)
     */
    fun findByNameContaining(keyword: String, pageable: Pageable): Page<Product>

    /**
     * 판매자 ID로 제품 조회
     */
    fun findBySellerId(sellerId: Long, pageable: Pageable): Page<Product>

    /**
     * 상점 ID로 제품 조회
     */
    fun findByStoreId(storeId: Long, pageable: Pageable): Page<Product>

    /**
     * 상태(NEW/USED)로 제품 조회
     */
    fun findByCondition(condition: ProductCondition, pageable: Pageable): Page<Product>

    /**
     * 다양한 조건으로 제품 검색
     */
    fun searchProducts(
        productType: String?,
        keyword: String?,
        sellerId: Long?,
        condition: ProductCondition?,
        inStock: Boolean?,
        pageable: Pageable
    ): Page<Product>

    /**
     * 제품 삭제
     */
    fun delete(id: Long)
} 