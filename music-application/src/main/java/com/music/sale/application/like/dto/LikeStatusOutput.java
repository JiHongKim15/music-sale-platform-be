// Copyright (C) 2024 Your Name or Company
package com.music.sale.application.like.dto;

/**
 * 좋아요 상태 출력 DTO
 */
public class LikeStatusOutput {
    private final boolean isLiked;

    public LikeStatusOutput(boolean isLiked) {
        this.isLiked = isLiked;
    }

    public boolean isLiked() {
        return isLiked;
    }
}

