package com.music.sale.application.like.dto.output;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 좋아요 상태 출력 DTO
 */
@Getter
@AllArgsConstructor
public class LikeStatusOutput {
    private final boolean isLiked;
}

