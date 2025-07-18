package com.music.sale.persistence.cart.repository

import com.music.sale.persistence.cart.entity.CartEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CartRepository : JpaRepository<CartEntity, Long> {
    fun findByUserId(userId: Long): CartEntity?
}
