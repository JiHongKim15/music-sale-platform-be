// Copyright (C) 2024 Your Name or Company
package com.music.sale.adapter.persistence.viewcount

import com.music.sale.adapter.persistence.viewcount.mapper.ProductViewCountPersistenceMapper
import com.music.sale.adapter.persistence.viewcount.repository.ProductViewCountRepository
import com.music.sale.application.viewcount.dto.ProductViewCountOutput
import com.music.sale.application.viewcount.port.outport.ProductViewCountPort
import com.music.sale.domain.viewcount.ProductViewCount
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
@Transactional
class ProductViewCountPersistenceAdapter(
    private val productViewCountRepository: ProductViewCountRepository,
    private val mapper: ProductViewCountPersistenceMapper,
) : ProductViewCountPort {

    @Transactional(readOnly = true)
    override fun findByProductId(productId: Long): ProductViewCountOutput? {
        return productViewCountRepository.findByProductId(productId)?.let { 
            mapper.toOutput(it) 
        }
    }

    override fun save(viewCount: ProductViewCount): ProductViewCountOutput {
        val existingEntity = productViewCountRepository.findByProductId(viewCount.productId)
        
        val entityToSave = if (existingEntity != null) {
            // 기존 엔티티 업데이트
            existingEntity.updateViewCount(viewCount.currentViewers, viewCount.totalViews)
            existingEntity
        } else {
            // 새 엔티티 생성
            mapper.toEntity(viewCount)
        }
        
        val savedEntity = productViewCountRepository.save(entityToSave)
        return mapper.toOutput(savedEntity)
    }
} 
