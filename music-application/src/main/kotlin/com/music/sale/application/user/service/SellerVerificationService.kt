// Copyright (C) 2024 Your Name or Company
package com.music.sale.application.user.service

import com.music.sale.application.user.port.outport.UserPort
import com.music.sale.domain.user.User
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/** 판매자 인증 서비스 (임시 단순화 버전) */
@Service
@Transactional
open class SellerVerificationService(
        private val userPort: UserPort,
) {
    fun getUserById(userId: Long): User {
        return userPort.findById(userId)
                ?: throw IllegalArgumentException("사용자를 찾을 수 없습니다: $userId")
    }
}
