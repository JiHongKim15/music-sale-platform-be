package com.sodamjae.domain.product

import com.sodamjae.domain.shop.Shop
import java.math.BigDecimal

class Product private constructor(
    val id: ProductId?,
    private var name: Name,
    private var description: Description,
    private var price: Price,
    private var category: Category,
    private var condition: ProductCondition,
    private var quantity: Quantity,
    val shop: Shop
) {
    // Getters for persistence
    fun getName(): Name = name
    fun getDescription(): Description = description
    fun getPrice(): Price = price
    fun getCategory(): Category = category
    fun getCondition(): ProductCondition = condition
    fun getQuantity(): Quantity = quantity

    companion object {
        fun create(
            name: Name,
            description: Description,
            price: Price,
            category: Category,
            condition: ProductCondition,
            quantity: Quantity,
            shop: Shop
        ): Product {
            return Product(
                id = null,
                name = name,
                description = description,
                price = price,
                category = category,
                condition = condition,
                quantity = quantity,
                shop = shop
            )
        }
    }

    fun updateProduct(
        name: Name,
        description: Description,
        price: Price,
        category: Category,
        condition: ProductCondition,
        quantity: Quantity
    ) {
        this.name = name
        this.description = description
        this.price = price
        this.category = category
        this.condition = condition
        this.quantity = quantity
    }

    fun decreaseQuantity(amount: Int) {
        val newQuantity = this.quantity.value - amount
        require(newQuantity >= 0) { "재고가 부족합니다" }
        this.quantity = Quantity(newQuantity)
    }

    // Value Objects
    @JvmInline
    value class ProductId(val value: Long)

    @JvmInline
    value class Name(val value: String) {
        init {
            require(value.isNotBlank()) { "상품명은 비어있을 수 없습니다" }
        }
    }

    @JvmInline
    value class Description(val value: String) {
        init {
            require(value.isNotBlank()) { "상품 설명은 비어있을 수 없습니다" }
        }
    }

    @JvmInline
    value class Price(val value: BigDecimal) {
        init {
            require(value >= BigDecimal.ZERO) { "가격은 0 이상이어야 합니다" }
        }
    }

    @JvmInline
    value class Quantity(val value: Int) {
        init {
            require(value >= 0) { "수량은 0 이상이어야 합니다" }
        }
    }

    enum class Category {
        GUITAR, PIANO, DRUM, BASS, VIOLIN, OTHER
    }

    enum class ProductCondition {
        NEW, LIKE_NEW, GOOD, FAIR
    }
} 