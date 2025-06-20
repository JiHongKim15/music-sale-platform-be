// Copyright (C) 2024 Your Name or Company
package com.music.sale.adapter.persistence.user.mapper

import com.music.sale.adapter.persistence.user.entity.UserEntity
import com.music.sale.domain.user.User
import org.springframework.stereotype.Component

@Component
class UserPersistenceMapper {
    fun toDomain(entity: UserEntity): User {
        return User(
                id = entity.id,
                email = User.Email(entity.email),
                name = User.Name(entity.name),
                role = entity.role,
                provider = entity.provider,
                providerId = entity.providerId,
        )
    }

    fun toEntity(domain: User): UserEntity {
        return UserEntity(
                id = domain.id,
                email = domain.email!!.value,
                name = domain.name!!.value,
                role = domain.role!!,
                provider = domain.provider,
                providerId = domain.providerId,
        )
    }
}
