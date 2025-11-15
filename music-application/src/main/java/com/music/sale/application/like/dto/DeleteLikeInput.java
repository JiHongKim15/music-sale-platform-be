package com.music.sale.application.like.dto;

import com.music.sale.domain.like.LikeableType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

/**
 * 좋아요 삭제 Input DTO
 * CQRS 패턴: Command 전용 Input
 * Lombok 적용: @Getter, @AllArgsConstructor, @Builder
 */
@Getter
@AllArgsConstructor
@Builder
public class DeleteLikeInput {
    private final Long userId;
    private final Long likeableId;
    private final LikeableType likeableType;
}

