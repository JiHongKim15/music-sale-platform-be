// Copyright (C) 2024 Your Name or Company
package com.music.sale.persistence.product.repository

import com.music.sale.persistence.product.entity.ProductItemEntity
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface ProductItemCommandJpaRepository : JpaRepository<ProductItemEntity, Long> {
    
    @Query("SELECT DISTINCT pi FROM ProductItemEntity pi " +
           "LEFT JOIN FETCH pi.catalog c " +
           "LEFT JOIN FETCH c.category " +
           "LEFT JOIN FETCH pi.seller " +
           "LEFT JOIN FETCH pi.store")
    fun findAllWithJoins(pageable: Pageable): List<ProductItemEntity>

    @Query("SELECT count(pi.id) FROM ProductItemEntity pi")
    fun countAllItems(): Long

    @Query("SELECT DISTINCT pi FROM ProductItemEntity pi " +
           "LEFT JOIN FETCH pi.catalog c " +
           "LEFT JOIN FETCH c.category " +
           "LEFT JOIN FETCH pi.seller " +
           "LEFT JOIN FETCH pi.store " +
           "LEFT JOIN FETCH pi.images " +
           "WHERE pi.id = :id")
    fun findByIdWithJoins(id: Long): Optional<ProductItemEntity>
}
