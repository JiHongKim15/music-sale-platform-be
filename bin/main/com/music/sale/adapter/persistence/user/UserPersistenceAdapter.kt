package com.music.sale.adapter.persistence.user

import com.music.sale.adapter.persistence.user.entity.AuthUserEntity
import com.music.sale.adapter.persistence.user.entity.UserEntity
import com.music.sale.adapter.persistence.user.repository.AuthUserRepository
import com.music.sale.adapter.persistence.user.repository.UserRepository
import com.music.sale.application.user.port.out.CategoryPort
import com.music.sale.domain.user.User
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
class UserPersistenceAdapter(
    private val userRepository: UserRepository,
    private val authUserRepository: AuthUserRepository
) : CategoryPort {

    @Transactional
    override fun saveProvider(user: User, provider: String, providerId: String): User {
        val userEntity = UserEntity.fromDomain(user)
        val savedUserEntity = userRepository.save(userEntity)

        val authUserEntity = AuthUserEntity.fromProvider(user, provider, providerId)
        authUserRepository.save(authUserEntity)

        return savedUserEntity.toDomain()
    }

    @Transactional
    override fun saveEmail(user: User, password: String): User {
        val userEntity = UserEntity.fromDomain(user)
        val savedUserEntity = userRepository.save(userEntity)

        val authUserEntity = AuthUserEntity.formEmail(user, password)
        authUserRepository.save(authUserEntity)

        return savedUserEntity.toDomain()
    }

    override fun findById(id: Long): User? {
        return userRepository.findById(id).orElse(null)?.toDomain()
    }

    override fun findByEmail(email: String): User? {
        return userRepository.findByEmail(email)?.toDomain()
    }
} 