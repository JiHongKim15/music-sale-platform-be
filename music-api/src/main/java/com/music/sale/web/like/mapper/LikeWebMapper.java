package com.music.sale.web.like.mapper;

import com.music.sale.application.like.dto.input.CreateLikeInput;
import com.music.sale.application.like.dto.input.DeleteLikeInput;
import com.music.sale.application.like.dto.output.CreateLikeOutput;
import com.music.sale.application.like.dto.output.GetLikeOutput;
import com.music.sale.application.like.dto.output.GetLikeStatusOutput;
import com.music.sale.domain.like.enums.LikeableType;
import com.music.sale.web.like.response.CreateLikeResponse;
import com.music.sale.web.like.response.GetLikeResponse;
import com.music.sale.web.like.response.GetLikeStatusResponse;
import org.springframework.stereotype.Component;

@Component
public class LikeWebMapper {

    public CreateLikeInput toCreateLikeInput(Long userId, Long likeableId, LikeableType likeableType) {
        return CreateLikeInput.builder()
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

    public CreateLikeResponse toCreateLikeResponse(CreateLikeOutput output) {
        return new CreateLikeResponse(
                output.id(),
                output.userId(),
                output.likeableId(),
                output.likeableType(),
                output.createdAt()
        );
    }

    public GetLikeResponse toGetLikeResponse(GetLikeOutput output) {
        return new GetLikeResponse(
                output.id(),
                output.userId(),
                output.likeableId(),
                output.likeableType(),
                output.createdAt()
        );
    }

    public GetLikeStatusResponse toGetLikeStatusResponse(GetLikeStatusOutput output) {
        return new GetLikeStatusResponse(output.isLiked());
    }
}

