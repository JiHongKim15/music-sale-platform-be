// Copyright (C) 2024 Your Name or Company
package com.music.sale.adapter.persistence.cart

import com.music.sale.adapter.persistence.cart.entity.CartEntity
import com.music.sale.adapter.persistence.cart.mapper.CartPersistenceMapper
import com.music.sale.adapter.persistence.cart.repository.CartRepository
import com.music.sale.adapter.persistence.product.repository.ProductItemRepository
import com.music.sale.adapter.persistence.user.repository.UserRepository
import com.music.sale.application.cart.dto.CartOutput
import com.music.sale.application.cart.port.outport.CartPort
import com.music.sale.common.Pageable
import com.music.sale.domain.cart.Cart
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
@Transactional
class CartPersistenceAdapter(
    private val cartRepository: CartRepository,
    private val userRepository: UserRepository,
    private val productItemRepository: ProductItemRepository,
    private val mapper: CartPersistenceMapper,
) : CartPort {

    @Transactional(readOnly = true)
    override fun findByUserId(userId: Long, pageable: Pageable): Page<CartOutput> {
        val springPageable = PageRequest.of(
            pageable.pageNumber,
            pageable.pageSize,
            Sort.by(Sort.Direction.DESC, "createdAt")
        )
        
        val userCarts = cartRepository.findByUserId(userId)
        val startIndex = springPageable.offset.toInt()
        val endIndex = minOf(startIndex + springPageable.pageSize, userCarts.size)
        
        val pagedCarts = if (startIndex < userCarts.size) {
            userCarts.subList(startIndex, endIndex)
        } else {
            emptyList()
        }
        
        val cartOutputs = pagedCarts.map { mapper.toOutput(it) }
        
        return PageImpl(cartOutputs, springPageable, userCarts.size.toLong())
    }

    override fun save(cart: Cart): CartOutput {
        val user = userRepository.findById(cart.user.id!!)
            .orElseThrow { IllegalArgumentException("사용자를 찾을 수 없습니다: ${cart.user.id}") }
        
        val product = productItemRepository.findById(cart.product.id!!)
            .orElseThrow { IllegalArgumentException("상품을 찾을 수 없습니다: ${cart.product.id}") }
        
        val cartEntity = CartEntity(
            id = cart.id,
            user = user,
            product = product,
            quantity = cart.quantity
        )
        
        val savedEntity = cartRepository.save(cartEntity)
        return mapper.toOutput(savedEntity)
    }

    override fun deleteByUserIdAndProductId(userId: Long, productId: Long) {
        if (!cartRepository.existsByUserIdAndProductId(userId, productId)) {
            throw IllegalArgumentException("장바구니를 찾을 수 없습니다")
        }
        
        cartRepository.deleteByUserIdAndProductId(userId, productId)
    }

    @Transactional(readOnly = true)
    override fun findByUserIdAndProductId(userId: Long, productId: Long): CartOutput? {
        return cartRepository.findByUserIdAndProductId(userId, productId)?.let { mapper.toOutput(it) }
    }

    @Transactional(readOnly = true)
    override fun existsByUserIdAndProductId(userId: Long, productId: Long): Boolean {
        return cartRepository.existsByUserIdAndProductId(userId, productId)
    }

    override fun deleteByUserId(userId: Long) {
        cartRepository.deleteByUserId(userId)
    }

    @Transactional(readOnly = true)
    override fun getTotalAmountByUserId(userId: Long): Int {
        return cartRepository.getTotalAmountByUserId(userId)
    }
} 
