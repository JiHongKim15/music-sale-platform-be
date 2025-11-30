package com.music.sale.persistence.like.entity;

import com.music.sale.domain.like.enums.LikeableType;
import com.music.sale.persistence.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

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
public class LikeEntity extends BaseEntity {

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
}

