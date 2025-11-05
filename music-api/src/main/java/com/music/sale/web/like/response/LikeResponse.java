// Copyright (C) 2024 Your Name or Company
package com.music.sale.web.like.response;

import com.music.sale.domain.like.LikeableType;

import java.time.LocalDateTime;

/**
 * 좋아요 생성 응답 DTO
 */
public class LikeResponse {
    private final Long id;
    private final Long userId;
    private final Long likeableId;
    private final LikeableType likeableType;
    private final LocalDateTime createdAt;

    public LikeResponse(Long id, Long userId, Long likeableId, LikeableType likeableType, LocalDateTime createdAt) {
        this.id = id;
        this.userId = userId;
        this.likeableId = likeableId;
        this.likeableType = likeableType;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getLikeableId() {
        return likeableId;
    }

    public LikeableType getLikeableType() {
        return likeableType;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}

