package com.sodamjae.application.service

import com.sodamjae.application.port.`in`.UserUseCase
import com.sodamjae.application.port.out.UserPort
import com.sodamjae.domain.user.User
import com.sodamjae.domain.user.UserDomainService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserService(
    private val userPort: UserPort,
    private val userDomainService: UserDomainService
) : UserUseCase {

    @Transactional
    override fun createUser(user: User): User {
        // 도메인 서비스를 통한 검증
        userDomainService.validateUser(user)

        // 중복 이메일 체크 (애플리케이션 레벨의 규칙)
        if (userPort.findByEmail(user.email.value) != null) {
            throw IllegalStateException("이미 존재하는 이메일입니다")
        }

        // 저장 및 이메일 발송 등 외부 시스템과의 통신
        val savedUser = userPort.save(user)
        // sendWelcomeEmail(savedUser) // 이메일 발송 등 부가 기능

        return savedUser
    }

    override fun getUser(id: Long): User? {
        return userPort.findById(id)
    }
} 