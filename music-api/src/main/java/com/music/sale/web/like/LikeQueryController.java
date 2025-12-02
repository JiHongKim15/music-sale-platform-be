package com.music.sale.web.like;

import com.music.sale.application.like.dto.input.GetLikeStatusInput;
import com.music.sale.application.like.dto.input.GetMyLikesInput;
import com.music.sale.application.like.dto.output.GetLikeOutput;
import com.music.sale.application.like.dto.output.GetLikeStatusOutput;
import com.music.sale.application.like.port.inport.LikeQueryUseCase;
import com.music.sale.common.ApiResponse;
import com.music.sale.domain.like.enums.LikeableType;
import com.music.sale.web.like.mapper.LikeWebMapper;
import com.music.sale.web.like.response.GetLikeResponse;
import com.music.sale.web.like.response.GetLikeStatusResponse;
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
  public ApiResponse<GetLikeStatusResponse> getLikeStatus(
      @PathVariable("targetId") Long targetId, @RequestParam("type") LikeableType likeableType) {
    Long userId = 1L; // TODO: Session에서 가져오도록 수정 예정

    GetLikeStatusInput input = mapper.toGetLikeStatusInput(userId, targetId, likeableType);
    GetLikeStatusOutput output = likeQueryUseCase.getLikeStatus(input);
    return ApiResponse.success(mapper.toGetLikeStatusResponse(output));
  }

  @GetMapping("/users/me")
  public ApiResponse<Page<GetLikeResponse>> getMyLikes(
      @RequestParam("type") LikeableType likeableType,
      @RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "20") int size) {
    Long userId = 1L; // TODO: Session에서 가져오도록 수정 예정

    GetMyLikesInput input = mapper.toGetMyLikesInput(userId, likeableType, page, size);
    Page<GetLikeOutput> likeOutputs = likeQueryUseCase.getMyLikes(input);
    return ApiResponse.success(likeOutputs.map(mapper::toGetLikeResponse));
  }
}
