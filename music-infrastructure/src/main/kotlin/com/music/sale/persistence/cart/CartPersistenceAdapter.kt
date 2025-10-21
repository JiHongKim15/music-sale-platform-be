package com.music.sale.persistence.cart

import com.music.sale.application.cart.dto.CartOutput
import com.music.sale.application.cart.port.outport.CartPort
import com.music.sale.application.product.dto.ProductCatalog
import com.music.sale.application.product.dto.ProductOutput
import com.music.sale.common.Pageable
import com.music.sale.domain.cart.Cart
import com.music.sale.domain.category.Category
import com.music.sale.domain.category.CategoryType
import com.music.sale.domain.product.enum.ProductCondition
import com.music.sale.domain.product.enum.ProductStatus
import com.music.sale.persistence.cart.mapper.CartMapper
import com.music.sale.persistence.cart.repository.CartRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.stereotype.Repository

@Repository
open class CartPersistenceAdapter(
    private val cartRepository: CartRepository,
    private val cartMapper: CartMapper,
) : CartPort {
    override fun findByUserId(
        userId: Long,
        pageable: Pageable,
    ): Page<CartOutput> {
        val cart = cartRepository.findByUserId(userId)
        return if (cart != null) {
            // TODO: Load product details
            val cartOutput =
                CartOutput(
                    id = cart.id ?: 0L,
                    userId = cart.userId,
                    product =
                        ProductOutput(
                            id = cart.productId,
                            name = "Product",
                            catalog =
                                ProductCatalog(
                                    id = 1L,
                                    name = "Product",
                                    categories =
                                        listOf(
                                            Category(
                                                id = 1L,
                                                name = "Category",
                                                type = CategoryType.PRODUCT,
                                                parent = null,
                                                path = "/1",
                                                depth = 0,
                                            ),
                                        ),
                                    brand = "Brand",
                                    attribute = emptyMap(),
                                ),
                            // 임시 가격
                            price = cart.quantity * 1000,
                            seller = null,
                            store = null,
                            condition = ProductCondition.NEW,
                            conditionGrade = null,
                            stockQuantity = cart.quantity,
                            status = ProductStatus.SELLING,
                            attributes = null,
                            images = null,
                        ),
                    quantity = cart.quantity,
                    // 임시 가격
                    totalPrice = cart.quantity * 1000,
                    createdAt = cart.createdAt,
                    updatedAt = cart.updatedAt,
                )
            PageImpl(listOf(cartOutput))
        } else {
            PageImpl(emptyList())
        }
    }

    override fun save(cart: Cart): CartOutput {
        val cartEntity = cartMapper.toEntity(cart)
        val savedEntity = cartRepository.save(cartEntity)
        return CartOutput(
            id = savedEntity.id ?: 0L,
            userId = savedEntity.userId,
            product =
                ProductOutput(
                    id = savedEntity.productId,
                    name = "Product",
                    catalog =
                        ProductCatalog(
                            id = 1L,
                            name = "Product",
                            categories =
                                listOf(
                                    Category(
                                        id = 1L,
                                        name = "Category",
                                        type = CategoryType.PRODUCT,
                                        parent = null,
                                        path = "/1",
                                        depth = 0,
                                    ),
                                ),
                            brand = "Brand",
                            attribute = emptyMap(),
                        ),
                    // 임시 가격
                    price = savedEntity.quantity * 1000,
                    seller = null,
                    store = null,
                    condition = ProductCondition.NEW,
                    conditionGrade = null,
                    stockQuantity = savedEntity.quantity,
                    status = ProductStatus.SELLING,
                    attributes = null,
                    images = null,
                ),
            quantity = savedEntity.quantity,
            // 임시 가격
            totalPrice = savedEntity.quantity * 1000,
            createdAt = savedEntity.createdAt,
            updatedAt = savedEntity.updatedAt,
        )
    }

    override fun deleteByUserIdAndProductId(
        userId: Long,
        productId: Long,
    ) {
        // TODO: Implement
    }

    override fun findByUserIdAndProductId(
        userId: Long,
        productId: Long,
    ): CartOutput? {
        // TODO: Implement
        return null
    }

    override fun existsByUserIdAndProductId(
        userId: Long,
        productId: Long,
    ): Boolean {
        // TODO: Implement
        return false
    }

    override fun deleteByUserId(userId: Long) {
        // TODO: Implement
    }

    override fun getTotalAmountByUserId(userId: Long): Int {
        // TODO: Implement
        return 0
    }
}
