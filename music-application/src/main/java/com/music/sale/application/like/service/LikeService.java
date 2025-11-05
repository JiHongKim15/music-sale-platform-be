// Copyright (C) 2024 Your Name or Company
package com.music.sale.application.like.service;

import com.music.sale.application.like.dto.LikeOutput;
import com.music.sale.application.like.dto.LikeStatusOutput;
import com.music.sale.application.like.exception.LikeAlreadyExistsException;
import com.music.sale.application.like.exception.LikeNotFoundException;
import com.music.sale.application.like.mapper.LikeMapper;
import com.music.sale.application.like.port.inport.LikeUseCase;
import com.music.sale.application.like.port.outport.LikePort;
import com.music.sale.common.Pageable;
import com.music.sale.domain.like.Like;
import com.music.sale.domain.like.LikeableType;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 좋아요 서비스
 * 비즈니스 로직을 처리하며, Port 인터페이스에만 의존합니다.
 */
@Service
@Transactional
public class LikeService implements LikeUseCase {
    private final LikePort likePort;
    private final LikeMapper likeMapper;

    public LikeService(LikePort likePort, LikeMapper likeMapper) {
        this.likePort = likePort;
        this.likeMapper = likeMapper;
    }

    @Override
    public LikeOutput addLike(Long userId, Long likeableId, LikeableType likeableType) {
        validateNotDuplicate(userId, likeableId, likeableType);
        Like savedLike = saveNewLike(userId, likeableId, likeableType);
        return likeMapper.toOutput(savedLike);
    }

    private void validateNotDuplicate(Long userId, Long likeableId, LikeableType likeableType) {
        if (isDuplicate(userId, likeableId, likeableType)) {
            throw createDuplicateException(likeableType);
        }
    }

    private boolean isDuplicate(Long userId, Long likeableId, LikeableType likeableType) {
        return likePort.exists(userId, likeableId, likeableType);
    }

    private LikeAlreadyExistsException createDuplicateException(LikeableType type) {
        return new LikeAlreadyExistsException("이미 " + getLikeableTypeKorean(type) + "한 대상입니다.");
    }

    private Like saveNewLike(Long userId, Long likeableId, LikeableType likeableType) {
        Like like = Like.create(userId, likeableId, likeableType);
        return likePort.save(like);
    }

    @Override
    public void deleteLike(Long userId, Long likeableId, LikeableType likeableType) {
        validateExists(userId, likeableId, likeableType);
        likePort.delete(userId, likeableId, likeableType);
    }

    private void validateExists(Long userId, Long likeableId, LikeableType likeableType) {
        if (notExists(userId, likeableId, likeableType)) {
            throw createNotFoundException(likeableType);
        }
    }

    private boolean notExists(Long userId, Long likeableId, LikeableType likeableType) {
        return !likePort.exists(userId, likeableId, likeableType);
    }

    private LikeNotFoundException createNotFoundException(LikeableType type) {
        return new LikeNotFoundException(getLikeableTypeKorean(type) + " 기록을 찾을 수 없습니다.");
    }

    /**
     * 좋아요 상태 조회
     */
    @Override
    @Transactional(readOnly = true)
    public LikeStatusOutput getLikeStatus(Long userId, Long likeableId, LikeableType likeableType) {
        boolean isLiked = likePort.exists(userId, likeableId, likeableType);
        return new LikeStatusOutput(isLiked);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Object> getMyLikes(Long userId, LikeableType likeableType, Pageable pageable) {
        Page<Like> likes = findLikes(userId, likeableType, pageable);
        return convertToOutputPage(likes);
    }

    private Page<Like> findLikes(Long userId, LikeableType likeableType, Pageable pageable) {
        return likePort.findByUserIdAndType(userId, likeableType, pageable);
    }

    private Page<Object> convertToOutputPage(Page<Like> likes) {
        return likes.map(this::convertToOutput);
    }

    private Object convertToOutput(Like like) {
        return likeMapper.toOutput(like);
    }

    private String getLikeableTypeKorean(LikeableType type) {
        if (isProduct(type)) return "찜";
        if (isStore(type)) return "구독";
        if (isSeller(type)) return "팔로우";
        return "좋아요";
    }

    private boolean isProduct(LikeableType type) {
        return type == LikeableType.PRODUCT;
    }

    private boolean isStore(LikeableType type) {
        return type == LikeableType.STORE;
    }

    private boolean isSeller(LikeableType type) {
        return type == LikeableType.SELLER;
    }
}

