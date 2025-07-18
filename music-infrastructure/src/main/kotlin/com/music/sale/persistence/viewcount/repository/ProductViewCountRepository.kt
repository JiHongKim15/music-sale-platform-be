package com.music.sale.persistence.viewcount.repository

import com.music.sale.persistence.viewcount.entity.ProductViewCountEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ProductViewCountRepository : JpaRepository<ProductViewCountEntity, Long> {
    fun findByProductId(productId: Long): ProductViewCountEntity?
}
