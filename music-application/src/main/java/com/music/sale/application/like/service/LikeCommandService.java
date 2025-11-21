package com.music.sale.application.like.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.music.sale.application.like.dto.input.AddLikeInput;
import com.music.sale.application.like.dto.input.DeleteLikeInput;
import com.music.sale.application.like.dto.output.LikeOutput;
import com.music.sale.application.like.exception.LikeAlreadyExistsException;
import com.music.sale.application.like.exception.LikeNotFoundException;
import com.music.sale.application.like.mapper.LikeMapper;
import com.music.sale.application.like.port.inport.LikeCommandUseCase;
import com.music.sale.application.like.port.outport.LikeCommandPort;
import com.music.sale.domain.like.Like;

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
    public LikeOutput addLike(AddLikeInput input) {
        validateNotDuplicate(input);
        Like savedLike = saveNewLike(input);
        return likeMapper.toOutput(savedLike);
    }

    private void validateNotDuplicate(AddLikeInput input) {
        if (isDuplicate(input)) {
            throw createDuplicateException(input.getLikeableType());
        }
    }

    private boolean isDuplicate(AddLikeInput input) {
        return likeCommandPort.exists(input.getUserId(), input.getLikeableId(), input.getLikeableType());
    }

    private LikeAlreadyExistsException createDuplicateException(com.music.sale.domain.like.LikeableType type) {
        return new LikeAlreadyExistsException("이미 " + type.getKorean() + "한 대상입니다.");
    }

    private Like saveNewLike(AddLikeInput input) {
        Like like = Like.create(input.getUserId(), input.getLikeableId(), input.getLikeableType());
        return likeCommandPort.save(like);
    }

    @Override
    public void deleteLike(DeleteLikeInput input) {
        validateExists(input);
        likeCommandPort.delete(input.getUserId(), input.getLikeableId(), input.getLikeableType());
    }

    private void validateExists(DeleteLikeInput input) {
        if (notExists(input)) {
            throw createNotFoundException(input.getLikeableType());
        }
    }

    private boolean notExists(DeleteLikeInput input) {
        return !likeCommandPort.exists(input.getUserId(), input.getLikeableId(), input.getLikeableType());
    }

    private LikeNotFoundException createNotFoundException(com.music.sale.domain.like.LikeableType type) {
        return new LikeNotFoundException(type.getKorean() + " 기록을 찾을 수 없습니다.");
    }
}
