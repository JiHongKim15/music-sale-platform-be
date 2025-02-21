package com.sodamjae.domain.user

import org.springframework.stereotype.Service

@Service
class UserDomainService {
    fun validateUser(user: User) {
        require(user.email.value.isNotBlank()) { "이메일은 필수입니다" }
        require(user.getPassword().value.isNotBlank()) { "비밀번호는 필수입니다" }
        require(user.getName().value.isNotBlank()) { "이름은 필수입니다" }
        require(isValidEmail(user.email.value)) { "올바른 이메일 형식이 아닙니다" }
    }

    private fun isValidEmail(email: String): Boolean {
        return email.matches(Regex("^[A-Za-z0-9+_.-]+@(.+)$"))
    }
} 