// Copyright (C) 2024 Your Name or Company
package com.music.sale.application.user.port.outport

import com.music.sale.domain.user.User

interface UserPort {
    fun save(user: User): User

    fun findById(id: Long): User?

    fun findByEmail(email: String): User?

    fun saveEmail(
        user: User,
        password: String,
    ): User

    fun saveProvider(
        user: User,
        provider: String,
        providerId: String,
    ): User
}
