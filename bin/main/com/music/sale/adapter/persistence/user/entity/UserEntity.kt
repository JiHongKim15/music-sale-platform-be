package com.music.sale.adapter.persistence.user.entity

import com.music.sale.adapter.persistence.common.BaseEntity
import com.music.sale.domain.user.User
import com.music.sale.domain.user.enum.UserRole
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
    var name: String,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    var role: UserRole

) : BaseEntity() {

    fun toDomain(): User {
        return User(
            id = id!!,
            email = User.Email(email),
            name = User.Name(name),
            role = role
        )
    }

    companion object {
        fun fromDomain(user: User): UserEntity {
            return UserEntity(
                id = user.id,
                email = user.email?.value ?: "",
                name = user.getName()?.value ?: "",
                role = user.getRole() ?: UserRole.USER
            )
        }
    }
} 