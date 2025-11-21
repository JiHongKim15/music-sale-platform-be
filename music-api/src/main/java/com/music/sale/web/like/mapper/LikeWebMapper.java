package com.music.sale.web.like.mapper;

import com.music.sale.application.like.dto.input.AddLikeInput;
import com.music.sale.application.like.dto.input.DeleteLikeInput;
import com.music.sale.application.like.dto.output.LikeOutput;
import com.music.sale.application.like.dto.output.LikeStatusOutput;
import com.music.sale.domain.like.LikeableType;
import com.music.sale.web.like.response.LikeResponse;
import com.music.sale.web.like.response.LikeStatusResponse;
import org.springframework.stereotype.Component;

@Component
public class LikeWebMapper {
    
    public AddLikeInput toAddLikeInput(Long userId, Long likeableId, LikeableType likeableType) {
        return AddLikeInput.builder()
                .userId(userId)
                .likeableId(likeableId)
                .likeableType(likeableType)
                .build();
    }
    
    public DeleteLikeInput toDeleteLikeInput(Long userId, Long likeableId, LikeableType likeableType) {
        return DeleteLikeInput.builder()
                .userId(userId)
                .likeableId(likeableId)
                .likeableType(likeableType)
                .build();
    }
    
    public LikeResponse toResponse(LikeOutput output) {
        return new LikeResponse(
                output.getId(),
                output.getUserId(),
                output.getLikeableId(),
                output.getLikeableType(),
                output.getCreatedAt()
        );
    }
    
    public LikeStatusResponse toStatusResponse(LikeStatusOutput output) {
        return new LikeStatusResponse(output.isLiked());
    }
}

