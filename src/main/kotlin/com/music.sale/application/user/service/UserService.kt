package com.music.sale.application.user.service

import com.music.sale.application.user.port.`in`.CreateUserByEmailInput
import com.music.sale.application.user.port.`in`.CreateUserByProviderInput
import com.music.sale.application.user.port.`in`.UserUseCase
import com.music.sale.application.user.port.out.UserPort
import com.music.sale.domain.user.User
import com.music.sale.domain.user.UserDomainService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserService(
    private val userPort: UserPort,
    private val userDomainService: UserDomainService
) : UserUseCase {

    @Transactional
    override fun createUserByEmail(input: CreateUserByEmailInput): User {
        userDomainService.validateUserByEmail(input.toDomain())

        if (userPort.findByEmail(input.email) != null) {
            throw IllegalStateException("이미 존재하는 이메일입니다")
        }

        val savedUser = userPort.saveEmail(
            user = input.toDomain(),
            password = input.password
        )

        return savedUser
    }

    @Transactional
    override fun createUserByProvider(input: CreateUserByProviderInput): User {
        userDomainService.validateUserByProvider(input.toDomain())

        if (userPort.findByEmail(input.email) != null) {
            throw IllegalStateException("이미 존재하는 이메일입니다")
        }

        return userPort.saveProvider(
            user = input.toDomain(),
            provider = input.provider,
            providerId = input.providerId
        )
    }

    override fun getUser(id: Long): User? {
        return userPort.findById(id)
    }
} 