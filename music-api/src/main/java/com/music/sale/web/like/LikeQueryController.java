package com.music.sale.web.like;

import com.music.sale.application.like.dto.LikeOutput;
import com.music.sale.application.like.dto.LikeStatusOutput;
import com.music.sale.application.like.port.inport.LikeQueryUseCase;
import com.music.sale.common.ApiResponse;
import com.music.sale.common.DefaultPageable;
import com.music.sale.common.Pageable;
import com.music.sale.domain.like.LikeableType;
import com.music.sale.web.like.mapper.LikeWebMapper;
import com.music.sale.web.like.response.LikeResponse;
import com.music.sale.web.like.response.LikeStatusResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/likes")
public class LikeQueryController {
    private final LikeQueryUseCase likeQueryUseCase;
    private final LikeWebMapper mapper;

    @GetMapping("/status/{targetId}")
    public ApiResponse<LikeStatusResponse> getLikeStatus(
            @PathVariable("targetId") Long targetId,
            @RequestParam("type") LikeableType likeableType
    ) {
        Long userId = 1L; // TODO: Session에서 가져오도록 수정 예정
        LikeStatusOutput output = likeQueryUseCase.getLikeStatus(userId, targetId, likeableType);
        return ApiResponse.success(mapper.toStatusResponse(output), "SUCCESS");
    }

    @GetMapping("/users/me")
    public ApiResponse<Page<LikeResponse>> getMyLikes(
            @RequestParam("type") LikeableType likeableType,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size
    ) {
        Long userId = 1L; // TODO: Session에서 가져오도록 수정 예정
        Pageable pageable = new DefaultPageable(page, size, null, null);
        Page<LikeOutput> likes = likeQueryUseCase.getMyLikes(userId, likeableType, pageable);
        Page<LikeResponse> responsePage = likes.map(mapper::toResponse);
        return ApiResponse.success(responsePage, "SUCCESS");
    }
}


