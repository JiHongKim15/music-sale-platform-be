package com.music.sale.web.like;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.music.sale.application.like.dto.input.AddLikeInput;
import com.music.sale.application.like.dto.input.DeleteLikeInput;
import com.music.sale.application.like.dto.output.LikeOutput;
import com.music.sale.application.like.port.inport.LikeCommandUseCase;
import com.music.sale.common.ApiResponse;
import com.music.sale.domain.like.LikeableType;
import com.music.sale.web.like.mapper.LikeWebMapper;
import com.music.sale.web.like.response.LikeResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/likes")
public class LikeCommandController {
    private final LikeCommandUseCase likeCommandUseCase;
    private final LikeWebMapper mapper;

    @PostMapping("/{targetId}")
    public ApiResponse<LikeResponse> likeTarget(
            @PathVariable Long targetId,
            @RequestParam("type") LikeableType likeableType) {
        Long userId = 1L; // TODO: Session에서 가져오도록 수정 예정
        AddLikeInput input = mapper.toAddLikeInput(userId, targetId, likeableType);
        LikeOutput output = likeCommandUseCase.addLike(input);
        LikeResponse response = mapper.toResponse(output);
        return ApiResponse.success(response, "LIKE_CREATED");
    }

    @DeleteMapping("/{targetId}")
    public ApiResponse<Void> unlikeTarget(
            @PathVariable Long targetId,
            @RequestParam("type") LikeableType likeableType) {
        Long userId = 1L; // TODO: Session에서 가져오도록 수정 예정
        DeleteLikeInput input = mapper.toDeleteLikeInput(userId, targetId, likeableType);
        likeCommandUseCase.deleteLike(input);
        return ApiResponse.success(null, "SUCCESS");
    }
}
