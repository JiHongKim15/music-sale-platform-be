package com.music.sale.persistence.like;

import com.music.sale.application.like.port.outport.LikeCommandPort;
import com.music.sale.domain.like.Like;
import com.music.sale.domain.like.enums.LikeableType;
import com.music.sale.persistence.like.entity.LikeEntity;
import com.music.sale.persistence.like.mapper.LikePersistenceMapper;
import com.music.sale.persistence.like.repository.LikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
@RequiredArgsConstructor
public class LikeCommandPersistenceAdapter implements LikeCommandPort {
  private final LikeRepository likeRepository;
  private final LikePersistenceMapper likePersistenceMapper;

  @Override
  public Like save(Like like) {
    LikeEntity entity = likePersistenceMapper.toEntity(like);
    return likePersistenceMapper.toDomain(saveEntity(entity));
  }

  private LikeEntity saveEntity(LikeEntity entity) {
    return likeRepository.save(entity);
  }

  @Override
  public void delete(Long userId, Long likeableId, LikeableType likeableType) {
    likeRepository.deleteByUserIdAndLikeableIdAndLikeableType(userId, likeableId, likeableType);
  }

  @Override
  public boolean exists(Long userId, Long likeableId, LikeableType likeableType) {
    return likeRepository.existsByUserIdAndLikeableIdAndLikeableType(
        userId, likeableId, likeableType);
  }
}
