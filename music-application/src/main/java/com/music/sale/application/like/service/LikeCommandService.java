package com.music.sale.application.like.service;

import com.music.sale.application.like.dto.input.CreateLikeInput;
import com.music.sale.application.like.dto.input.DeleteLikeInput;
import com.music.sale.application.like.dto.output.CreateLikeOutput;
import com.music.sale.application.like.exception.LikeAlreadyExistsException;
import com.music.sale.application.like.exception.LikeNotFoundException;
import com.music.sale.application.like.mapper.LikeMapper;
import com.music.sale.application.like.port.inport.LikeCommandUseCase;
import com.music.sale.application.like.port.outport.LikeCommandPort;
import com.music.sale.domain.like.Like;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public CreateLikeOutput createLike(CreateLikeInput input) {
        validateNotDuplicate(input);
        Like savedLike = saveNewLike(input);
        return likeMapper.toCreateOutput(savedLike);
    }

    private void validateNotDuplicate(CreateLikeInput input) {
        if (isDuplicate(input)) {
            throw createDuplicateException(input.likeableType());
        }
    }

    private boolean isDuplicate(CreateLikeInput input) {
        return likeCommandPort.exists(input.userId(), input.likeableId(), input.likeableType());
    }

    private LikeAlreadyExistsException createDuplicateException(com.music.sale.domain.like.enums.LikeableType type) {
        return new LikeAlreadyExistsException("이미 " + type.getKorean() + "한 대상입니다.");
    }

    private Like saveNewLike(CreateLikeInput input) {
        Like like = Like.create(input.userId(), input.likeableId(), input.likeableType());
        return likeCommandPort.save(like);
    }

    @Override
    public void deleteLike(DeleteLikeInput input) {
        validateExists(input);
        likeCommandPort.delete(input.userId(), input.likeableId(), input.likeableType());
    }

    private void validateExists(DeleteLikeInput input) {
        if (notExists(input)) {
            throw createNotFoundException(input.likeableType());
        }
    }

    private boolean notExists(DeleteLikeInput input) {
        return !likeCommandPort.exists(input.userId(), input.likeableId(), input.likeableType());
    }

    private LikeNotFoundException createNotFoundException(com.music.sale.domain.like.enums.LikeableType type) {
        return new LikeNotFoundException(type.getKorean() + " 기록을 찾을 수 없습니다.");
    }
}
