package com.music.sale.application.like.port.outport;

import com.music.sale.domain.like.Like;
import com.music.sale.domain.like.LikeableType;

/**
 * 좋아요 Command Port (쓰기 전용)
 * Infrastructure 레이어에서 구현
 */
public interface LikeCommandPort {
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
}


