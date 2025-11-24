package com.music.sale.application.like.dto.input;

import com.music.sale.domain.like.enums.LikeableType;
import lombok.Builder;

/**
 * 좋아요 생성 Input DTO
 */
@Builder
public record CreateLikeInput(Long userId, Long likeableId, LikeableType likeableType) {
}
