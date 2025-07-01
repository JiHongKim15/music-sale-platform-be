// Copyright (C) 2024 Your Name or Company
package com.music.sale.adapter.web.viewcount

import com.music.sale.adapter.web.common.ApiResponse
import com.music.sale.application.viewcount.port.inport.ProductViewCountUseCase
import org.springframework.http.ResponseEntity
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/viewcount")
class ProductViewCountController(
    private val productViewCountUseCase: ProductViewCountUseCase,
) {

    /**
     * 상품 조회수 조회
     */
    @GetMapping("/product/{productId}")
    fun getProductViewCount(
        @PathVariable productId: Long,
    ): ResponseEntity<ApiResponse<com.music.sale.application.viewcount.dto.ProductViewCountOutput>> {
        val viewCount = productViewCountUseCase.getProductViewCount(productId)
        return ResponseEntity.ok(ApiResponse.success(data = viewCount))
    }

    /**
     * 페이지 진입 (조회자 수 증가)
     */
    @PostMapping("/product/{productId}/enter")
    fun enterProduct(
        @PathVariable productId: Long,
    ): ResponseEntity<ApiResponse<com.music.sale.application.viewcount.dto.ProductViewCountOutput>> {
        val viewCount = productViewCountUseCase.incrementViewer(productId)
        return ResponseEntity.ok(ApiResponse.success(data = viewCount))
    }

    /**
     * 페이지 이탈 (조회자 수 감소)
     */
    @PostMapping("/product/{productId}/leave")
    fun leaveProduct(
        @PathVariable productId: Long,
    ): ResponseEntity<ApiResponse<com.music.sale.application.viewcount.dto.ProductViewCountOutput>> {
        val viewCount = productViewCountUseCase.decrementViewer(productId)
        return ResponseEntity.ok(ApiResponse.success(data = viewCount))
    }

    /**
     * 페이지 조회 (총 조회수 증가)
     */
    @PostMapping("/product/{productId}/view")
    fun viewProduct(
        @PathVariable productId: Long,
    ): ResponseEntity<ApiResponse<com.music.sale.application.viewcount.dto.ProductViewCountOutput>> {
        val viewCount = productViewCountUseCase.addView(productId)
        return ResponseEntity.ok(ApiResponse.success(data = viewCount))
    }

    /**
     * WebSocket 메시지 핸들러 - 페이지 진입
     */
    @MessageMapping("/product/enter")
    @SendTo("/topic/product/{productId}/viewcount")
    fun handleEnterProduct(productId: Long): com.music.sale.application.viewcount.dto.ProductViewCountOutput {
        return productViewCountUseCase.incrementViewer(productId)
    }

    /**
     * WebSocket 메시지 핸들러 - 페이지 이탈
     */
    @MessageMapping("/product/leave")
    @SendTo("/topic/product/{productId}/viewcount")
    fun handleLeaveProduct(productId: Long): com.music.sale.application.viewcount.dto.ProductViewCountOutput {
        return productViewCountUseCase.decrementViewer(productId)
    }
} 
