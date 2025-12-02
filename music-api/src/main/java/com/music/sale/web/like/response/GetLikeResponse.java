package com.music.sale.web.like.response;

import com.music.sale.domain.like.enums.LikeableType;
import java.time.LocalDateTime;
import lombok.Builder;

@Builder
public record GetLikeResponse(
    Long id, Long userId, Long likeableId, LikeableType likeableType, LocalDateTime createdAt) {}
