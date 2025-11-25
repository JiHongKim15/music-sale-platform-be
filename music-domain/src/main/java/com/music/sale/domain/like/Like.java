package com.music.sale.domain.like;

import com.music.sale.domain.like.enums.LikeableType;
import lombok.Builder;

@Builder
public record Like(Long id, Long userId, Long likeableId, LikeableType likeableType) {

    public static Like create(Long userId, Long likeableId, LikeableType likeableType) {
        return new Like(null, userId, likeableId, likeableType);
    }
}
