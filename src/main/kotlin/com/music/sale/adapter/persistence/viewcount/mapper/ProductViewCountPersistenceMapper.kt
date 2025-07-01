// Copyright (C) 2024 Your Name or Company
package com.music.sale.adapter.persistence.viewcount.mapper

import com.music.sale.adapter.persistence.viewcount.entity.ProductViewCountEntity
import com.music.sale.application.viewcount.dto.ProductViewCountOutput
import com.music.sale.domain.viewcount.ProductViewCount
import org.springframework.stereotype.Component

@Component
class ProductViewCountPersistenceMapper {
    
    /**
     * 도메인 모델을 엔티티로 변환
     */
    fun toEntity(domain: ProductViewCount): ProductViewCountEntity {
        return ProductViewCountEntity(
            id = domain.id,
            productId = domain.productId,
            currentViewers = domain.currentViewers,
            totalViews = domain.totalViews,
            lastUpdated = domain.lastUpdated
        )
    }
    
    /**
     * 엔티티를 도메인 모델로 변환
     */
    fun toDomain(entity: ProductViewCountEntity): ProductViewCount {
        return ProductViewCount(
            id = entity.id,
            productId = entity.productId,
            currentViewers = entity.currentViewers,
            totalViews = entity.totalViews,
            lastUpdated = entity.lastUpdated
        )
    }
    
    /**
     * 엔티티를 출력 DTO로 변환
     */
    fun toOutput(entity: ProductViewCountEntity): ProductViewCountOutput {
        return ProductViewCountOutput(
            productId = entity.productId,
            currentViewers = entity.currentViewers,
            totalViews = entity.totalViews,
            lastUpdated = entity.lastUpdated
        )
    }
    
    /**
     * 도메인 모델을 출력 DTO로 변환
     */
    fun toOutput(domain: ProductViewCount): ProductViewCountOutput {
        return ProductViewCountOutput(
            productId = domain.productId,
            currentViewers = domain.currentViewers,
            totalViews = domain.totalViews,
            lastUpdated = domain.lastUpdated
        )
    }
} 
