package com.sodamjae.application.user.port.`in`

import com.sodamjae.domain.user.User


interface UserUseCase {
    fun createUserByEmail(user: CreateUserByEmailInput): User

    fun createUserByProvider(user: CreateUserByProviderInput): User
    fun getUser(id: Long): User?
} 