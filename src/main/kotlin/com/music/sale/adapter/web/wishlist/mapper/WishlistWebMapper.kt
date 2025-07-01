// Copyright (C) 2024 Your Name or Company
package com.music.sale.adapter.web.wishlist.mapper

import com.music.sale.adapter.web.wishlist.request.AddToWishlistRequest
import com.music.sale.adapter.web.wishlist.request.RemoveFromWishlistRequest
import com.music.sale.adapter.web.wishlist.response.WishlistResponse
import com.music.sale.application.wishlist.dto.WishlistOutput
import org.springframework.stereotype.Component

/**
 * 찜 목록 웹 매퍼
 */
@Component
class WishlistWebMapper {
    
    fun toResponse(wishlistOutput: WishlistOutput): WishlistResponse {
        return WishlistResponse.from(wishlistOutput)
    }
    
    fun toProductId(request: AddToWishlistRequest): Long {
        return request.productId
    }
    
    fun toProductId(request: RemoveFromWishlistRequest): Long {
        return request.productId
    }
} 
