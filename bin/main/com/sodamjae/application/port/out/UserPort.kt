package com.sodamjae.application.port.out

import com.sodamjae.domain.user.User


interface UserPort {
    fun save(user: User): User
    fun findById(id: Long): User?
    fun findByEmail(email: String): User?
} 