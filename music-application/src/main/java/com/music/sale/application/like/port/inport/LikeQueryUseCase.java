package com.music.sale.application.like.port.inport;

import com.music.sale.application.like.dto.input.GetLikeStatusInput;
import com.music.sale.application.like.dto.input.GetMyLikesInput;
import com.music.sale.application.like.dto.output.GetLikeOutput;
import com.music.sale.application.like.dto.output.GetLikeStatusOutput;
import org.springframework.data.domain.Page;

public interface LikeQueryUseCase {
  GetLikeStatusOutput getLikeStatus(GetLikeStatusInput input);

  Page<GetLikeOutput> getMyLikes(GetMyLikesInput input);
}
