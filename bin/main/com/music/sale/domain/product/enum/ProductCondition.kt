// Copyright (C) 2024 Your Name or Company
package com.music.sale.domain.product.enum

/**
 * 제품 상태(컨디션) enum
 * 제품의 상태가 새 제품인지 중고품인지 나타냄
 */
enum class ProductCondition {
    NEW, // 새 제품
    USED, // 중고품
}

enum class ProductConditionGrade {
    S, // 최상급
    A, // 양호
    B, // 보통
    C, // 미흡
    D, // 불량
    F, // 폐기
    NONE, // 등급 없음 (새 제품 등급이 필요 없는 경우)
}
