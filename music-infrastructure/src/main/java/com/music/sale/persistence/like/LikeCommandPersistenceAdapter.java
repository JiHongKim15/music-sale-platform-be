package com.music.sale.persistence.like;

import com.music.sale.application.like.port.outport.LikeCommandPort;
import com.music.sale.domain.like.Like;
import com.music.sale.domain.like.LikeableType;
import com.music.sale.persistence.like.entity.LikeEntity;
import com.music.sale.persistence.like.mapper.LikeEntityMapper;
import com.music.sale.persistence.like.repository.LikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
@RequiredArgsConstructor
public class LikeCommandPersistenceAdapter implements LikeCommandPort {
    private final LikeRepository likeRepository;
    private final LikeEntityMapper likeEntityMapper;

    @Override
    public Like save(Like like) {
        LikeEntity entity = convertToEntity(like);
        LikeEntity savedEntity = saveEntity(entity);
        return convertToDomain(savedEntity);
    }

    private LikeEntity convertToEntity(Like like) {
        return likeEntityMapper.toEntity(like);
    }

    private LikeEntity saveEntity(LikeEntity entity) {
        return likeRepository.save(entity);
    }

    private Like convertToDomain(LikeEntity entity) {
        return likeEntityMapper.toDomain(entity);
    }

    @Override
    public void delete(Long userId, Long likeableId, LikeableType likeableType) {
        likeRepository.deleteByUserIdAndLikeableIdAndLikeableType(userId, likeableId, likeableType);
    }

    @Override
    public boolean exists(Long userId, Long likeableId, LikeableType likeableType) {
        return likeRepository.existsByUserIdAndLikeableIdAndLikeableType(userId, likeableId, likeableType);
    }
}


