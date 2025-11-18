// Copyright (C) 2024 Your Name or Company
package com.music.sale.domain.like;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * 좋아요 도메인 모델
 * 다형적 관계를 통해 상품, 스토어, 판매자에 대한 좋아요를 관리합니다.
 */
public class Like {
    private final Long id;
    private final Long userId;
    private final Long likeableId;
    private final LikeableType likeableType;
    private final LocalDateTime createdAt;

    private Like(Long id, Long userId, Long likeableId, LikeableType likeableType, LocalDateTime createdAt) {
        validateUserId(userId);
        validateLikeableId(likeableId);
        validateLikeableType(likeableType);
        this.id = id;
        this.userId = userId;
        this.likeableId = likeableId;
        this.likeableType = likeableType;
        this.createdAt = resolveCreatedAt(createdAt);
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

    private LocalDateTime resolveCreatedAt(LocalDateTime createdAt) {
        return createdAt != null ? createdAt : LocalDateTime.now();
    }

    /**
     * 좋아요 생성
     */
    public static Like create(Long userId, Long likeableId, LikeableType likeableType) {
        return new Like(null, userId, likeableId, likeableType, LocalDateTime.now());
    }

    /**
     * 기존 좋아요 재구성 (영속성 계층에서 사용)
     */
    public static Like of(Long id, Long userId, Long likeableId, LikeableType likeableType, LocalDateTime createdAt) {
        return new Like(id, userId, likeableId, likeableType, createdAt);
    }

    /**
     * 비즈니스 규칙: 같은 사용자가 같은 대상에 대한 좋아요인지 확인
     */
    public boolean isSameTarget(Long userId, Long likeableId, LikeableType likeableType) {
        return isSameUser(userId) && isSameLikeable(likeableId) && isSameType(likeableType);
    }

    private boolean isSameUser(Long userId) {
        return Objects.equals(this.userId, userId);
    }

    private boolean isSameLikeable(Long likeableId) {
        return Objects.equals(this.likeableId, likeableId);
    }

    private boolean isSameType(LikeableType likeableType) {
        return this.likeableType == likeableType;
    }

    // Getters
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

    @Override
    public boolean equals(Object o) {
        if (isNotLikeInstance(o)) return false;
        return hasSameId((Like) o);
    }

    private boolean isNotLikeInstance(Object o) {
        return this != o && (o == null || getClass() != o.getClass());
    }

    private boolean hasSameId(Like other) {
        return Objects.equals(id, other.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Like{" +
                "id=" + id +
                ", userId=" + userId +
                ", likeableId=" + likeableId +
                ", likeableType=" + likeableType +
                ", createdAt=" + createdAt +
                '}';
    }
}

