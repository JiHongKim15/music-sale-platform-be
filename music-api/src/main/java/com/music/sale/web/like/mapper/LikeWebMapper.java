package com.music.sale.web.like.mapper;

import com.music.sale.application.like.dto.LikeOutput;
import com.music.sale.application.like.dto.LikeStatusOutput;
import com.music.sale.domain.like.LikeableType;
import com.music.sale.web.like.command.AddLikeCommand;
import com.music.sale.web.like.command.DeleteLikeCommand;
import com.music.sale.web.like.response.LikeResponse;
import com.music.sale.web.like.response.LikeStatusResponse;
import org.springframework.stereotype.Component;

@Component
public class LikeWebMapper {
    
    public AddLikeCommand toAddCommand(Long userId, Long likeableId, LikeableType likeableType) {
        return new AddLikeCommand(userId, likeableId, likeableType);
    }
    
    public DeleteLikeCommand toDeleteCommand(Long userId, Long likeableId, LikeableType likeableType) {
        return new DeleteLikeCommand(userId, likeableId, likeableType);
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

