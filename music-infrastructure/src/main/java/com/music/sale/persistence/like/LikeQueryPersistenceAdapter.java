package com.music.sale.persistence.like;

import com.music.sale.application.like.port.outport.LikeQueryPort;
import com.music.sale.domain.like.Like;
import com.music.sale.domain.like.enums.LikeableType;
import com.music.sale.persistence.like.mapper.LikePersistenceMapper;
import com.music.sale.persistence.like.repository.LikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class LikeQueryPersistenceAdapter implements LikeQueryPort {
  private final LikeRepository likeRepository;
  private final LikePersistenceMapper likePersistenceMapper;

  @Override
  public boolean exists(Long userId, Long likeableId, LikeableType likeableType) {
    return likeRepository.existsByUserIdAndLikeableIdAndLikeableType(
        userId, likeableId, likeableType);
  }

  @Override
  public Page<Like> findByUserIdAndType(Long userId, LikeableType likeableType, Pageable pageable) {
    PageRequest pageRequest = createPageRequest(pageable);
    return likeRepository
        .findByUserIdAndLikeableType(userId, likeableType, pageRequest)
        .map(likePersistenceMapper::toDomain);
  }

  private PageRequest createPageRequest(Pageable pageable) {
    return PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), createSort());
  }

  private Sort createSort() {
    return Sort.by(Sort.Direction.DESC, "createdAt");
  }

  @Override
  public long countByLikeableIdAndType(Long likeableId, LikeableType likeableType) {
    return likeRepository.countByLikeableIdAndLikeableType(likeableId, likeableType);
  }
}
