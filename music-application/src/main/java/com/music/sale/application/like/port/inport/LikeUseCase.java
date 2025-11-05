// Copyright (C) 2024 Your Name or Company
package com.music.sale.application.like.port.inport;

import com.music.sale.application.like.dto.LikeOutput;
import com.music.sale.application.like.dto.LikeStatusOutput;
import com.music.sale.common.Pageable;
import com.music.sale.domain.like.LikeableType;
import org.springframework.data.domain.Page;

/**
 * 좋아요 기능 UseCase 인터페이스
 * 상품 찜, 스토어 구독, 판매자 팔로우 등을 통합 관리합니다.
 */
public interface LikeUseCase {
    /**
     * 좋아요 추가
     * @param userId 사용자 ID
     * @param likeableId 대상 ID (상품, 스토어, 판매자)
     * @param likeableType 대상 타입
     * @return 생성된 좋아요 정보
     * @throws com.music.sale.application.like.exception.LikeAlreadyExistsException 이미 좋아요한 경우 (409 Conflict)
     * @throws com.music.sale.application.like.exception.TargetNotFoundException 대상을 찾을 수 없는 경우 (404 Not Found)
     */
    LikeOutput addLike(Long userId, Long likeableId, LikeableType likeableType);

    /**
     * 좋아요 삭제
     * @param userId 사용자 ID
     * @param likeableId 대상 ID
     * @param likeableType 대상 타입
     * @throws com.music.sale.application.like.exception.LikeNotFoundException 좋아요 기록을 찾을 수 없는 경우 (404 Not Found)
     */
    void deleteLike(Long userId, Long likeableId, LikeableType likeableType);

    /**
     * 좋아요 상태 조회 (Boolean)
     * @param userId 사용자 ID
     * @param likeableId 대상 ID
     * @param likeableType 대상 타입
     * @return 좋아요 여부
     */
    LikeStatusOutput getLikeStatus(Long userId, Long likeableId, LikeableType likeableType);

    /**
     * 내 좋아요 목록 조회 (페이징)
     * @param userId 사용자 ID
     * @param likeableType 대상 타입 (PRODUCT, STORE, SELLER)
     * @param pageable 페이징 정보
     * @return 좋아요 목록 (대상의 요약 정보 포함)
     */
    Page<Object> getMyLikes(Long userId, LikeableType likeableType, Pageable pageable);
}

