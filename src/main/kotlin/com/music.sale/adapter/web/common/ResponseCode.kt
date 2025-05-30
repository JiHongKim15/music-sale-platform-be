package com.music.sale.adapter.web.common

enum class ResponseCode(
    val code: String,
    val message: String
) {
    // 공통 응답 코드
    SUCCESS("SUCCESS", "성공적으로 처리되었습니다."),
    INVALID_REQUEST("INVALID_REQUEST", "잘못된 요청입니다."),
    INTERNAL_SERVER_ERROR("INTERNAL_SERVER_ERROR", "서버 내부 오류가 발생했습니다."),
    
    // 상품 관련 응답 코드
    PRODUCT_CREATED("PRODUCT_CREATED", "상품이 성공적으로 등록되었습니다."),
    PRODUCT_UPDATED("PRODUCT_UPDATED", "상품이 성공적으로 수정되었습니다."),
    PRODUCT_DELETED("PRODUCT_DELETED", "상품이 성공적으로 삭제되었습니다."),
    PRODUCT_NOT_FOUND("PRODUCT_NOT_FOUND", "상품을 찾을 수 없습니다."),
    PRODUCT_ALREADY_EXISTS("PRODUCT_ALREADY_EXISTS", "이미 존재하는 상품입니다."),
    PRODUCT_INVALID_PRICE("PRODUCT_INVALID_PRICE", "유효하지 않은 가격입니다."),
    PRODUCT_INVALID_STOCK("PRODUCT_INVALID_STOCK", "유효하지 않은 재고 수량입니다."),

    // 사용자 관련 응답 코드
    USER_CREATED("USER_CREATED", "사용자가 성공적으로 생성되었습니다."),
    USER_NOT_FOUND("USER_NOT_FOUND", "사용자를 찾을 수 없습니다."),
    USER_ALREADY_EXISTS("USER_ALREADY_EXISTS", "이미 존재하는 사용자입니다."),
    USER_INVALID_EMAIL("USER_INVALID_EMAIL", "유효하지 않은 이메일 형식입니다."),
    USER_INVALID_PASSWORD("USER_INVALID_PASSWORD", "유효하지 않은 비밀번호 형식입니다."),
    USER_INVALID_PROVIDER("USER_INVALID_PROVIDER", "유효하지 않은 소셜 로그인 제공자입니다.")
} 