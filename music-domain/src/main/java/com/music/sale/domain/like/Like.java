package com.music.sale.domain.like;

import com.music.sale.domain.like.enums.LikeableType;

public record Like(Long id, Long userId, Long likeableId, LikeableType likeableType) {

}
