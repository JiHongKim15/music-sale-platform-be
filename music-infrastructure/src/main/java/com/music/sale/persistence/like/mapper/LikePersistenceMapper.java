package com.music.sale.persistence.like.mapper;

import com.music.sale.domain.like.Like;
import com.music.sale.persistence.like.entity.LikeEntity;
import org.springframework.stereotype.Component;

@Component
public class LikePersistenceMapper {

  public Like toDomain(LikeEntity entity) {
    return Like.builder()
        .id(entity.getId())
        .userId(entity.getUserId())
        .likeableId(entity.getLikeableId())
        .likeableType(entity.getLikeableType())
        .build();
  }

  public LikeEntity toEntity(Like domain) {
    return LikeEntity.builder()
        .id(domain.id())
        .userId(domain.userId())
        .likeableId(domain.likeableId())
        .likeableType(domain.likeableType())
        .build();
  }
}
