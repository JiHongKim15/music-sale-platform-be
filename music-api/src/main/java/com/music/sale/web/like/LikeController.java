// Copyright (C) 2024 Your Name or Company
package com.music.sale.web.like;

import com.music.sale.application.like.dto.LikeOutput;
import com.music.sale.application.like.dto.LikeStatusOutput;
import com.music.sale.application.like.port.inport.LikeUseCase;
import com.music.sale.common.ApiResponse;
import com.music.sale.domain.like.LikeableType;
import com.music.sale.web.like.mapper.LikeWebMapper;
import com.music.sale.web.like.response.LikeResponse;
import com.music.sale.web.like.response.LikeStatusResponse;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * 좋아요 Controller
 * 상품 찜, 스토어 구독, 판매자 팔로우 API를 제공합니다.
 */
@RestController
@RequestMapping("/api/v1")
public class LikeController {
    private final LikeUseCase likeUseCase;
    private final LikeWebMapper mapper;

    public LikeController(LikeUseCase likeUseCase, LikeWebMapper mapper) {
        this.likeUseCase = likeUseCase;
        this.mapper = mapper;
    }

    @PostMapping("/products/{productId}/likes")
    public ResponseEntity<ApiResponse<LikeResponse>> likeProduct(
            @PathVariable Long productId,
            @RequestHeader("X-User-Id") Long userId
    ) {
        LikeOutput output = addProductLike(userId, productId);
        return createCreatedResponse(output);
    }

    private LikeOutput addProductLike(Long userId, Long productId) {
        return likeUseCase.addLike(userId, productId, LikeableType.PRODUCT);
    }

    private ResponseEntity<ApiResponse<LikeResponse>> createCreatedResponse(LikeOutput output) {
        return ResponseEntity.status(HttpStatus.CREATED).body(createSuccessResponse(output, "LIKE_CREATED"));
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
            @RequestHeader("X-User-Id") Long userId // TODO: JWT에서 추출
    ) {
        likeUseCase.deleteLike(userId, productId, LikeableType.PRODUCT);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/products/{productId}/likes/status")
    public ResponseEntity<ApiResponse<LikeStatusResponse>> getProductLikeStatus(
            @PathVariable Long productId,
            @RequestHeader("X-User-Id") Long userId
    ) {
        LikeStatusOutput output = getProductStatus(userId, productId);
        return createStatusResponse(output);
    }

    private LikeStatusOutput getProductStatus(Long userId, Long productId) {
        return likeUseCase.getLikeStatus(userId, productId, LikeableType.PRODUCT);
    }

    private ResponseEntity<ApiResponse<LikeStatusResponse>> createStatusResponse(LikeStatusOutput output) {
        return ResponseEntity.ok(ApiResponse.success(mapper.toStatusResponse(output), "SUCCESS"));
    }

    @GetMapping("/users/me/likes/products")
    public ResponseEntity<ApiResponse<Page<Object>>> getMyLikedProducts(
            @RequestHeader("X-User-Id") Long userId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size
    ) {
        Page<Object> likes = findMyProductLikes(userId, page, size);
        return createPageResponse(likes);
    }

    private Page<Object> findMyProductLikes(Long userId, int page, int size) {
        com.music.sale.common.Pageable pageable = createPageable(page, size);
        return likeUseCase.getMyLikes(userId, LikeableType.PRODUCT, pageable);
    }

    private com.music.sale.common.Pageable createPageable(int page, int size) {
        return new com.music.sale.common.DefaultPageable(page, size, null, null);
    }

    private ResponseEntity<ApiResponse<Page<Object>>> createPageResponse(Page<Object> data) {
        return ResponseEntity.ok(ApiResponse.success(data, "SUCCESS"));
    }

    /**
     * 스토어 구독
     * POST /api/v1/stores/{storeId}/likes
     */
    @PostMapping("/stores/{storeId}/likes")
    public ResponseEntity<ApiResponse<LikeResponse>> subscribeStore(
            @PathVariable Long storeId,
            @RequestHeader("X-User-Id") Long userId // TODO: JWT에서 추출
    ) {
        var output = likeUseCase.addLike(userId, storeId, LikeableType.STORE);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.success(
                        mapper.toResponse(output),
                        "STORE_SUBSCRIBED"
                ));
    }

    /**
     * 스토어 구독 취소
     * DELETE /api/v1/stores/{storeId}/likes
     */
    @DeleteMapping("/stores/{storeId}/likes")
    public ResponseEntity<Void> unsubscribeStore(
            @PathVariable Long storeId,
            @RequestHeader("X-User-Id") Long userId // TODO: JWT에서 추출
    ) {
        likeUseCase.deleteLike(userId, storeId, LikeableType.STORE);
        return ResponseEntity.noContent().build();
    }

    /**
     * 스토어 구독 상태 조회
     * GET /api/v1/stores/{storeId}/likes/status
     */
    @GetMapping("/stores/{storeId}/likes/status")
    public ResponseEntity<ApiResponse<LikeStatusResponse>> getStoreLikeStatus(
            @PathVariable Long storeId,
            @RequestHeader("X-User-Id") Long userId // TODO: JWT에서 추출
    ) {
        var output = likeUseCase.getLikeStatus(userId, storeId, LikeableType.STORE);
        return ResponseEntity.ok(ApiResponse.success(
                mapper.toStatusResponse(output),
                "SUCCESS"
        ));
    }

    /**
     * 내 구독 스토어 목록 조회
     * GET /api/v1/users/me/likes/stores
     */
    @GetMapping("/users/me/likes/stores")
    public ResponseEntity<ApiResponse<Page<Object>>> getMySubscribedStores(
            @RequestHeader("X-User-Id") Long userId, // TODO: JWT에서 추출
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size
    ) {
        com.music.sale.common.Pageable pageable = new com.music.sale.common.DefaultPageable(page, size, null, null);
        Page<Object> likes = likeUseCase.getMyLikes(userId, LikeableType.STORE, pageable);
        return ResponseEntity.ok(ApiResponse.success(likes, "SUCCESS"));
    }

    /**
     * 판매자 팔로우
     * POST /api/v1/sellers/{sellerId}/likes
     */
    @PostMapping("/sellers/{sellerId}/likes")
    public ResponseEntity<ApiResponse<LikeResponse>> followSeller(
            @PathVariable Long sellerId,
            @RequestHeader("X-User-Id") Long userId // TODO: JWT에서 추출
    ) {
        var output = likeUseCase.addLike(userId, sellerId, LikeableType.SELLER);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.success(
                        mapper.toResponse(output),
                        "SELLER_FOLLOWED"
                ));
    }

    /**
     * 판매자 팔로우 취소
     * DELETE /api/v1/sellers/{sellerId}/likes
     */
    @DeleteMapping("/sellers/{sellerId}/likes")
    public ResponseEntity<Void> unfollowSeller(
            @PathVariable Long sellerId,
            @RequestHeader("X-User-Id") Long userId // TODO: JWT에서 추출
    ) {
        likeUseCase.deleteLike(userId, sellerId, LikeableType.SELLER);
        return ResponseEntity.noContent().build();
    }

    /**
     * 판매자 팔로우 상태 조회
     * GET /api/v1/sellers/{sellerId}/likes/status
     */
    @GetMapping("/sellers/{sellerId}/likes/status")
    public ResponseEntity<ApiResponse<LikeStatusResponse>> getSellerLikeStatus(
            @PathVariable Long sellerId,
            @RequestHeader("X-User-Id") Long userId // TODO: JWT에서 추출
    ) {
        var output = likeUseCase.getLikeStatus(userId, sellerId, LikeableType.SELLER);
        return ResponseEntity.ok(ApiResponse.success(
                mapper.toStatusResponse(output),
                "SUCCESS"
        ));
    }

    /**
     * 내 팔로우 판매자 목록 조회
     * GET /api/v1/users/me/likes/sellers
     */
    @GetMapping("/users/me/likes/sellers")
    public ResponseEntity<ApiResponse<Page<Object>>> getMyFollowedSellers(
            @RequestHeader("X-User-Id") Long userId, // TODO: JWT에서 추출
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size
    ) {
        com.music.sale.common.Pageable pageable = new com.music.sale.common.DefaultPageable(page, size, null, null);
        Page<Object> likes = likeUseCase.getMyLikes(userId, LikeableType.SELLER, pageable);
        return ResponseEntity.ok(ApiResponse.success(likes, "SUCCESS"));
    }
}

