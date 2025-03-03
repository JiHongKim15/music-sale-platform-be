package com.sodamjae.application.user.port.out

import com.sodamjae.domain.user.User


interface UserPort {
    fun saveProvider(user: User, provider: String, providerId: String): User
    fun saveEmail(user: User, password: String): User
    fun findById(id: Long): User?
    fun findByEmail(email: String): User?
} 