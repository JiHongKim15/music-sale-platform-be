package com.music.sale.application.like.dto.output;

import com.music.sale.domain.like.enums.LikeableType;
import lombok.Builder;

import java.time.LocalDateTime;

/**
 * 좋아요 조회 결과 Output DTO
 */
@Builder
public record GetLikeOutput(Long id, Long userId, Long likeableId, LikeableType likeableType, LocalDateTime createdAt) {
}
