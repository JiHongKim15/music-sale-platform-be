package com.music.sale.domain.product.enum

/**
 * 상품 상태 enum
 * 상품의 판매 상태를 나타냄
 */
enum class ProductStatus {
    DRAFT,      // 초안 (아직 판매 시작 안함)
    ACTIVE,     // 판매 중
    SOLD_OUT,   // 품절
    INACTIVE    // 판매 중지
} 