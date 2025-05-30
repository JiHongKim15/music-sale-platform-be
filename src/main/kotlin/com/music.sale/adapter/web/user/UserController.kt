package com.music.sale.adapter.web.user

import com.music.sale.adapter.web.common.ApiResponse
import com.music.sale.adapter.web.common.ResponseCode
import com.music.sale.adapter.web.user.reqeust.CreateUserByEmailRequest
import com.music.sale.adapter.web.user.reqeust.CreateUserByProviderRequest
import com.music.sale.adapter.web.user.response.UserResponse
import com.music.sale.application.user.mapper.UserMapper
import com.music.sale.application.user.port.`in`.UserUseCase
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/users")
class UserController(
    private val userUseCase: UserUseCase,
    private val userMapper: UserMapper
) {
    @PostMapping("/email")
    fun createUserByEmail(@RequestBody request: CreateUserByEmailRequest): ResponseEntity<ApiResponse<UserResponse>> {
        val user = userUseCase.createUserByEmail(request.toUserUseCase())
        return ResponseEntity.ok(
            ApiResponse.success(
                data = UserResponse.from(user),
                code = ResponseCode.USER_CREATED.code
            )
        )
    }

    @PostMapping("/provider")
    fun createUserByProvider(@RequestBody request: CreateUserByProviderRequest): ResponseEntity<ApiResponse<UserResponse>> {
        val user = userUseCase.createUserByProvider(request.toUserUseCase())
        return ResponseEntity.ok(
            ApiResponse.success(
                data = UserResponse.from(user),
                code = ResponseCode.USER_CREATED.code
            )
        )
    }

    @GetMapping("/{id}")
    fun getUser(@PathVariable id: Long): ResponseEntity<ApiResponse<UserResponse>> {
        val user = userUseCase.getUser(id)
        return user?.let {
            ResponseEntity.ok(
                ApiResponse.success(
                    data = UserResponse.from(it)
                )
            )
        } ?: ResponseEntity.ok(
            ApiResponse.error(
                code = ResponseCode.USER_NOT_FOUND.code
            )
        )
    }
}