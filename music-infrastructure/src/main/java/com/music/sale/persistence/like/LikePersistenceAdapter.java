// Copyright (C) 2024 Your Name or Company
package com.music.sale.persistence.like;

import com.music.sale.application.like.port.outport.LikePort;
import com.music.sale.common.Pageable;
import com.music.sale.domain.like.Like;
import com.music.sale.domain.like.LikeableType;
import com.music.sale.persistence.like.entity.LikeEntity;
import com.music.sale.persistence.like.repository.LikeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * 좋아요 Persistence Adapter
 * LikePort 인터페이스를 구현하여 Infrastructure 계층의 세부 사항을 캡슐화합니다.
 */
@Repository
@Transactional
public class LikePersistenceAdapter implements LikePort {
    private final LikeRepository likeRepository;

    public LikePersistenceAdapter(LikeRepository likeRepository) {
        this.likeRepository = likeRepository;
    }

    /**
     * 좋아요 저장
     */
    @Override
    public Like save(Like like) {
        LikeEntity entity = LikeEntity.fromDomain(like);
        LikeEntity savedEntity = likeRepository.save(entity);
        return savedEntity.toDomain();
    }

    /**
     * 좋아요 삭제
     */
    @Override
    public void delete(Long userId, Long likeableId, LikeableType likeableType) {
        likeRepository.deleteByUserIdAndLikeableIdAndLikeableType(userId, likeableId, likeableType);
    }

    /**
     * 좋아요 존재 여부 확인
     */
    @Override
    @Transactional(readOnly = true)
    public boolean exists(Long userId, Long likeableId, LikeableType likeableType) {
        return likeRepository.existsByUserIdAndLikeableIdAndLikeableType(userId, likeableId, likeableType);
    }

    @Override
    @Transactional(readOnly = true)
    public Like findByUserIdAndLikeableIdAndType(Long userId, Long likeableId, LikeableType likeableType) {
        LikeEntity entity = findEntity(userId, likeableId, likeableType);
        return convertToDomain(entity);
    }

    private LikeEntity findEntity(Long userId, Long likeableId, LikeableType likeableType) {
        return likeRepository.findByUserIdAndLikeableIdAndLikeableType(userId, likeableId, likeableType);
    }

    private Like convertToDomain(LikeEntity entity) {
        return entity != null ? entity.toDomain() : null;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Like> findByUserIdAndType(Long userId, LikeableType likeableType, Pageable pageable) {
        PageRequest pageRequest = createPageRequest(pageable);
        return findAndConvert(userId, likeableType, pageRequest);
    }

    private PageRequest createPageRequest(Pageable pageable) {
        return PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), createSort());
    }

    private Sort createSort() {
        return Sort.by(Sort.Direction.DESC, "createdAt");
    }

    private Page<Like> findAndConvert(Long userId, LikeableType likeableType, PageRequest pageRequest) {
        return findEntities(userId, likeableType, pageRequest).map(LikeEntity::toDomain);
    }

    private Page<LikeEntity> findEntities(Long userId, LikeableType likeableType, PageRequest pageRequest) {
        return likeRepository.findByUserIdAndLikeableTypeOrderByCreatedAtDesc(userId, likeableType, pageRequest);
    }
}

