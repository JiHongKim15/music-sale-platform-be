// Copyright (C) 2024 Your Name or Company
package com.music.sale.adapter.web.wishlist

import com.music.sale.adapter.web.common.ApiResponse
import com.music.sale.adapter.web.wishlist.mapper.WishlistWebMapper
import com.music.sale.adapter.web.wishlist.request.AddToWishlistRequest
import com.music.sale.adapter.web.wishlist.request.GetWishlistRequest
import com.music.sale.adapter.web.wishlist.request.RemoveFromWishlistRequest
import com.music.sale.adapter.web.wishlist.response.WishlistResponse
import com.music.sale.application.wishlist.port.inport.WishlistUseCase
import com.music.sale.common.ResponseCode
import org.springframework.data.domain.Page
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/wishlist")
class WishlistController(
    private val wishlistUseCase: WishlistUseCase,
    private val mapper: WishlistWebMapper,
) {

    /**
     * 사용자의 찜 목록 조회
     */
    @GetMapping
    fun getUserWishlist(
        @ModelAttribute request: GetWishlistRequest,
        @RequestParam userId: Long, // TODO: 실제로는 JWT 토큰에서 추출
    ): ResponseEntity<ApiResponse<Page<WishlistResponse>>> {
        val wishlistPage = wishlistUseCase.getUserWishlist(userId, request.toPageable())
        val responsePage = wishlistPage.map { mapper.toResponse(it) }
        
        return ResponseEntity.ok(
            ApiResponse.success(data = responsePage)
        )
    }

    /**
     * 상품 찜하기 (하트 클릭)
     */
    @PostMapping("/add")
    fun addToWishlist(
        @RequestBody request: AddToWishlistRequest,
        @RequestParam userId: Long, // TODO: 실제로는 JWT 토큰에서 추출
    ): ResponseEntity<ApiResponse<WishlistResponse>> {
        val productId = mapper.toProductId(request)
        val wishlistOutput = wishlistUseCase.addToWishlist(userId, productId)
        val response = mapper.toResponse(wishlistOutput)
        
        return ResponseEntity.ok(
            ApiResponse.success(
                data = response,
                code = ResponseCode.WISHLIST_ADDED.code
            )
        )
    }

    /**
     * 찜 목록에서 제거 (하트 해제)
     */
    @DeleteMapping("/remove")
    fun removeFromWishlist(
        @RequestBody request: RemoveFromWishlistRequest,
        @RequestParam userId: Long, // TODO: 실제로는 JWT 토큰에서 추출
    ): ResponseEntity<ApiResponse<String>> {
        val productId = mapper.toProductId(request)
        wishlistUseCase.removeFromWishlist(userId, productId)
        
        return ResponseEntity.ok(
            ApiResponse.success(
                data = "찜 목록에서 제거되었습니다",
                code = ResponseCode.WISHLIST_REMOVED.code
            )
        )
    }

    /**
     * 상품이 찜 목록에 있는지 확인
     */
    @GetMapping("/check/{productId}")
    fun checkWishlistStatus(
        @PathVariable productId: Long,
        @RequestParam userId: Long, // TODO: 실제로는 JWT 토큰에서 추출
    ): ResponseEntity<ApiResponse<Boolean>> {
        val isInWishlist = wishlistUseCase.isProductInWishlist(userId, productId)
        
        return ResponseEntity.ok(
            ApiResponse.success(data = isInWishlist)
        )
    }
} 
