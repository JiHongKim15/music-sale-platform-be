package com.music.sale.application.user.port.`in`

import com.music.sale.application.user.dto.CreateUserByEmailInput
import com.music.sale.application.user.dto.CreateUserByProviderInput
import com.music.sale.application.user.dto.UserOutput


interface UserUseCase {
    fun createUserByEmail(input: CreateUserByEmailInput): UserOutput
    fun createUserByProvider(input: CreateUserByProviderInput): UserOutput
    fun getUser(id: Long): UserOutput?
} 