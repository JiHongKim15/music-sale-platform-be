package com.music.sale.adapter.persistence.user.mapper

import com.music.sale.adapter.persistence.user.entity.UserEntity
import com.music.sale.domain.user.User
import org.springframework.stereotype.Component

@Component
class UserPersistenceMapper {
    fun toDomain(entity: UserEntity): User {
        TODO("Not yet implemented")
    }
    fun toEntity(domain: User): UserEntity {
        TODO("Not yet implemented")
    }
}