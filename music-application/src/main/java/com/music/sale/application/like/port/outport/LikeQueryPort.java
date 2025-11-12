package com.music.sale.application.like.port.outport;

import com.music.sale.domain.like.Like;
import com.music.sale.domain.like.LikeableType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * 좋아요 Query Port (읽기 전용)
 * Infrastructure 레이어에서 구현
 */
public interface LikeQueryPort {
    /**
     * 좋아요 존재 여부 확인
     */
    boolean exists(Long userId, Long likeableId, LikeableType likeableType);

    /**
     * 사용자가 좋아요한 목록 조회 (페이징)
     */
    Page<Like> findByUserIdAndType(Long userId, LikeableType likeableType, Pageable pageable);

    /**
     * 특정 대상의 좋아요 개수 조회
     */
    long countByLikeableIdAndType(Long likeableId, LikeableType likeableType);
}


