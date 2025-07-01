// Copyright (C) 2024 Your Name or Company
package com.music.sale.adapter.persistence.wishlist

import com.music.sale.adapter.persistence.product.repository.ProductItemRepository
import com.music.sale.adapter.persistence.user.repository.UserRepository
import com.music.sale.adapter.persistence.wishlist.entity.WishlistEntity
import com.music.sale.adapter.persistence.wishlist.mapper.WishlistPersistenceMapper
import com.music.sale.adapter.persistence.wishlist.repository.WishlistRepository
import com.music.sale.application.wishlist.dto.WishlistOutput
import com.music.sale.application.wishlist.port.outport.WishlistPort
import com.music.sale.common.Pageable
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
@Transactional
class WishlistPersistenceAdapter(
    private val wishlistRepository: WishlistRepository,
    private val userRepository: UserRepository,
    private val productItemRepository: ProductItemRepository,
    private val mapper: WishlistPersistenceMapper,
) : WishlistPort {

    @Transactional(readOnly = true)
    override fun findByUserId(userId: Long, pageable: Pageable): Page<WishlistOutput> {
        val springPageable = PageRequest.of(
            pageable.pageNumber,
            pageable.pageSize,
            Sort.by(Sort.Direction.DESC, "createdAt")
        )
        
        val userWishlists = wishlistRepository.findByUserId(userId)
        val startIndex = springPageable.offset.toInt()
        val endIndex = minOf(startIndex + springPageable.pageSize, userWishlists.size)
        
        val pagedWishlists = if (startIndex < userWishlists.size) {
            userWishlists.subList(startIndex, endIndex)
        } else {
            emptyList()
        }
        
        val wishlistOutputs = pagedWishlists.map { mapper.toOutput(it) }
        
        return PageImpl(wishlistOutputs, springPageable, userWishlists.size.toLong())
    }

    override fun save(userId: Long, productId: Long): WishlistOutput {
        val user = userRepository.findById(userId)
            .orElseThrow { IllegalArgumentException("사용자를 찾을 수 없습니다: $userId") }
        
        val product = productItemRepository.findById(productId)
            .orElseThrow { IllegalArgumentException("상품을 찾을 수 없습니다: $productId") }
        
        val wishlistEntity = WishlistEntity(
            user = user,
            product = product
        )
        
        val savedEntity = wishlistRepository.save(wishlistEntity)
        return mapper.toOutput(savedEntity)
    }

    override fun deleteByUserIdAndProductId(userId: Long, productId: Long) {
        // 찜 목록에 있는지 확인
        if (!wishlistRepository.existsByUserIdAndProductId(userId, productId)) {
            throw IllegalArgumentException("찜 목록을 찾을 수 없습니다")
        }
        
        wishlistRepository.deleteByUserIdAndProductId(userId, productId)
    }

    @Transactional(readOnly = true)
    override fun findByUserIdAndProductId(userId: Long, productId: Long): WishlistOutput? {
        return wishlistRepository.findByUserIdAndProductId(userId, productId)?.let { mapper.toOutput(it) }
    }

    @Transactional(readOnly = true)
    override fun existsByUserIdAndProductId(userId: Long, productId: Long): Boolean {
        return wishlistRepository.existsByUserIdAndProductId(userId, productId)
    }
} 
