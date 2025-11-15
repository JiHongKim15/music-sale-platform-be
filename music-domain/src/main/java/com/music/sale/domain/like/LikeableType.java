package com.music.sale.domain.like;

public enum LikeableType {
    PRODUCT("상품 좋아요"),
    STORE("매장 구독"),
    SELLER("판매자 팔로우");

    private final String korean;

    LikeableType(String korean) {
        this.korean = korean;
    }

    public String getKorean() {
        return korean;
    }
}

