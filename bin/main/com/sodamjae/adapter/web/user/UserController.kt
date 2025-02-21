package com.sodamjae.adapter.web.user

import com.sodamjae.application.port.`in`.UserUseCase
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/users")
class UserController(
    private val userUseCase: UserUseCase
) {
    @PostMapping
    fun createUser(@RequestBody request: CreateUserRequest): ResponseEntity<UserResponse> {
        val user = userUseCase.createUser(request.toDomain())
        return ResponseEntity.ok(UserResponse.from(user))
    }

    @GetMapping("/{id}")
    fun getUser(@PathVariable id: Long): ResponseEntity<UserResponse> {
        val user = userUseCase.getUser(id)
        return user?.let { ResponseEntity.ok(UserResponse.from(it)) }
            ?: ResponseEntity.notFound().build()
    }
}