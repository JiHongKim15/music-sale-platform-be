// Copyright (C) 2024 Your Name or Company
package com.music.sale.application.like.mapper;

import com.music.sale.application.like.dto.output.LikeOutput;
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
        return LikeOutput.builder()
                .id(like.getId())
                .userId(like.getUserId())
                .likeableId(like.getLikeableId())
                .likeableType(like.getLikeableType())
                .createdAt(like.getCreatedAt())
                .build();
    }
}

