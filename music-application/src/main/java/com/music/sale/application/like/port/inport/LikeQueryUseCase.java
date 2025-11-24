package com.music.sale.application.like.port.inport;

import com.music.sale.application.like.dto.output.GetLikeOutput;
import com.music.sale.application.like.dto.output.GetLikeStatusOutput;
import com.music.sale.common.Pageable;
import com.music.sale.domain.like.enums.LikeableType;
import org.springframework.data.domain.Page;

public interface LikeQueryUseCase {
    GetLikeStatusOutput getLikeStatus(Long userId, Long likeableId, LikeableType likeableType);

    Page<GetLikeOutput> getMyLikes(Long userId, LikeableType likeableType, Pageable pageable);
}
