// Copyright (C) 2024 Your Name or Company
package com.music.sale.persistence.like.repository;

import com.music.sale.domain.like.LikeableType;
import com.music.sale.persistence.like.entity.LikeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 좋아요 JPA Repository
 */
@Repository
public interface LikeRepository extends JpaRepository<LikeEntity, Long> {
    /**
     * 좋아요 존재 여부 확인
     */
    boolean existsByUserIdAndLikeableIdAndLikeableType(
            Long userId,
            Long likeableId,
            LikeableType likeableType
    );

    /**
     * 좋아요 조회
     */
    LikeEntity findByUserIdAndLikeableIdAndLikeableType(
            Long userId,
            Long likeableId,
            LikeableType likeableType
    );

    /**
     * 좋아요 삭제
     */
    void deleteByUserIdAndLikeableIdAndLikeableType(
            Long userId,
            Long likeableId,
            LikeableType likeableType
    );

    /**
     * 사용자의 좋아요 목록 조회 (타입별, 페이징)
     */
    Page<LikeEntity> findByUserIdAndLikeableTypeOrderByCreatedAtDesc(
            Long userId,
            LikeableType likeableType,
            Pageable pageable
    );

    /**
     * 특정 대상의 좋아요 개수 조회
     */
    long countByLikeableIdAndLikeableType(
            Long likeableId,
            LikeableType likeableType
    );
}

