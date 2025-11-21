package com.music.sale.application.like.port.inport;

import org.springframework.data.domain.Page;

import com.music.sale.application.like.dto.output.LikeOutput;
import com.music.sale.application.like.dto.output.LikeStatusOutput;
import com.music.sale.common.Pageable;
import com.music.sale.domain.like.LikeableType;

/**
 * 좋아요 Query UseCase (읽기 전용)
 * CQRS 패턴 적용: Command(쓰기)와 Query(읽기) 분리
 */
public interface LikeQueryUseCase {
    /**
     * 좋아요 상태 조회 (좋아요 여부)
     */
    LikeStatusOutput getLikeStatus(Long userId, Long likeableId, LikeableType likeableType);

    /**
     * 내가 좋아요한 목록 조회 (페이징)
     */
    Page<LikeOutput> getMyLikes(Long userId, LikeableType likeableType, Pageable pageable);
}
