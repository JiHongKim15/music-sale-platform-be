package com.music.sale.application.like.service;

import com.music.sale.application.like.dto.input.GetLikeStatusInput;
import com.music.sale.application.like.dto.input.GetMyLikesInput;
import com.music.sale.application.like.dto.output.GetLikeOutput;
import com.music.sale.application.like.dto.output.GetLikeStatusOutput;
import com.music.sale.application.like.mapper.LikeMapper;
import com.music.sale.application.like.port.inport.LikeQueryUseCase;
import com.music.sale.application.like.port.outport.LikeQueryPort;
import com.music.sale.domain.like.Like;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
  public GetLikeStatusOutput getLikeStatus(GetLikeStatusInput input) {
    boolean isLiked =
        likeQueryPort.exists(input.userId(), input.likeableId(), input.likeableType());
    return new GetLikeStatusOutput(isLiked);
  }

  @Override
  public Page<GetLikeOutput> getMyLikes(GetMyLikesInput input) {
    Sort sort = Sort.by(Sort.Direction.DESC, "createdAt");
    Pageable springPageable =
        PageRequest.of(input.pageable().getPageNumber(), input.pageable().getPageSize(), sort);

    Page<Like> likes =
        likeQueryPort.findByUserIdAndType(input.userId(), input.likeableType(), springPageable);
    return likes.map(likeMapper::toGetOutput);
  }
}
