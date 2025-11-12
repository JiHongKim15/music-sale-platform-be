// Copyright (C) 2024 Your Name or Company
package com.music.sale.web.like.mapper;

import com.music.sale.application.like.dto.LikeOutput;
import com.music.sale.application.like.dto.LikeStatusOutput;
import com.music.sale.web.like.response.LikeResponse;
import com.music.sale.web.like.response.LikeStatusResponse;
import org.springframework.stereotype.Component;

/**
 * 좋아요 Web Mapper
 * Application DTO <-> Web Response DTO 변환
 */
@Component
public class LikeWebMapper {
    /**
     * LikeOutput -> LikeResponse 변환
     */
    public LikeResponse toResponse(LikeOutput output) {
        return new LikeResponse(
                output.getId(),
                output.getUserId(),
                output.getLikeableId(),
                output.getLikeableType(),
                output.getCreatedAt()
        );
    }

    /**
     * LikeStatusOutput -> LikeStatusResponse 변환
     */
    public LikeStatusResponse toStatusResponse(LikeStatusOutput output) {
        return new LikeStatusResponse(output.isLiked());
    }
}

