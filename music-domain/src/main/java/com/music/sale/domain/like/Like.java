package com.music.sale.domain.like;

import com.music.sale.domain.like.enums.LikeableType;

import lombok.Builder;
import lombok.Getter;

@Getter
public class Like {
    private final Long id;
    private final Long userId;
    private final Long likeableId;
    private final LikeableType likeableType;

    @Builder
    public Like(Long id, Long userId, Long likeableId, LikeableType likeableType) {
        if (userId == null || userId <= 0) {
            throw new IllegalArgumentException("사용자 ID는 양수여야 합니다");
        }
        if (likeableId == null || likeableId <= 0) {
            throw new IllegalArgumentException("좋아요 대상 ID는 양수여야 합니다");
        }
        if (likeableType == null) {
            throw new IllegalArgumentException("좋아요 대상 타입은 필수입니다");
        }
        this.id = id;
        this.userId = userId;
        this.likeableId = likeableId;
        this.likeableType = likeableType;
    }

}
