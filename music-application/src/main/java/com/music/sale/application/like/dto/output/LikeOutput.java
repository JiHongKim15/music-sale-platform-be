package com.music.sale.application.like.dto.output;

import com.music.sale.domain.like.LikeableType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 * 좋아요 출력 DTO
 */
@Getter
@AllArgsConstructor
@Builder
public class LikeOutput {
    private final Long id;
    private final Long userId;
    private final Long likeableId;
    private final LikeableType likeableType;
    private final LocalDateTime createdAt;
}

