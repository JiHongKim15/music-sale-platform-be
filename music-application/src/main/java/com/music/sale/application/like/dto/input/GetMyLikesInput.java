package com.music.sale.application.like.dto.input;

import com.music.sale.common.Pageable;
import com.music.sale.domain.like.enums.LikeableType;
import lombok.Builder;

@Builder
public record GetMyLikesInput(Long userId, LikeableType likeableType, Pageable pageable) {}
