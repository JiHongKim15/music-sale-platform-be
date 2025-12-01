package com.music.sale.application.like.exception;

import com.music.sale.common.ErrorDefinition;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum LikeErrorCode implements ErrorDefinition {
    LIKE_ALREADY_EXISTS("error.LIKE_ALREADY_EXISTS"),
    LIKE_NOT_FOUND("error.LIKE_NOT_FOUND"),
    TARGET_NOT_FOUND("error.TARGET_NOT_FOUND");

    private final String key;
}
