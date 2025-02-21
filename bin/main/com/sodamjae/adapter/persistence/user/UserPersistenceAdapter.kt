package com.sodamjae.adapter.persistence.user

import com.sodamjae.application.port.out.UserPort
import com.sodamjae.domain.user.User
import org.springframework.stereotype.Repository

@Repository
class UserPersistenceAdapter : UserPort {
    override fun save(user: User): User {
        // JPA 구현체 코드
        TODO("Not yet implemented")
    }

    override fun findById(id: Long): User? {
        TODO("Not yet implemented")
    }

    override fun findByEmail(email: String): User? {
        TODO("Not yet implemented")
    }
} 