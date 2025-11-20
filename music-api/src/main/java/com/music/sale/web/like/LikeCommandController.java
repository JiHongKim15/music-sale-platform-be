package com.music.sale.web.like;

import com.music.sale.application.like.dto.LikeOutput;
import com.music.sale.application.like.port.inport.LikeCommandUseCase;
import com.music.sale.common.ApiResponse;
import com.music.sale.domain.like.LikeableType;
import com.music.sale.web.like.command.AddLikeCommand;
import com.music.sale.web.like.command.DeleteLikeCommand;
import com.music.sale.web.like.mapper.LikeWebMapper;
import com.music.sale.web.like.response.LikeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/likes")
public class LikeCommandController {
    private final LikeCommandUseCase likeCommandUseCase;
    private final LikeWebMapper mapper;

    @PostMapping("/{targetId}")
    public ApiResponse<LikeResponse> likeTarget(
            @PathVariable Long targetId,
            @RequestParam("type") LikeableType likeableType
    ) {
        Long userId = 1L; // TODO: Session에서 가져오도록 수정 예정
        AddLikeCommand command = mapper.toAddCommand(userId, targetId, likeableType);
        LikeOutput output = likeCommandUseCase.addLike(command.getUserId(), command.getLikeableId(), command.getLikeableType());
        LikeResponse response = mapper.toResponse(output);
        return ApiResponse.success(response, "LIKE_CREATED");
    }

    @DeleteMapping("/{targetId}")
    public ApiResponse<Void> unlikeTarget(
            @PathVariable Long targetId,
            @RequestParam("type") LikeableType likeableType
    ) {
        Long userId = 1L; // TODO: Session에서 가져오도록 수정 예정
        DeleteLikeCommand command = mapper.toDeleteCommand(userId, targetId, likeableType);
        likeCommandUseCase.deleteLike(command.getUserId(), command.getLikeableId(), command.getLikeableType());
        return ApiResponse.success(null, "SUCCESS");
    }
}


