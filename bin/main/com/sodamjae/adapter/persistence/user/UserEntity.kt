package com.sodamjae.adapter.persistence.user

import com.sodamjae.adapter.persistence.common.BaseEntity
import com.sodamjae.domain.user.User
import jakarta.persistence.*

@Entity
@Table(name = "users")
class UserEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(nullable = false, unique = true)
    val email: String,

    @Column(nullable = false)
    var password: String,

    @Column(nullable = false)
    var name: String,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    var role: User.UserRole
) : BaseEntity() {
    fun toDomain(): User {
        return User.create(
            email = User.Email(email),
            password = User.Password.create(password),
            name = User.Name(name),
            role = role
        )
    }

    companion object {
        fun fromDomain(user: User): UserEntity {
            return UserEntity(
                id = user.id,
                email = user.email.value,
                password = user.getPassword().value,
                name = user.getName().value,
                role = user.getRole()
            )
        }
    }
} 