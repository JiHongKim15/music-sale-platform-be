package com.music.sale.application.user.port.`in`

import com.music.sale.domain.user.User


interface UserUseCase {
    fun createUserByEmail(user: CreateUserByEmailInput): User

    fun createUserByProvider(user: CreateUserByProviderInput): User
    fun getUser(id: Long): User?
} 