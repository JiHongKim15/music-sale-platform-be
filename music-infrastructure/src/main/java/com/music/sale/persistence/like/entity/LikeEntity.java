package com.music.sale.persistence.like.entity;

import com.music.sale.domain.like.LikeableType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

/**
 * 좋아요 JPA Entity
 * DB 스키마의 likes 테이블과 매핑됩니다.
 * Lombok 적용: @Getter, @NoArgsConstructor, @AllArgsConstructor, @Builder
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
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
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

    @PrePersist
    protected void onCreate() {
        if (this.createdAt == null) {
            this.createdAt = LocalDateTime.now();
        }
    }
}

