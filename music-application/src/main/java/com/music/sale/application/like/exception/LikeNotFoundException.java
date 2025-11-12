// Copyright (C) 2024 Your Name or Company
package com.music.sale.application.like.exception;

/**
 * 좋아요 기록을 찾을 수 없는 경우 발생하는 예외 (404 Not Found)
 */
public class LikeNotFoundException extends RuntimeException {
    public LikeNotFoundException(String message) {
        super(message);
    }

    public LikeNotFoundException() {
        super("좋아요 기록을 찾을 수 없습니다.");
    }
}

