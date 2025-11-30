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
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class LikeCommandService implements LikeCommandUseCase {
    private final LikeCommandPort likeCommandPort;
    private final LikeMapper likeMapper;

    @Override
    public CreateLikeOutput createLike(CreateLikeInput input) {
        if (likeCommandPort.exists(input.userId(), input.likeableId(), input.likeableType())) {
            throw new LikeAlreadyExistsException("이미 " + input.likeableType().getKorean() + "한 대상입니다.");
        }

        Like like = Like.create(input.userId(), input.likeableId(), input.likeableType());
        Like savedLike = likeCommandPort.save(like);

        return likeMapper.toCreateOutput(savedLike);
    }

    @Override
    public void deleteLike(DeleteLikeInput input) {
        if (!likeCommandPort.exists(input.userId(), input.likeableId(), input.likeableType())) {
            throw new LikeNotFoundException(input.likeableType().getKorean() + " 기록을 찾을 수 없습니다.");
        }

        likeCommandPort.delete(input.userId(), input.likeableId(), input.likeableType());
    }
}
