package com.music.sale.application.like.dto.input;

import com.music.sale.domain.like.enums.LikeableType;
import lombok.Builder;

@Builder
public record GetLikeStatusInput(Long userId, Long likeableId, LikeableType likeableType) {}
