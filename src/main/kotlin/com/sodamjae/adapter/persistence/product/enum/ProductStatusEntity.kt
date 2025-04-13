package com.sodamjae.adapter.persistence.product.enum

/**
 * 상품 상태 엔티티 enum
 * 데이터베이스에 저장되는 값
 */
enum class ProductStatusEntity {
    DRAFT, 
    ACTIVE, 
    SOLD_OUT, 
    INACTIVE
} 