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
@RequestMapping("/api/v1")
public class LikeCommandController {
    private final LikeCommandUseCase likeCommandUseCase;
    private final LikeWebMapper mapper;

    @PostMapping("/products/{productId}/likes")
    public ApiResponse<LikeResponse> likeProduct(@PathVariable Long productId) {
        Long userId = 1L; // TODO: Session에서 가져오도록 수정 예정
        AddLikeCommand command = mapper.toAddCommand(userId, productId, LikeableType.PRODUCT);
        LikeOutput output = likeCommandUseCase.addLike(command.getUserId(), command.getLikeableId(), command.getLikeableType());
        LikeResponse response = mapper.toResponse(output);
        return ApiResponse.success(response, "LIKE_CREATED");
    }

    @DeleteMapping("/products/{productId}/likes")
    public ApiResponse<Void> unlikeProduct(@PathVariable Long productId) {
        Long userId = 1L; // TODO: Session에서 가져오도록 수정 예정
        DeleteLikeCommand command = mapper.toDeleteCommand(userId, productId, LikeableType.PRODUCT);
        likeCommandUseCase.deleteLike(command.getUserId(), command.getLikeableId(), command.getLikeableType());
        return ApiResponse.success(null, "SUCCESS");
    }

    @PostMapping("/stores/{storeId}/likes")
    public ApiResponse<LikeResponse> subscribeStore(@PathVariable Long storeId) {
        Long userId = 1L; // TODO: Session에서 가져오도록 수정 예정
        AddLikeCommand command = mapper.toAddCommand(userId, storeId, LikeableType.STORE);
        LikeOutput output = likeCommandUseCase.addLike(command.getUserId(), command.getLikeableId(), command.getLikeableType());
        LikeResponse response = mapper.toResponse(output);
        return ApiResponse.success(response, "STORE_SUBSCRIBED");
    }

    @DeleteMapping("/stores/{storeId}/likes")
    public ApiResponse<Void> unsubscribeStore(@PathVariable Long storeId) {
        Long userId = 1L; // TODO: Session에서 가져오도록 수정 예정
        DeleteLikeCommand command = mapper.toDeleteCommand(userId, storeId, LikeableType.STORE);
        likeCommandUseCase.deleteLike(command.getUserId(), command.getLikeableId(), command.getLikeableType());
        return ApiResponse.success(null, "SUCCESS");
    }

    @PostMapping("/sellers/{sellerId}/likes")
    public ApiResponse<LikeResponse> followSeller(@PathVariable Long sellerId) {
        Long userId = 1L; // TODO: Session에서 가져오도록 수정 예정
        AddLikeCommand command = mapper.toAddCommand(userId, sellerId, LikeableType.SELLER);
        LikeOutput output = likeCommandUseCase.addLike(command.getUserId(), command.getLikeableId(), command.getLikeableType());
        LikeResponse response = mapper.toResponse(output);
        return ApiResponse.success(response, "SELLER_FOLLOWED");
    }

    @DeleteMapping("/sellers/{sellerId}/likes")
    public ApiResponse<Void> unfollowSeller(@PathVariable Long sellerId) {
        Long userId = 1L; // TODO: Session에서 가져오도록 수정 예정
        DeleteLikeCommand command = mapper.toDeleteCommand(userId, sellerId, LikeableType.SELLER);
        likeCommandUseCase.deleteLike(command.getUserId(), command.getLikeableId(), command.getLikeableType());
        return ApiResponse.success(null, "SUCCESS");
    }
}


