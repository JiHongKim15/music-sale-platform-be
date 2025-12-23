package com.music.sale.web.like;

import com.music.sale.application.auth.security.AuthenticatedUser;
import com.music.sale.application.auth.security.CurrentUser;
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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Like", description = "좋아요 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/likes")
public class LikeQueryController {
  private final LikeQueryUseCase likeQueryUseCase;
  private final LikeWebMapper mapper;

  @Operation(summary = "좋아요 상태 조회", description = "특정 대상에 대한 좋아요 상태를 조회합니다.")
  @GetMapping("/status/{targetId}")
  public ApiResponse<GetLikeStatusResponse> getLikeStatus(
      @Parameter(hidden = true) @CurrentUser AuthenticatedUser user,
      @PathVariable("targetId") Long targetId,
      @RequestParam("type") LikeableType likeableType) {
    GetLikeStatusInput input = mapper.toGetLikeStatusInput(user.userId(), targetId, likeableType);
    GetLikeStatusOutput output = likeQueryUseCase.getLikeStatus(input);
    return ApiResponse.success(mapper.toGetLikeStatusResponse(output));
  }

  @Operation(summary = "내 좋아요 목록 조회", description = "현재 로그인한 사용자의 좋아요 목록을 조회합니다.")
  @GetMapping("/users/me")
  public ApiResponse<Page<GetLikeResponse>> getMyLikes(
      @Parameter(hidden = true) @CurrentUser AuthenticatedUser user,
      @RequestParam("type") LikeableType likeableType,
      @RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "20") int size) {
    GetMyLikesInput input = mapper.toGetMyLikesInput(user.userId(), likeableType, page, size);
    Page<GetLikeOutput> likeOutputs = likeQueryUseCase.getMyLikes(input);
    return ApiResponse.success(likeOutputs.map(mapper::toGetLikeResponse));
  }
}
