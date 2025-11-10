package com.music.sale.application.like.service;

import com.music.sale.application.like.dto.LikeOutput;
import com.music.sale.application.like.exception.LikeAlreadyExistsException;
import com.music.sale.application.like.exception.LikeNotFoundException;
import com.music.sale.application.like.mapper.LikeMapper;
import com.music.sale.application.like.port.inport.LikeCommandUseCase;
import com.music.sale.application.like.port.outport.LikeCommandPort;
import com.music.sale.domain.like.Like;
import com.music.sale.domain.like.LikeableType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 좋아요 Command Service (쓰기 전용)
 * CQRS 패턴: Command(쓰기)와 Query(읽기) 분리
 */
@Service
@Transactional
public class LikeCommandService implements LikeCommandUseCase {
    private final LikeCommandPort likeCommandPort;
    private final LikeMapper likeMapper;

    public LikeCommandService(LikeCommandPort likeCommandPort, LikeMapper likeMapper) {
        this.likeCommandPort = likeCommandPort;
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
        return likeCommandPort.exists(userId, likeableId, likeableType);
    }

    private LikeAlreadyExistsException createDuplicateException(LikeableType type) {
        return new LikeAlreadyExistsException("이미 " + getLikeableTypeKorean(type) + "한 대상입니다.");
    }

    private Like saveNewLike(Long userId, Long likeableId, LikeableType likeableType) {
        Like like = Like.create(userId, likeableId, likeableType);
        return likeCommandPort.save(like);
    }

    @Override
    public void deleteLike(Long userId, Long likeableId, LikeableType likeableType) {
        validateExists(userId, likeableId, likeableType);
        likeCommandPort.delete(userId, likeableId, likeableType);
    }

    private void validateExists(Long userId, Long likeableId, LikeableType likeableType) {
        if (notExists(userId, likeableId, likeableType)) {
            throw createNotFoundException(likeableType);
        }
    }

    private boolean notExists(Long userId, Long likeableId, LikeableType likeableType) {
        return !likeCommandPort.exists(userId, likeableId, likeableType);
    }

    private LikeNotFoundException createNotFoundException(LikeableType type) {
        return new LikeNotFoundException(getLikeableTypeKorean(type) + " 기록을 찾을 수 없습니다.");
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


