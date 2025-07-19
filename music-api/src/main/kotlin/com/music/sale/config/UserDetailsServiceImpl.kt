package com.music.sale.config

import com.music.sale.persistence.user.repository.UserRepository
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
open class UserDetailsServiceImpl(private val userRepository: UserRepository) : UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails {
        val user =
            userRepository.findByEmail(username)
                ?: throw UsernameNotFoundException("User not found with email: $username")

        return User.builder()
            .username(user.email)
            .password("") // 실제로는 인증 테이블에서 가져와야 함
            .authorities(listOf(SimpleGrantedAuthority("ROLE_${user.role}")))
            .build()
    }
}
