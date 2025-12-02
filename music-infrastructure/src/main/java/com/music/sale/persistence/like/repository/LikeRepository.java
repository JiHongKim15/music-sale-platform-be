// Copyright (C) 2024 Your Name or Company
package com.music.sale.persistence.like.repository;

import com.music.sale.domain.like.enums.LikeableType;
import com.music.sale.persistence.like.entity.LikeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeRepository extends JpaRepository<LikeEntity, Long> {
  boolean existsByUserIdAndLikeableIdAndLikeableType(
      Long userId, Long likeableId, LikeableType likeableType);

  void deleteByUserIdAndLikeableIdAndLikeableType(
      Long userId, Long likeableId, LikeableType likeableType);

  Page<LikeEntity> findByUserIdAndLikeableType(
      Long userId, LikeableType likeableType, Pageable pageable);

  long countByLikeableIdAndLikeableType(Long likeableId, LikeableType likeableType);
}
