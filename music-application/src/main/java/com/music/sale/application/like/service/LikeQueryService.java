package com.music.sale.application.like.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.music.sale.application.like.dto.output.LikeOutput;
import com.music.sale.application.like.dto.output.LikeStatusOutput;
import com.music.sale.application.like.mapper.LikeMapper;
import com.music.sale.application.like.port.inport.LikeQueryUseCase;
import com.music.sale.application.like.port.outport.LikeQueryPort;
import com.music.sale.common.Pageable;
import com.music.sale.domain.like.Like;
import com.music.sale.domain.like.LikeableType;

@Service
@Transactional(readOnly = true)
public class LikeQueryService implements LikeQueryUseCase {
    private final LikeQueryPort likeQueryPort;
    private final LikeMapper likeMapper;

    public LikeQueryService(LikeQueryPort likeQueryPort, LikeMapper likeMapper) {
        this.likeQueryPort = likeQueryPort;
        this.likeMapper = likeMapper;
    }

    @Override
    public LikeStatusOutput getLikeStatus(Long userId, Long likeableId, LikeableType likeableType) {
        boolean isLiked = checkIsLiked(userId, likeableId, likeableType);
        return new LikeStatusOutput(isLiked);
    }

    private boolean checkIsLiked(Long userId, Long likeableId, LikeableType likeableType) {
        return likeQueryPort.exists(userId, likeableId, likeableType);
    }

    @Override
    public Page<LikeOutput> getMyLikes(Long userId, LikeableType likeableType, Pageable pageable) {
        Page<Like> likes = findLikes(userId, likeableType, pageable);
        return convertToOutputPage(likes);
    }

    private Page<Like> findLikes(Long userId, LikeableType likeableType, Pageable pageable) {
        org.springframework.data.domain.Pageable springPageable = convertToSpringPageable(pageable);
        return likeQueryPort.findByUserIdAndType(userId, likeableType, springPageable);
    }

    private org.springframework.data.domain.Pageable convertToSpringPageable(Pageable pageable) {
        Sort sort = createSort();
        return PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);
    }

    private Sort createSort() {
        return Sort.by(Sort.Direction.DESC, "createdAt");
    }

    private Page<LikeOutput> convertToOutputPage(Page<Like> likes) {
        return likes.map(this::convertToOutput);
    }

    private LikeOutput convertToOutput(Like like) {
        return likeMapper.toOutput(like);
    }
}
