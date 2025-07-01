// Copyright (C) 2024 Your Name or Company
package com.music.sale.adapter.persistence.viewcount.entity

import com.music.sale.adapter.persistence.common.BaseEntity
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(
    name = "product_view_count",
    uniqueConstraints = [
        UniqueConstraint(columnNames = ["product_id"])
    ]
)
class ProductViewCountEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(name = "product_id", nullable = false, unique = true)
    val productId: Long,

    @Column(name = "current_viewers", nullable = false)
    var currentViewers: Int = 0,

    @Column(name = "total_views", nullable = false)
    var totalViews: Int = 0,

    @Column(name = "last_updated", nullable = false)
    var lastUpdated: LocalDateTime = LocalDateTime.now(),
) : BaseEntity() {
    
    fun updateViewCount(currentViewers: Int, totalViews: Int) {
        this.currentViewers = currentViewers
        this.totalViews = totalViews
        this.lastUpdated = LocalDateTime.now()
    }
} 
