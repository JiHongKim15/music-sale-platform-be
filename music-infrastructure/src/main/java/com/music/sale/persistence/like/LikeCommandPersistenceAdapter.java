package com.music.sale.persistence.like;

import com.music.sale.application.like.port.outport.LikeCommandPort;
import com.music.sale.domain.like.Like;
import com.music.sale.domain.like.LikeableType;
import com.music.sale.persistence.like.entity.LikeEntity;
import com.music.sale.persistence.like.repository.LikeRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * 좋아요 Command Persistence Adapter (쓰기 전용)
 * CQRS 패턴: Command(쓰기)와 Query(읽기) 분리
 */
@Repository
@Transactional
public class LikeCommandPersistenceAdapter implements LikeCommandPort {
    private final LikeRepository likeRepository;

    public LikeCommandPersistenceAdapter(LikeRepository likeRepository) {
        this.likeRepository = likeRepository;
    }

    @Override
    public Like save(Like like) {
        LikeEntity entity = convertToEntity(like);
        LikeEntity savedEntity = saveEntity(entity);
        return convertToDomain(savedEntity);
    }

    private LikeEntity convertToEntity(Like like) {
        return LikeEntity.fromDomain(like);
    }

    private LikeEntity saveEntity(LikeEntity entity) {
        return likeRepository.save(entity);
    }

    private Like convertToDomain(LikeEntity entity) {
        return entity.toDomain();
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


