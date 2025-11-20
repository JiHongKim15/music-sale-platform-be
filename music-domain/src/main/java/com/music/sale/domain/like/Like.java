// Copyright (C) 2024 Your Name or Company
package com.music.sale.domain.like;

import java.time.LocalDateTime;
import java.util.Objects;

public class Like {
    private final Long id;
    private final Long userId;
    private final Long likeableId;
    private final LikeableType likeableType;

    private Like(Long id, Long userId, Long likeableId, LikeableType likeableType) {
        validateUserId(userId);
        validateLikeableId(likeableId);
        validateLikeableType(likeableType);
        this.id = id;
        this.userId = userId;
        this.likeableId = likeableId;
        this.likeableType = likeableType;
    }

    private void validateUserId(Long userId) {
        if (isInvalidId(userId)) {
            throw new IllegalArgumentException("사용자 ID는 양수여야 합니다");
        }
    }

    private void validateLikeableId(Long likeableId) {
        if (isInvalidId(likeableId)) {
            throw new IllegalArgumentException("좋아요 대상 ID는 양수여야 합니다");
        }
    }

    private void validateLikeableType(LikeableType likeableType) {
        if (likeableType == null) {
            throw new IllegalArgumentException("좋아요 대상 타입은 필수입니다");
        }
    }

    private boolean isInvalidId(Long id) {
        return id == null || id <= 0;
    }

    public static Like create(Long userId, Long likeableId, LikeableType likeableType) {
        return new Like(null, userId, likeableId, likeableType);
    }

}

