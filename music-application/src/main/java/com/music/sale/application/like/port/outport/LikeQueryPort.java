package com.music.sale.application.like.port.outport;

import com.music.sale.domain.like.Like;
import com.music.sale.domain.like.enums.LikeableType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LikeQueryPort {
  boolean exists(Long userId, Long likeableId, LikeableType likeableType);

  Page<Like> findByUserIdAndType(Long userId, LikeableType likeableType, Pageable pageable);

  long countByLikeableIdAndType(Long likeableId, LikeableType likeableType);
}
