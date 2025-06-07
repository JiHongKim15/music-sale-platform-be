package com.music.sale.domain.order

class Payment private constructor(
    val method: PaymentMethod,
    var status: PaymentStatus = PaymentStatus.PENDING
) {
    enum class PaymentMethod {
        CREDIT_CARD, BANK_TRANSFER, KAKAO_PAY, NAVER_PAY
    }

    enum class PaymentStatus {
        PENDING, COMPLETED, FAILED, REFUNDED
    }

    companion object {
        fun create(method: PaymentMethod): Payment {
            return Payment(method = method)
        }
    }
} 