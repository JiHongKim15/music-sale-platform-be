// Copyright (C) 2024 Your Name or Company
package com.music.sale.application.wishlist.service

import com.music.sale.application.wishlist.mapper.WishlistMapper
import com.music.sale.application.wishlist.port.inport.WishlistUseCase
import com.music.sale.application.wishlist.port.outport.WishlistPort
import com.music.sale.common.Pageable
import org.springframework.data.domain.Page
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class WishlistService(
    private val wishlistPort: WishlistPort,
    private val mapper: WishlistMapper,
) : WishlistUseCase {

    @Transactional(readOnly = true)
    override fun getUserWishlist(userId: Long, pageable: Pageable): Page<com.music.sale.application.wishlist.dto.WishlistOutput> {
        return wishlistPort.findByUserId(userId, pageable)
    }

    override fun addToWishlist(userId: Long, productId: Long): com.music.sale.application.wishlist.dto.WishlistOutput {
        // 이미 찜 목록에 있는지 확인
        if (wishlistPort.existsByUserIdAndProductId(userId, productId)) {
            throw IllegalArgumentException("이미 찜 목록에 추가된 상품입니다")
        }
        
        return wishlistPort.save(userId, productId)
    }

    override fun removeFromWishlist(userId: Long, productId: Long) {
        // 찜 목록에 있는지 확인
        if (!wishlistPort.existsByUserIdAndProductId(userId, productId)) {
            throw IllegalArgumentException("찜 목록에 없는 상품입니다")
        }
        
        wishlistPort.deleteByUserIdAndProductId(userId, productId)
    }

    @Transactional(readOnly = true)
    override fun isProductInWishlist(userId: Long, productId: Long): Boolean {
        return wishlistPort.existsByUserIdAndProductId(userId, productId)
    }
} 
