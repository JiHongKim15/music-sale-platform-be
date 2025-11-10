// Copyright (C) 2024 Your Name or Company
package com.music.sale.application.like.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 좋아요 상태 출력 DTO
 * Lombok 적용: @Getter, @AllArgsConstructor
 */
@Getter
@AllArgsConstructor
public class LikeStatusOutput {
    private final boolean isLiked;
}

