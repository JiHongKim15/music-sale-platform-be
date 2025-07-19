// Copyright (C) 2024 Your Name or Company
package com.music.sale.application.viewcount.service

import com.music.sale.application.viewcount.dto.ProductViewCountOutput
import com.music.sale.application.viewcount.port.inport.ProductViewCountUseCase
import com.music.sale.application.viewcount.port.outport.ProductViewCountPort
import com.music.sale.domain.viewcount.ProductViewCount
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
open class ProductViewCountService(
    private val productViewCountPort: ProductViewCountPort,
    private val messagingTemplate: SimpMessagingTemplate,
) : ProductViewCountUseCase {
    override fun getProductViewCount(productId: Long): ProductViewCountOutput {
        return productViewCountPort.findByProductId(productId)
            ?: ProductViewCountOutput(productId, 0, 0)
    }

    override fun incrementViewer(productId: Long): ProductViewCountOutput {
        val existingViewCount = productViewCountPort.findByProductId(productId)
        val domainViewCount =
            if (existingViewCount != null) {
                // DTO를 도메인 모델로 변환
                val domain =
                    ProductViewCount(
                        productId = existingViewCount.productId,
                        currentViewers = existingViewCount.currentViewers,
                        totalViews = existingViewCount.totalViews,
                        lastUpdated = existingViewCount.lastUpdated,
                    )
                domain.incrementViewer()
            } else {
                ProductViewCount(
                    productId = productId,
                    currentViewers = 1,
                    totalViews = 1,
                )
            }

        val updatedViewCount = productViewCountPort.save(domainViewCount)

        // WebSocket으로 실시간 업데이트 전송
        broadcastViewCountUpdate(updatedViewCount)

        return updatedViewCount
    }

    override fun decrementViewer(productId: Long): ProductViewCountOutput {
        val existingViewCount =
            productViewCountPort.findByProductId(productId)
                ?: return ProductViewCountOutput(productId, 0, 0)

        // DTO를 도메인 모델로 변환
        val domain =
            ProductViewCount(
                productId = existingViewCount.productId,
                currentViewers = existingViewCount.currentViewers,
                totalViews = existingViewCount.totalViews,
                lastUpdated = existingViewCount.lastUpdated,
            )

        val updatedViewCount = productViewCountPort.save(domain.decrementViewer())

        // WebSocket으로 실시간 업데이트 전송
        broadcastViewCountUpdate(updatedViewCount)

        return updatedViewCount
    }

    override fun addView(productId: Long): ProductViewCountOutput {
        val existingViewCount = productViewCountPort.findByProductId(productId)
        val domainViewCount =
            if (existingViewCount != null) {
                // DTO를 도메인 모델로 변환
                val domain =
                    ProductViewCount(
                        productId = existingViewCount.productId,
                        currentViewers = existingViewCount.currentViewers,
                        totalViews = existingViewCount.totalViews,
                        lastUpdated = existingViewCount.lastUpdated,
                    )
                domain.addView()
            } else {
                ProductViewCount(
                    productId = productId,
                    currentViewers = 0,
                    totalViews = 1,
                )
            }

        val updatedViewCount = productViewCountPort.save(domainViewCount)

        return updatedViewCount
    }

    private fun broadcastViewCountUpdate(viewCount: ProductViewCountOutput) {
        messagingTemplate.convertAndSend(
            "/topic/product/${viewCount.productId}/viewcount",
            viewCount,
        )
    }
}
