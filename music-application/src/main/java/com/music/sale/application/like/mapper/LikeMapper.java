// Copyright (C) 2024 Your Name or Company
package com.music.sale.application.like.mapper;

import com.music.sale.application.like.dto.LikeOutput;
import com.music.sale.domain.like.Like;
import org.springframework.stereotype.Component;

/**
 * 좋아요 매퍼
 * Domain 모델 <-> Application DTO 변환
 */
@Component
public class LikeMapper {
    /**
     * Domain 모델을 Output DTO로 변환
     */
    public LikeOutput toOutput(Like like) {
        return new LikeOutput(
                like.getId(),
                like.getUserId(),
                like.getLikeableId(),
                like.getLikeableType(),
                like.getCreatedAt()
        );
    }

    /**
     * Output DTO를 Domain 모델로 변환
     */
    public Like toDomain(LikeOutput output) {
        return Like.of(
                output.getId(),
                output.getUserId(),
                output.getLikeableId(),
                output.getLikeableType(),
                output.getCreatedAt()
        );
    }
}

