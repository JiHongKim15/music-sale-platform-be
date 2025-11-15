// Copyright (C) 2024 Your Name or Company
package com.music.sale.web.like.response;

import com.music.sale.domain.like.LikeableType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 * 좋아요 생성 응답 DTO
 * Lombok 적용: @Getter, @AllArgsConstructor, @Builder
 */
@Getter
@AllArgsConstructor
@Builder
public class LikeResponse {
    private final Long id;
    private final Long userId;
    private final Long likeableId;
    private final LikeableType likeableType;
    private final LocalDateTime createdAt;
}

