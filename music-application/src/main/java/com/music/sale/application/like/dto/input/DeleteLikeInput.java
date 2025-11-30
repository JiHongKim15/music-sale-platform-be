package com.music.sale.application.like.dto.input;

import com.music.sale.domain.like.enums.LikeableType;
import lombok.Builder;

/**
 * 좋아요 삭제 Input DTO
 */
@Builder
public record DeleteLikeInput(Long userId, Long likeableId, LikeableType likeableType) {
}

