// Copyright (C) 2024 Your Name or Company
package com.music.sale.web.like.response;

/**
 * 좋아요 상태 응답 DTO
 */
public class LikeStatusResponse {
    private final boolean isLiked;

    public LikeStatusResponse(boolean isLiked) {
        this.isLiked = isLiked;
    }

    public boolean isLiked() {
        return isLiked;
    }
}

