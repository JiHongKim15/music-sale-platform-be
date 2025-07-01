// Copyright (C) 2024 Your Name or Company
package com.music.sale.adapter.persistence.wishlist.repository

import com.music.sale.adapter.persistence.wishlist.entity.WishlistEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

/**
 * 찜 목록 리포지토리
 */
interface WishlistRepository : JpaRepository<WishlistEntity, Long> {
    
    /**
     * 사용자 ID로 찜 목록 조회
     */
    @Query("SELECT w FROM WishlistEntity w WHERE w.user.id = :userId")
    fun findByUserId(@Param("userId") userId: Long): List<WishlistEntity>
    
    /**
     * 사용자 ID와 상품 ID로 찜 목록 조회
     */
    @Query("SELECT w FROM WishlistEntity w WHERE w.user.id = :userId AND w.product.id = :productId")
    fun findByUserIdAndProductId(
        @Param("userId") userId: Long,
        @Param("productId") productId: Long
    ): WishlistEntity?
    
    /**
     * 사용자 ID와 상품 ID로 찜 목록 존재 여부 확인
     */
    @Query("SELECT COUNT(w) > 0 FROM WishlistEntity w WHERE w.user.id = :userId AND w.product.id = :productId")
    fun existsByUserIdAndProductId(
        @Param("userId") userId: Long,
        @Param("productId") productId: Long
    ): Boolean
    
    /**
     * 사용자 ID와 상품 ID로 찜 목록 삭제
     */
    @Modifying
    @Query("DELETE FROM WishlistEntity w WHERE w.user.id = :userId AND w.product.id = :productId")
    fun deleteByUserIdAndProductId(
        @Param("userId") userId: Long,
        @Param("productId") productId: Long
    )
} 
