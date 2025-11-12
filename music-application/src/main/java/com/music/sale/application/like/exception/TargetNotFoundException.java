// Copyright (C) 2024 Your Name or Company
package com.music.sale.application.like.exception;

/**
 * 좋아요 대상(상품, 스토어, 판매자)을 찾을 수 없는 경우 발생하는 예외 (404 Not Found)
 */
public class TargetNotFoundException extends RuntimeException {
    public TargetNotFoundException(String message) {
        super(message);
    }

    public TargetNotFoundException() {
        super("대상을 찾을 수 없습니다.");
    }
}

