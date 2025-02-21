package com.sodamjae.application.port.`in`

import com.sodamjae.domain.user.User


interface UserUseCase {
    fun createUser(user: User): User
    fun getUser(id: Long): User?
} 