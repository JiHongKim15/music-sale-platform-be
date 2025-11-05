// Copyright (C) 2024 Your Name or Company
package com.music.sale.application.like.port.outport;

import com.music.sale.common.Pageable;
import com.music.sale.domain.like.Like;
import com.music.sale.domain.like.LikeableType;
import org.springframework.data.domain.Page;

/**
 * 좋아요 Port 인터페이스
 * Infrastructure 계층에서 구현됩니다.
 */
public interface LikePort {
    /**
     * 좋아요 저장
     */
    Like save(Like like);

    /**
     * 좋아요 삭제
     */
    void delete(Long userId, Long likeableId, LikeableType likeableType);

    /**
     * 좋아요 존재 여부 확인
     */
    boolean exists(Long userId, Long likeableId, LikeableType likeableType);

    /**
     * 좋아요 조회
     */
    Like findByUserIdAndLikeableIdAndType(Long userId, Long likeableId, LikeableType likeableType);

    /**
     * 사용자의 좋아요 목록 조회 (타입별)
     */
    Page<Like> findByUserIdAndType(Long userId, LikeableType likeableType, Pageable pageable);
}

