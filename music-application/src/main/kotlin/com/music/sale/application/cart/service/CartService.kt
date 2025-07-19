// Copyright (C) 2024 Your Name or Company
package com.music.sale.application.cart.service

import com.music.sale.application.cart.dto.CartOutput
import com.music.sale.application.cart.port.inport.CartUseCase
import com.music.sale.application.cart.port.outport.CartPort
import com.music.sale.application.product.port.outport.ProductPort
import com.music.sale.application.user.port.outport.UserPort
import com.music.sale.common.Pageable
import com.music.sale.domain.cart.Cart
import org.springframework.data.domain.Page
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
open class CartService(
    private val cartPort: CartPort,
    private val userPort: UserPort,
    private val productPort: ProductPort,
) : CartUseCase {
    @Transactional(readOnly = true)
    override fun getUserCart(
        userId: Long,
        pageable: Pageable,
    ): Page<CartOutput> {
        return cartPort.findByUserId(userId, pageable)
    }

    override fun addToCart(
        userId: Long,
        productId: Long,
        quantity: Int,
    ): CartOutput {
        val user =
            userPort.findById(userId)
                ?: throw IllegalArgumentException("사용자를 찾을 수 없습니다: $userId")

        val product =
            productPort.findById(productId)
                ?: throw IllegalArgumentException("상품을 찾을 수 없습니다: $productId")

        // 이미 장바구니에 있는지 확인
        val existingCart = cartPort.findByUserIdAndProductId(userId, productId)

        return if (existingCart != null) {
            // 기존 수량에 추가
            val newQuantity = existingCart.quantity + quantity
            updateCartQuantity(userId, productId, newQuantity)
        } else {
            // 새로운 장바구니 아이템 생성
            val cart = Cart.create(user, product, quantity)
            cartPort.save(cart)
        }
    }

    override fun updateCartQuantity(
        userId: Long,
        productId: Long,
        quantity: Int,
    ): CartOutput {
        val existingCart =
            cartPort.findByUserIdAndProductId(userId, productId)
                ?: throw IllegalArgumentException("장바구니에서 해당 상품을 찾을 수 없습니다")

        val user =
            userPort.findById(userId)
                ?: throw IllegalArgumentException("사용자를 찾을 수 없습니다: $userId")

        val product =
            productPort.findById(productId)
                ?: throw IllegalArgumentException("상품을 찾을 수 없습니다: $productId")

        // 도메인 모델로 변환 후 수량 업데이트
        val cart =
            Cart(
                id = existingCart.id,
                user = user,
                product = product,
                quantity = existingCart.quantity,
                createdAt = existingCart.createdAt,
                updatedAt = existingCart.updatedAt,
            )

        val updatedCart = cart.updateQuantity(quantity)
        return cartPort.save(updatedCart)
    }

    override fun removeFromCart(
        userId: Long,
        productId: Long,
    ) {
        if (!cartPort.existsByUserIdAndProductId(userId, productId)) {
            throw IllegalArgumentException("장바구니에서 해당 상품을 찾을 수 없습니다")
        }

        cartPort.deleteByUserIdAndProductId(userId, productId)
    }

    override fun clearCart(userId: Long) {
        cartPort.deleteByUserId(userId)
    }

    @Transactional(readOnly = true)
    override fun getCartTotalAmount(userId: Long): Int {
        return cartPort.getTotalAmountByUserId(userId)
    }
}
