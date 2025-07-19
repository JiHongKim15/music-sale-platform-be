package com.music.sale.persistence.wishlist.repository

import com.music.sale.persistence.wishlist.entity.WishlistEntity
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface WishlistRepository : JpaRepository<WishlistEntity, Long> {
    fun findByUserId(
            userId: Long,
            pageable: Pageable,
    ): org.springframework.data.domain.Page<WishlistEntity>

    fun findByUserIdAndProductId(
            userId: Long,
            productId: Long,
    ): WishlistEntity?

    fun existsByUserIdAndProductId(
            userId: Long,
            productId: Long,
    ): Boolean
}
