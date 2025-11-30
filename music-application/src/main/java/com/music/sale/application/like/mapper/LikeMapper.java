// Copyright (C) 2024 Your Name or Company
package com.music.sale.application.like.mapper;

import com.music.sale.application.like.dto.output.CreateLikeOutput;
import com.music.sale.application.like.dto.output.GetLikeOutput;
import com.music.sale.domain.like.Like;
import org.springframework.stereotype.Component;

/**
 * 좋아요 매퍼
 * Domain 모델 <-> Application DTO 변환
 */
@Component
public class LikeMapper {
    /**
     * Domain 모델을 생성 결과 Output DTO로 변환
     */
    public CreateLikeOutput toCreateOutput(Like like) {
        return CreateLikeOutput.builder()
                .id(like.id())
                .userId(like.userId())
                .likeableId(like.likeableId())
                .likeableType(like.likeableType())
                .build();
    }

    /**
     * Domain 모델을 조회 결과 Output DTO로 변환
     */
    public GetLikeOutput toGetOutput(Like like) {
        return GetLikeOutput.builder()
                .id(like.id())
                .userId(like.userId())
                .likeableId(like.likeableId())
                .likeableType(like.likeableType())
                .build();
    }
}

