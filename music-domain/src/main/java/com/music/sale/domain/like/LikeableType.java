// Copyright (C) 2024 Your Name or Company
package com.music.sale.domain.like;

/**
 * 좋아요 대상 타입
 * 다형적 관계(Polymorphic Association)를 위한 Enum
 */
public enum LikeableType {
    /** 상품(product_item)에 대한 좋아요 */
    PRODUCT,

    /** 스토어(stores)에 대한 좋아요(구독) */
    STORE,

    /** 판매자(users)에 대한 좋아요(팔로우) */
    SELLER
}

