// Copyright (C) 2024 Your Name or Company
package com.music.sale.application.like.exception;

/**
 * 이미 좋아요한 경우 발생하는 예외 (409 Conflict)
 */
public class LikeAlreadyExistsException extends RuntimeException {
    public LikeAlreadyExistsException(String message) {
        super(message);
    }

    public LikeAlreadyExistsException() {
        super("이미 좋아요한 대상입니다.");
    }
}

