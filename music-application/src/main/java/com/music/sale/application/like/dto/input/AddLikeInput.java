package com.music.sale.application.like.dto.input;

import com.music.sale.domain.like.LikeableType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

/**
 * 좋아요 추가 Input DTO
 */
@Getter
@AllArgsConstructor
@Builder
public class AddLikeInput {
    private final Long userId;
    private final Long likeableId;
    private final LikeableType likeableType;
}
