package com.music.sale.application.like.port.inport;

import com.music.sale.application.like.dto.input.AddLikeInput;
import com.music.sale.application.like.dto.input.DeleteLikeInput;
import com.music.sale.application.like.dto.output.LikeOutput;

/**
 * 좋아요 Command UseCase (쓰기 전용)
 * CQRS 패턴 적용: Command(쓰기)와 Query(읽기) 분리
 */
public interface LikeCommandUseCase {
    /**
     * 좋아요 추가
     */
    LikeOutput addLike(AddLikeInput input);

    /**
     * 좋아요 취소
     */
    void deleteLike(DeleteLikeInput input);
}



