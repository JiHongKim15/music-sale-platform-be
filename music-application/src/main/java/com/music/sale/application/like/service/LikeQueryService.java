package com.music.sale.application.like.service;

import com.music.sale.application.like.dto.output.GetLikeOutput;
import com.music.sale.application.like.dto.output.GetLikeStatusOutput;
import com.music.sale.application.like.mapper.LikeMapper;
import com.music.sale.application.like.port.inport.LikeQueryUseCase;
import com.music.sale.application.like.port.outport.LikeQueryPort;
import com.music.sale.common.Pageable;
import com.music.sale.domain.like.Like;
import com.music.sale.domain.like.enums.LikeableType;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class LikeQueryService implements LikeQueryUseCase {
    private final LikeQueryPort likeQueryPort;
    private final LikeMapper likeMapper;

    @Override
    public GetLikeStatusOutput getLikeStatus(Long userId, Long likeableId, LikeableType likeableType) {
        boolean isLiked = likeQueryPort.exists(userId, likeableId, likeableType);
        return new GetLikeStatusOutput(isLiked);
    }

    @Override
    public Page<GetLikeOutput> getMyLikes(Long userId, LikeableType likeableType, Pageable pageable) {
        // 1. Spring Pageable 객체 생성 (정렬 포함)
        Sort sort = Sort.by(Sort.Direction.DESC, "createdAt");
        org.springframework.data.domain.Pageable springPageable = PageRequest.of(
                pageable.getPageNumber(),
                pageable.getPageSize(),
                sort
        );

        // 2. 데이터 조회
        Page<Like> likes = likeQueryPort.findByUserIdAndType(userId, likeableType, springPageable);

        // 3. 결과 DTO 페이지로 변환하여 반환
        return likes.map(likeMapper::toGetOutput);
    }
}
