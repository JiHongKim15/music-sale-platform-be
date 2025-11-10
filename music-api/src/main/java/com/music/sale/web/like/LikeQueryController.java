package com.music.sale.web.like;

import com.music.sale.application.like.dto.LikeStatusOutput;
import com.music.sale.application.like.port.inport.LikeQueryUseCase;
import com.music.sale.common.ApiResponse;
import com.music.sale.common.DefaultPageable;
import com.music.sale.common.Pageable;
import com.music.sale.domain.like.LikeableType;
import com.music.sale.web.like.mapper.LikeWebMapper;
import com.music.sale.web.like.response.LikeStatusResponse;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * 좋아요 Query Controller (읽기 전용)
 * CQRS 패턴: Command(쓰기)와 Query(읽기) 분리
 * 상품 찜, 스토어 구독, 판매자 팔로우의 조회 API를 제공합니다.
 */
@RestController
@RequestMapping("/api/v1")
public class LikeQueryController {
    private final LikeQueryUseCase likeQueryUseCase;
    private final LikeWebMapper mapper;

    public LikeQueryController(LikeQueryUseCase likeQueryUseCase, LikeWebMapper mapper) {
        this.likeQueryUseCase = likeQueryUseCase;
        this.mapper = mapper;
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
        return likeQueryUseCase.getLikeStatus(userId, productId, LikeableType.PRODUCT);
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
        Pageable pageable = createPageable(page, size);
        return likeQueryUseCase.getMyLikes(userId, LikeableType.PRODUCT, pageable);
    }

    private Pageable createPageable(int page, int size) {
        return new DefaultPageable(page, size, null, null);
    }

    private ResponseEntity<ApiResponse<Page<Object>>> createPageResponse(Page<Object> data) {
        return ResponseEntity.ok(ApiResponse.success(data, "SUCCESS"));
    }

    @GetMapping("/stores/{storeId}/likes/status")
    public ResponseEntity<ApiResponse<LikeStatusResponse>> getStoreLikeStatus(
            @PathVariable Long storeId,
            @RequestHeader("X-User-Id") Long userId
    ) {
        LikeStatusOutput output = getStoreStatus(userId, storeId);
        return createStatusResponse(output);
    }

    private LikeStatusOutput getStoreStatus(Long userId, Long storeId) {
        return likeQueryUseCase.getLikeStatus(userId, storeId, LikeableType.STORE);
    }

    @GetMapping("/users/me/likes/stores")
    public ResponseEntity<ApiResponse<Page<Object>>> getMySubscribedStores(
            @RequestHeader("X-User-Id") Long userId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size
    ) {
        Page<Object> likes = findMyStoreLikes(userId, page, size);
        return createPageResponse(likes);
    }

    private Page<Object> findMyStoreLikes(Long userId, int page, int size) {
        Pageable pageable = createPageable(page, size);
        return likeQueryUseCase.getMyLikes(userId, LikeableType.STORE, pageable);
    }

    @GetMapping("/sellers/{sellerId}/likes/status")
    public ResponseEntity<ApiResponse<LikeStatusResponse>> getSellerLikeStatus(
            @PathVariable Long sellerId,
            @RequestHeader("X-User-Id") Long userId
    ) {
        LikeStatusOutput output = getSellerStatus(userId, sellerId);
        return createStatusResponse(output);
    }

    private LikeStatusOutput getSellerStatus(Long userId, Long sellerId) {
        return likeQueryUseCase.getLikeStatus(userId, sellerId, LikeableType.SELLER);
    }

    @GetMapping("/users/me/likes/sellers")
    public ResponseEntity<ApiResponse<Page<Object>>> getMyFollowedSellers(
            @RequestHeader("X-User-Id") Long userId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size
    ) {
        Page<Object> likes = findMySellerLikes(userId, page, size);
        return createPageResponse(likes);
    }

    private Page<Object> findMySellerLikes(Long userId, int page, int size) {
        Pageable pageable = createPageable(page, size);
        return likeQueryUseCase.getMyLikes(userId, LikeableType.SELLER, pageable);
    }
}


