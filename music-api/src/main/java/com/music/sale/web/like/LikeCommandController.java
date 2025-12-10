package com.music.sale.web.like;

import com.music.sale.application.auth.security.AuthenticatedUser;
import com.music.sale.application.auth.security.CurrentUser;
import com.music.sale.application.like.dto.input.CreateLikeInput;
import com.music.sale.application.like.dto.input.DeleteLikeInput;
import com.music.sale.application.like.port.inport.LikeCommandUseCase;
import com.music.sale.common.ApiResponse;
import com.music.sale.domain.like.enums.LikeableType;
import com.music.sale.web.like.mapper.LikeWebMapper;
import com.music.sale.web.like.response.CreateLikeResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Like", description = "좋아요 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/likes")
public class LikeCommandController {
  private final LikeCommandUseCase likeCommandUseCase;
  private final LikeWebMapper mapper;

  @Operation(summary = "좋아요 추가", description = "특정 대상에 좋아요를 추가합니다.")
  @PostMapping("/{targetId}")
  public ApiResponse<CreateLikeResponse> likeTarget(
      @Parameter(hidden = true) @CurrentUser AuthenticatedUser user,
      @PathVariable Long targetId,
      @RequestParam("type") LikeableType likeableType) {
    CreateLikeInput input = mapper.toCreateLikeInput(user.userId(), targetId, likeableType);
    CreateLikeResponse response = mapper.toCreateLikeResponse(likeCommandUseCase.createLike(input));
    return ApiResponse.success(response);
  }

  @Operation(summary = "좋아요 취소", description = "특정 대상의 좋아요를 취소합니다.")
  @DeleteMapping("/{targetId}")
  public ApiResponse<Void> unlikeTarget(
      @Parameter(hidden = true) @CurrentUser AuthenticatedUser user,
      @PathVariable Long targetId,
      @RequestParam("type") LikeableType likeableType) {
    DeleteLikeInput input = mapper.toDeleteLikeInput(user.userId(), targetId, likeableType);
    likeCommandUseCase.deleteLike(input);
    return ApiResponse.success();
  }
}
