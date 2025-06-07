package com.music.sale.domain.user

import org.springframework.stereotype.Service

@Service
class UserDomainService {
    fun validateUserByEmail(user: User) {
        require(user.email?.value?.isNotBlank() == true) { "이메일은 필수입니다" }
        user.password?.value?.let { require(it.isNotBlank()) { "비밀번호는 필수입니다" } }
        require(user.getName()?.value?.isNotBlank() == true) { "이름은 필수입니다" }
        require(isValidEmail(user.email?.value ?: "")) { "올바른 이메일 형식이 아닙니다" }
    }

    fun validateUserByProvider(user: User) {
        require(user.email?.value?.isNotBlank() == true) { "이메일은 필수입니다" }
        user.providerId?.let { require(it.isNotBlank()) { "비밀번호는 필수입니다" } }
        user.provider?.let { require(it.isNotBlank()) { "비밀번호는 필수입니다" } }
        require(user.getName()?.value?.isNotBlank() == true) { "이름은 필수입니다" }
        require(isValidEmail(user.email?.value ?: "")) { "올바른 이메일 형식이 아닙니다" }
    }

    private fun isValidEmail(email: String): Boolean {
        return email.matches(Regex("^[A-Za-z0-9+_.-]+@(.+)$"))
    }
} 