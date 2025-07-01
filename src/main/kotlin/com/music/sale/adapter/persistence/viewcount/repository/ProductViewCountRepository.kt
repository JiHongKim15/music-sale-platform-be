// Copyright (C) 2024 Your Name or Company
package com.music.sale.adapter.persistence.viewcount.repository

import com.music.sale.adapter.persistence.viewcount.entity.ProductViewCountEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ProductViewCountRepository : JpaRepository<ProductViewCountEntity, Long> {
    
    /**
     * 상품 ID로 조회수 정보 조회
     */
    fun findByProductId(productId: Long): ProductViewCountEntity?
    
    /**
     * 상품 ID로 조회수 정보 존재 여부 확인
     */
    fun existsByProductId(productId: Long): Boolean
} 
