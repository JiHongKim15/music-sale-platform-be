// Copyright (C) 2024 Your Name or Company
package com.music.sale.web.like.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 좋아요 상태 응답 DTO
 * Lombok 적용: @Getter, @AllArgsConstructor
 */
@Getter
@AllArgsConstructor
public class LikeStatusResponse {
    private final boolean isLiked;
}

