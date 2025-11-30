package com.music.sale.web.like.response;

import com.music.sale.domain.like.enums.LikeableType;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record GetLikeResponse(Long id, Long userId, Long likeableId, LikeableType likeableType,
                              LocalDateTime createdAt) {
}
