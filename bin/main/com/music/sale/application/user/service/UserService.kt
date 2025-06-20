// Copyright (C) 2024 Your Name or Company
package com.music.sale.application.user.service

import com.music.sale.application.user.dto.CreateUserByEmailInput
import com.music.sale.application.user.dto.CreateUserByProviderInput
import com.music.sale.application.user.dto.UserOutput
import com.music.sale.application.user.mapper.UserMapper
import com.music.sale.application.user.port.inport.UserUseCase
import com.music.sale.application.user.port.outport.UserPort
import com.music.sale.domain.user.UserDomainService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserService(
    private val userPort: UserPort,
    private val userDomainService: UserDomainService,
    private val userMapper: UserMapper,
) : UserUseCase {
    @Transactional
    override fun createUserByEmail(input: CreateUserByEmailInput): UserOutput {
        userDomainService.validateUserByEmail(input.toDomain())

        if (userPort.findByEmail(input.email) != null) {
            throw IllegalStateException("이미 존재하는 이메일입니다")
        }

        val savedUser = userPort.save(input.toDomain())

        return userMapper.toOutput(savedUser)
    }

    @Transactional
    override fun createUserByProvider(input: CreateUserByProviderInput): UserOutput {
        userDomainService.validateUserByProvider(input.toDomain())

        if (userPort.findByEmail(input.email) != null) {
            throw IllegalStateException("이미 존재하는 이메일입니다")
        }

        val savedUser = userPort.save(input.toDomain())

        return userMapper.toOutput(savedUser)
    }

    override fun getUser(id: Long): UserOutput? {
        return userPort.findById(id)?.let { userMapper.toOutput(it) }
    }
}
