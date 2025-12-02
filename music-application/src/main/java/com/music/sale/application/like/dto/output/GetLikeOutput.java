package com.music.sale.application.like.dto.output;

import com.music.sale.domain.like.enums.LikeableType;
import java.time.LocalDateTime;
import lombok.Builder;

@Builder
public record GetLikeOutput(
    Long id, Long userId, Long likeableId, LikeableType likeableType, LocalDateTime createdAt) {}
