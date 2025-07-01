// Copyright (C) 2024 Your Name or Company
package com.music.sale.adapter.web.cart

import com.music.sale.adapter.web.cart.request.AddToCartRequest
import com.music.sale.adapter.web.cart.request.GetCartRequest
import com.music.sale.adapter.web.cart.request.RemoveFromCartRequest
import com.music.sale.adapter.web.cart.request.UpdateCartQuantityRequest
import com.music.sale.adapter.web.common.ApiResponse
import com.music.sale.application.cart.dto.CartOutput
import com.music.sale.application.cart.port.inport.CartUseCase
import com.music.sale.common.ResponseCode
import org.springframework.data.domain.Page
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/cart")
class CartController(
    private val cartUseCase: CartUseCase,
) {

    /**
     * 사용자의 장바구니 조회
     */
    @GetMapping
    fun getUserCart(
        @ModelAttribute request: GetCartRequest,
        @RequestParam userId: Long, // TODO: 실제로는 JWT 토큰에서 추출
    ): ResponseEntity<ApiResponse<Page<CartOutput>>> {
        val cartPage = cartUseCase.getUserCart(userId, request.toPageable())
        
        return ResponseEntity.ok(
            ApiResponse.success(data = cartPage)
        )
    }

    /**
     * 장바구니에 상품 추가
     */
    @PostMapping("/add")
    fun addToCart(
        @RequestBody request: AddToCartRequest,
        @RequestParam userId: Long, // TODO: 실제로는 JWT 토큰에서 추출
    ): ResponseEntity<ApiResponse<CartOutput>> {
        val cartOutput = cartUseCase.addToCart(userId, request.productId, request.quantity)
        
        return ResponseEntity.ok(
            ApiResponse.success(
                data = cartOutput,
                code = ResponseCode.CART_ADDED.code
            )
        )
    }

    /**
     * 장바구니 상품 수량 변경
     */
    @PutMapping("/quantity")
    fun updateCartQuantity(
        @RequestBody request: UpdateCartQuantityRequest,
        @RequestParam userId: Long, // TODO: 실제로는 JWT 토큰에서 추출
    ): ResponseEntity<ApiResponse<CartOutput>> {
        val cartOutput = cartUseCase.updateCartQuantity(userId, request.productId, request.quantity)
        
        return ResponseEntity.ok(
            ApiResponse.success(
                data = cartOutput,
                code = ResponseCode.CART_UPDATED.code
            )
        )
    }

    /**
     * 장바구니에서 상품 제거
     */
    @DeleteMapping("/remove")
    fun removeFromCart(
        @RequestBody request: RemoveFromCartRequest,
        @RequestParam userId: Long, // TODO: 실제로는 JWT 토큰에서 추출
    ): ResponseEntity<ApiResponse<String>> {
        cartUseCase.removeFromCart(userId, request.productId)
        
        return ResponseEntity.ok(
            ApiResponse.success(
                data = "장바구니에서 제거되었습니다",
                code = ResponseCode.CART_REMOVED.code
            )
        )
    }

    /**
     * 장바구니 비우기
     */
    @DeleteMapping("/clear")
    fun clearCart(
        @RequestParam userId: Long, // TODO: 실제로는 JWT 토큰에서 추출
    ): ResponseEntity<ApiResponse<String>> {
        cartUseCase.clearCart(userId)
        
        return ResponseEntity.ok(
            ApiResponse.success(
                data = "장바구니가 비워졌습니다",
                code = ResponseCode.CART_CLEARED.code
            )
        )
    }

    /**
     * 장바구니 총 금액 조회
     */
    @GetMapping("/total")
    fun getCartTotalAmount(
        @RequestParam userId: Long, // TODO: 실제로는 JWT 토큰에서 추출
    ): ResponseEntity<ApiResponse<Int>> {
        val totalAmount = cartUseCase.getCartTotalAmount(userId)
        
        return ResponseEntity.ok(
            ApiResponse.success(data = totalAmount)
        )
    }
} 
