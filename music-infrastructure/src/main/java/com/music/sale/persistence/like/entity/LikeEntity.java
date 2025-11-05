// Copyright (C) 2024 Your Name or Company
package com.music.sale.persistence.like.entity;

import com.music.sale.domain.like.Like;
import com.music.sale.domain.like.LikeableType;
import jakarta.persistence.*;

import java.time.LocalDateTime;

/**
 * 좋아요 JPA Entity
 * DB 스키마의 likes 테이블과 매핑됩니다.
 */
@Entity
@Table(
        name = "likes",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uix_user_likeable",
                        columnNames = {"user_id", "likeable_type", "likeable_id"}
                )
        },
        indexes = {
                @Index(
                        name = "idx_user_type_created",
                        columnList = "user_id, likeable_type, created_at DESC"
                ),
                @Index(
                        name = "idx_likeable",
                        columnList = "likeable_type, likeable_id"
                )
        }
)
public class LikeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "likeable_id", nullable = false)
    private Long likeableId;

    @Enumerated(EnumType.STRING)
    @Column(name = "likeable_type", nullable = false, length = 20)
    private LikeableType likeableType;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    protected LikeEntity() {
        // JPA 기본 생성자
    }

    public LikeEntity(Long id, Long userId, Long likeableId, LikeableType likeableType, LocalDateTime createdAt) {
        this.id = id;
        this.userId = userId;
        this.likeableId = likeableId;
        this.likeableType = likeableType;
        this.createdAt = createdAt != null ? createdAt : LocalDateTime.now();
    }

    /**
     * Entity -> Domain 변환
     */
    public Like toDomain() {
        return Like.of(id, userId, likeableId, likeableType, createdAt);
    }

    /**
     * Domain -> Entity 변환
     */
    public static LikeEntity fromDomain(Like like) {
        return new LikeEntity(
                like.getId(),
                like.getUserId(),
                like.getLikeableId(),
                like.getLikeableType(),
                like.getCreatedAt()
        );
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getLikeableId() {
        return likeableId;
    }

    public void setLikeableId(Long likeableId) {
        this.likeableId = likeableId;
    }

    public LikeableType getLikeableType() {
        return likeableType;
    }

    public void setLikeableType(LikeableType likeableType) {
        this.likeableType = likeableType;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @PrePersist
    protected void onCreate() {
        if (this.createdAt == null) {
            this.createdAt = LocalDateTime.now();
        }
    }
}

