// Copyright (C) 2024 Your Name or Company
package com.music.sale.web.like.response;

import com.music.sale.domain.like.enums.LikeableType;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.time.LocalDateTime;

@AllArgsConstructor
@Builder
public record CreateLikeResponse(Long id, Long userId, Long likeableId, LikeableType likeableType, LocalDateTime createdAt) {
}
