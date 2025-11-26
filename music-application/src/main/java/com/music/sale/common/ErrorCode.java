package com.music.sale.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    // TODO errorCode가 image만 관련된 것이 아니라면 내부 ErrorProductImage와 같이 enum 선언??

    // 공통
    PRODUCT_NOT_FOUND("제품을 찾을 수 없습니다.", 404), // Added
    // 이미지 관련
    IMAGE_NOT_FOUND("이미지를 찾을 수 없습니다.", 404),
    IMAGE_DELETE_FAILED("이미지 삭제에 실패했습니다.", 500),
    INVALID_IMAGE_ORDER("잘못된 이미지 순서입니다.", 400),
    FILE_SIZE_EXCEEDED("파일 크기가 최대 허용 크기를 초과했습니다.", 400);

    private final String message;
    private final int statusCode;
}
