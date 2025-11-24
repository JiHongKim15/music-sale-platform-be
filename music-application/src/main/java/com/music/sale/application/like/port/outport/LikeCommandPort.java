package com.music.sale.application.like.port.outport;

import com.music.sale.domain.like.Like;
import com.music.sale.domain.like.enums.LikeableType;

public interface LikeCommandPort {
    Like save(Like like);

    void delete(Long userId, Long likeableId, LikeableType likeableType);

    boolean exists(Long userId, Long likeableId, LikeableType likeableType);
}



