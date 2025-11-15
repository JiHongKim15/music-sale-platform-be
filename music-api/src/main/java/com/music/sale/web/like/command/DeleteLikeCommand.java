package com.music.sale.web.like.command;

import com.music.sale.domain.like.LikeableType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DeleteLikeCommand {
    private final Long userId;
    private final Long likeableId;
    private final LikeableType likeableType;
}

