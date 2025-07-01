// Copyright (C) 2024 Your Name or Company
package com.music.sale.adapter.persistence.cart.repository

import com.music.sale.adapter.persistence.cart.entity.CartEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

/**
 * 장바구니 리포지토리
 */
interface CartRepository : JpaRepository<CartEntity, Long> {
    
    /**
     * 사용자 ID로 장바구니 조회
     */
    @Query("SELECT c FROM CartEntity c WHERE c.user.id = :userId ORDER BY c.createdAt DESC")
    fun findByUserId(@Param("userId") userId: Long): List<CartEntity>
    
    /**
     * 사용자 ID와 상품 ID로 장바구니 조회
     */
    @Query("SELECT c FROM CartEntity c WHERE c.user.id = :userId AND c.product.id = :productId")
    fun findByUserIdAndProductId(
        @Param("userId") userId: Long,
        @Param("productId") productId: Long
    ): CartEntity?
    
    /**
     * 사용자 ID와 상품 ID로 장바구니 존재 여부 확인
     */
    @Query("SELECT COUNT(c) > 0 FROM CartEntity c WHERE c.user.id = :userId AND c.product.id = :productId")
    fun existsByUserIdAndProductId(
        @Param("userId") userId: Long,
        @Param("productId") productId: Long
    ): Boolean
    
    /**
     * 사용자 ID와 상품 ID로 장바구니 삭제
     */
    @Modifying
    @Query("DELETE FROM CartEntity c WHERE c.user.id = :userId AND c.product.id = :productId")
    fun deleteByUserIdAndProductId(
        @Param("userId") userId: Long,
        @Param("productId") productId: Long
    )
    
    /**
     * 사용자 ID로 장바구니 모두 삭제
     */
    @Modifying
    @Query("DELETE FROM CartEntity c WHERE c.user.id = :userId")
    fun deleteByUserId(@Param("userId") userId: Long)
    
    /**
     * 사용자의 장바구니 총 금액 계산
     */
    @Query("SELECT COALESCE(SUM(c.product.price * c.quantity), 0) FROM CartEntity c WHERE c.user.id = :userId")
    fun getTotalAmountByUserId(@Param("userId") userId: Long): Int
} 
