// Copyright (C) 2024 Your Name or Company
package com.music.sale.common

enum class ResponseCode(
    val code: String,
    val message: String,
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
    USER_UPDATED("USER_UPDATED", "사용자 정보가 성공적으로 수정되었습니다."),
    USER_DELETED("USER_DELETED", "사용자가 성공적으로 삭제되었습니다."),
    USER_FOUND("USER_FOUND", "사용자 정보를 성공적으로 조회했습니다."),
    USER_NOT_FOUND("USER_NOT_FOUND", "사용자를 찾을 수 없습니다."),
    USER_ALREADY_EXISTS("USER_ALREADY_EXISTS", "이미 존재하는 사용자입니다."),
    USER_INVALID_EMAIL("USER_INVALID_EMAIL", "유효하지 않은 이메일 형식입니다."),
    USER_INVALID_PASSWORD("USER_INVALID_PASSWORD", "유효하지 않은 비밀번호 형식입니다."),
    USER_INVALID_PROVIDER("USER_INVALID_PROVIDER", "유효하지 않은 소셜 로그인 제공자입니다."),
    USER_STATS_FOUND("USER_STATS_FOUND", "사용자 통계 정보를 성공적으로 조회했습니다."),

    // 휴대폰 인증 관련 응답 코드
    PHONE_VERIFICATION_SENT("PHONE_VERIFICATION_SENT", "휴대폰 인증 코드가 발송되었습니다."),
    PHONE_VERIFICATION_COMPLETED("PHONE_VERIFICATION_COMPLETED", "휴대폰 인증이 완료되었습니다."),
    PHONE_VERIFICATION_FAILED("PHONE_VERIFICATION_FAILED", "휴대폰 인증에 실패했습니다."),
    PHONE_VERIFICATION_EXPIRED("PHONE_VERIFICATION_EXPIRED", "휴대폰 인증 코드가 만료되었습니다."),
    PHONE_VERIFICATION_INVALID("PHONE_VERIFICATION_INVALID", "유효하지 않은 휴대폰 인증 코드입니다."),

    // 소셜 계정 연동 관련 응답 코드
    SOCIAL_ACCOUNT_CONNECTED("SOCIAL_ACCOUNT_CONNECTED", "소셜 계정이 성공적으로 연동되었습니다."),
    SOCIAL_ACCOUNT_DISCONNECTED("SOCIAL_ACCOUNT_DISCONNECTED", "소셜 계정 연동이 해제되었습니다."),
    SOCIAL_ACCOUNT_ALREADY_CONNECTED("SOCIAL_ACCOUNT_ALREADY_CONNECTED", "이미 연동된 소셜 계정입니다."),
    SOCIAL_ACCOUNT_NOT_FOUND("SOCIAL_ACCOUNT_NOT_FOUND", "연동된 소셜 계정을 찾을 수 없습니다."),

    // 2단계 인증 관련 응답 코드
    TWO_FACTOR_SETUP_COMPLETED("TWO_FACTOR_SETUP_COMPLETED", "2단계 인증 설정이 완료되었습니다."),
    TWO_FACTOR_VERIFIED("TWO_FACTOR_VERIFIED", "2단계 인증이 성공적으로 확인되었습니다."),
    TWO_FACTOR_INVALID("TWO_FACTOR_INVALID", "유효하지 않은 2단계 인증 코드입니다."),
    TWO_FACTOR_DISABLED("TWO_FACTOR_DISABLED", "2단계 인증이 비활성화되었습니다."),

    // 관심 카테고리 관련 응답 코드
    INTERESTED_CATEGORIES_UPDATED("INTERESTED_CATEGORIES_UPDATED", "관심 카테고리가 성공적으로 업데이트되었습니다."),
    INTERESTED_CATEGORIES_FOUND("INTERESTED_CATEGORIES_FOUND", "관심 카테고리를 성공적으로 조회했습니다."),

    // 인증 관련 응답 코드
    LOGIN_SUCCESS("LOGIN_SUCCESS", "로그인이 성공적으로 완료되었습니다."),
    REGISTER_SUCCESS("REGISTER_SUCCESS", "회원가입이 성공적으로 완료되었습니다."),
    LOGIN_FAILED("LOGIN_FAILED", "로그인에 실패했습니다."),
    INVALID_TOKEN("INVALID_TOKEN", "유효하지 않은 토큰입니다."),
    TOKEN_EXPIRED("TOKEN_EXPIRED", "토큰이 만료되었습니다."),
    UNAUTHORIZED("UNAUTHORIZED", "인증이 필요합니다."),
    FORBIDDEN("FORBIDDEN", "접근 권한이 없습니다."),

    // 찜 목록 관련 응답 코드
    WISHLIST_ADDED("WISHLIST_ADDED", "찜 목록에 성공적으로 추가되었습니다."),
    WISHLIST_REMOVED("WISHLIST_REMOVED", "찜 목록에서 성공적으로 제거되었습니다."),
    WISHLIST_ALREADY_EXISTS("WISHLIST_ALREADY_EXISTS", "이미 찜 목록에 추가된 상품입니다."),
    WISHLIST_NOT_FOUND("WISHLIST_NOT_FOUND", "찜 목록을 찾을 수 없습니다."),

    // 장바구니 관련 응답 코드
    CART_ADDED("CART_ADDED", "장바구니에 성공적으로 추가되었습니다."),
    CART_UPDATED("CART_UPDATED", "장바구니가 성공적으로 업데이트되었습니다."),
    CART_REMOVED("CART_REMOVED", "장바구니에서 성공적으로 제거되었습니다."),
    CART_CLEARED("CART_CLEARED", "장바구니가 성공적으로 비워졌습니다."),
    CART_ALREADY_EXISTS("CART_ALREADY_EXISTS", "이미 장바구니에 추가된 상품입니다."),
    CART_NOT_FOUND("CART_NOT_FOUND", "장바구니를 찾을 수 없습니다."),
    CART_INVALID_QUANTITY("CART_INVALID_QUANTITY", "유효하지 않은 수량입니다."),
}
