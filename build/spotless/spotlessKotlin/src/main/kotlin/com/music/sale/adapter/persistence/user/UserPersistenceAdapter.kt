// Copyright (C) 2024 Your Name or Company
package com.music.sale.adapter.persistence.user

import com.music.sale.adapter.persistence.user.entity.AuthUserEntity
import com.music.sale.adapter.persistence.user.mapper.UserPersistenceMapper
import com.music.sale.adapter.persistence.user.repository.AuthUserRepository
import com.music.sale.adapter.persistence.user.repository.UserRepository
import com.music.sale.application.user.port.outport.UserPort
import com.music.sale.domain.user.User
import org.springframework.stereotype.Repository

@Repository
class UserPersistenceAdapter(
    private val userRepository: UserRepository,
    private val authUserRepository: AuthUserRepository,
    private val mapper: UserPersistenceMapper,
) : UserPort {
    override fun save(user: User): User {
        val userEntity = mapper.toEntity(user)
        val savedUser = userRepository.save(userEntity)
        return mapper.toDomain(savedUser)
    }

    override fun findById(id: Long): User? {
        return userRepository.findById(id).map(mapper::toDomain).orElse(null)
    }

    override fun findByEmail(email: String): User? {
        return userRepository.findByEmail(email)?.let(mapper::toDomain)
    }

    override fun saveEmail(
        user: User,
        password: String,
    ): User {
        val userEntity = mapper.toEntity(user)
        val savedUserEntity = userRepository.save(userEntity)

        val authUserEntity =
            AuthUserEntity(
                userId = savedUserEntity.id!!,
                provider = "email",
                // NOTE: Password should be encoded
                password = password,
            )
        authUserRepository.save(authUserEntity)
        return mapper.toDomain(savedUserEntity)
    }

    override fun saveProvider(
        user: User,
        provider: String,
        providerId: String,
    ): User {
        TODO("Not yet implemented")
    }
}
