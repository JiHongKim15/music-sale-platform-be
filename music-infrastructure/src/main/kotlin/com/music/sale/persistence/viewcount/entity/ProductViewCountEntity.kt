package com.music.sale.persistence.viewcount.entity

import com.music.sale.application.viewcount.dto.ProductViewCountOutput
import com.music.sale.domain.viewcount.ProductViewCount
import com.music.sale.persistence.common.BaseEntity
import jakarta.persistence.*

@Entity
@Table(name = "product_view_count")
class ProductViewCountEntity(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long? = null,
        @Column(name = "product_id", nullable = false) val productId: Long,
        @Column(name = "current_viewers", nullable = false) val currentViewers: Int = 0,
        @Column(name = "total_views", nullable = false) val totalViews: Int = 0,
        @Column(name = "last_updated", nullable = false)
        val lastUpdated: java.time.LocalDateTime = java.time.LocalDateTime.now(),
) : BaseEntity() {
    fun toDomain(): ProductViewCount {
        return ProductViewCount(
                id = id,
                productId = productId,
                currentViewers = currentViewers,
                totalViews = totalViews,
                lastUpdated = lastUpdated,
        )
    }

    fun toOutput(): ProductViewCountOutput {
        return ProductViewCountOutput(
                productId = productId,
                currentViewers = currentViewers,
                totalViews = totalViews,
                lastUpdated = lastUpdated,
        )
    }

    companion object {
        fun fromDomain(productViewCount: ProductViewCount): ProductViewCountEntity {
            return ProductViewCountEntity(
                    id = productViewCount.id,
                    productId = productViewCount.productId,
                    currentViewers = productViewCount.currentViewers,
                    totalViews = productViewCount.totalViews,
                    lastUpdated = productViewCount.lastUpdated,
            )
        }
    }
}
