package com.music.sale.application.user.port.out

import com.music.sale.domain.user.User


interface UserPort {
    fun saveProvider(user: User, provider: String, providerId: String): UserOutput
    fun saveEmail(user: User, password: String): UserOutput
    fun findById(id: Long): UserOutput?
    fun findByEmail(email: String): UserOutput?
} 