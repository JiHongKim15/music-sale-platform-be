package com.music.sale.web.like;

import com.music.sale.application.like.dto.LikeOutput;
import com.music.sale.application.like.port.inport.LikeCommandUseCase;
import com.music.sale.common.ApiResponse;
import com.music.sale.domain.like.LikeableType;
import com.music.sale.web.like.mapper.LikeWebMapper;
import com.music.sale.web.like.response.LikeResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * 좋아요 Command Controller (쓰기 전용)
 * CQRS 패턴: Command(쓰기)와 Query(읽기) 분리
 * 상품 찜, 스토어 구독, 판매자 팔로우의 생성/삭제 API를 제공합니다.
 */
@RestController
@RequestMapping("/api/v1")
public class LikeCommandController {
    private final LikeCommandUseCase likeCommandUseCase;
    private final LikeWebMapper mapper;

    public LikeCommandController(LikeCommandUseCase likeCommandUseCase, LikeWebMapper mapper) {
        this.likeCommandUseCase = likeCommandUseCase;
        this.mapper = mapper;
    }

    // ============ 상품 찜 ============

    /**
     * 상품 찜하기
     * POST /api/v1/products/{productId}/likes
     */
    @PostMapping("/products/{productId}/likes")
    public ResponseEntity<ApiResponse<LikeResponse>> likeProduct(
            @PathVariable Long productId,
            @RequestHeader("X-User-Id") Long userId
    ) {
        LikeOutput output = addProductLike(userId, productId);
        return createCreatedResponse(output, "LIKE_CREATED");
    }

    private LikeOutput addProductLike(Long userId, Long productId) {
        return likeCommandUseCase.addLike(userId, productId, LikeableType.PRODUCT);
    }

    private ResponseEntity<ApiResponse<LikeResponse>> createCreatedResponse(LikeOutput output, String code) {
        return ResponseEntity.status(HttpStatus.CREATED).body(createSuccessResponse(output, code));
    }

    private ApiResponse<LikeResponse> createSuccessResponse(LikeOutput output, String code) {
        return ApiResponse.success(mapper.toResponse(output), code);
    }

    /**
     * 상품 찜 취소
     * DELETE /api/v1/products/{productId}/likes
     */
    @DeleteMapping("/products/{productId}/likes")
    public ResponseEntity<Void> unlikeProduct(
            @PathVariable Long productId,
            @RequestHeader("X-User-Id") Long userId
    ) {
        deleteProductLike(userId, productId);
        return ResponseEntity.noContent().build();
    }

    private void deleteProductLike(Long userId, Long productId) {
        likeCommandUseCase.deleteLike(userId, productId, LikeableType.PRODUCT);
    }

    // ============ 스토어 구독 ============

    /**
     * 스토어 구독
     * POST /api/v1/stores/{storeId}/likes
     */
    @PostMapping("/stores/{storeId}/likes")
    public ResponseEntity<ApiResponse<LikeResponse>> subscribeStore(
            @PathVariable Long storeId,
            @RequestHeader("X-User-Id") Long userId
    ) {
        LikeOutput output = addStoreLike(userId, storeId);
        return createCreatedResponse(output, "STORE_SUBSCRIBED");
    }

    private LikeOutput addStoreLike(Long userId, Long storeId) {
        return likeCommandUseCase.addLike(userId, storeId, LikeableType.STORE);
    }

    /**
     * 스토어 구독 취소
     * DELETE /api/v1/stores/{storeId}/likes
     */
    @DeleteMapping("/stores/{storeId}/likes")
    public ResponseEntity<Void> unsubscribeStore(
            @PathVariable Long storeId,
            @RequestHeader("X-User-Id") Long userId
    ) {
        deleteStoreLike(userId, storeId);
        return ResponseEntity.noContent().build();
    }

    private void deleteStoreLike(Long userId, Long storeId) {
        likeCommandUseCase.deleteLike(userId, storeId, LikeableType.STORE);
    }

    // ============ 판매자 팔로우 ============

    /**
     * 판매자 팔로우
     * POST /api/v1/sellers/{sellerId}/likes
     */
    @PostMapping("/sellers/{sellerId}/likes")
    public ResponseEntity<ApiResponse<LikeResponse>> followSeller(
            @PathVariable Long sellerId,
            @RequestHeader("X-User-Id") Long userId
    ) {
        LikeOutput output = addSellerLike(userId, sellerId);
        return createCreatedResponse(output, "SELLER_FOLLOWED");
    }

    private LikeOutput addSellerLike(Long userId, Long sellerId) {
        return likeCommandUseCase.addLike(userId, sellerId, LikeableType.SELLER);
    }

    /**
     * 판매자 팔로우 취소
     * DELETE /api/v1/sellers/{sellerId}/likes
     */
    @DeleteMapping("/sellers/{sellerId}/likes")
    public ResponseEntity<Void> unfollowSeller(
            @PathVariable Long sellerId,
            @RequestHeader("X-User-Id") Long userId
    ) {
        deleteSellerLike(userId, sellerId);
        return ResponseEntity.noContent().build();
    }

    private void deleteSellerLike(Long userId, Long sellerId) {
        likeCommandUseCase.deleteLike(userId, sellerId, LikeableType.SELLER);
    }
}


