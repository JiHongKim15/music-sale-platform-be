package com.sodamjae.domain.user

class User private constructor(
    val id: Long? = null,
    val email: Email,
    private var password: Password,
    private var name: Name,
    private var role: UserRole,
) {
    // Getters for persistence
    fun getPassword(): Password = password
    fun getName(): Name = name
    fun getRole(): UserRole = role

    companion object {
        fun create(email: Email, password: Password, name: Name, role: UserRole = UserRole.CUSTOMER): User {
            return User(
                email = email,
                password = password,
                name = name,
                role = role
            )
        }
    }

    fun updateProfile(name: Name) {
        this.name = name
    }

    fun changePassword(newPassword: Password) {
        this.password = newPassword
    }

    fun changeRole(newRole: UserRole) {
        this.role = newRole
    }

    // Value Objects
    @JvmInline
    value class Email(val value: String) {
        init {
            require(value.contains("@")) { "이메일 형식이 올바르지 않습니다" }
        }
    }

    @JvmInline
    value class Password private constructor(val value: String) {
        companion object {
            fun create(password: String): Password {
                require(password.length >= 8) { "비밀번호는 8자 이상이어야 합니다" }
                return Password(password)
            }
        }
    }

    @JvmInline
    value class Name(val value: String) {
        init {
            require(value.isNotBlank()) { "이름은 비어있을 수 없습니다" }
        }
    }

    enum class UserRole {
        ADMIN, SELLER, CUSTOMER;

        fun hasPermission(requiredRole: UserRole): Boolean {
            return when (this) {
                ADMIN -> true
                SELLER -> this == requiredRole
                CUSTOMER -> this == requiredRole
            }
        }
    }
} 