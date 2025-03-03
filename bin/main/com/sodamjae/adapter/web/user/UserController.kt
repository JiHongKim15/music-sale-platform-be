package com.sodamjae.adapter.web.user

import com.sodamjae.application.user.port.`in`.UserUseCase
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/users")
class UserController(
    private val userUseCase: UserUseCase
) {
    @PostMapping("/email")
    fun createUserByEmail(@RequestBody request: CreateUserByEmailRequest): ResponseEntity<UserResponse> {
        val user = userUseCase.createUserByEmail(request.toUserUseCase())
        return ResponseEntity.ok(UserResponse.from(user))
    }

    @PostMapping("/provider")
    fun createUserByProvider(@RequestBody request: CreateUserByProviderRequest): ResponseEntity<UserResponse> {
        val user = userUseCase.createUserByProvider(request.toUserUseCase())
        return ResponseEntity.ok(UserResponse.from(user))
    }

    @GetMapping("/{id}")
    fun getUser(@PathVariable id: Long): ResponseEntity<UserResponse> {
        val user = userUseCase.getUser(id)
        return user?.let { ResponseEntity.ok(UserResponse.from(it)) }
            ?: ResponseEntity.notFound().build()
    }
}