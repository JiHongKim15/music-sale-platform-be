// Copyright (C) 2024 Your Name or Company
package com.music.sale.adapter.web.user

import com.music.sale.adapter.web.common.ApiResponse
import com.music.sale.adapter.web.user.mapper.UserWebMapper
import com.music.sale.adapter.web.user.reqeust.CreateUserByEmailRequest
import com.music.sale.adapter.web.user.reqeust.CreateUserByProviderRequest
import com.music.sale.adapter.web.user.response.UserResponse
import com.music.sale.application.user.port.inport.UserUseCase
import com.music.sale.common.ResponseCode
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/users")
class UserController(private val useCase: UserUseCase, private val userWebMapper: UserWebMapper) {
    @PostMapping("/email")
    fun createUserByEmail(
        @RequestBody request: CreateUserByEmailRequest,
    ): ResponseEntity<ApiResponse<UserResponse>> {
        val user = useCase.createUserByEmail(userWebMapper.toCreateUserByEmailInput(request))
        return ResponseEntity.ok(
            ApiResponse.success(
                data = userWebMapper.toUserResponse(user),
                code = ResponseCode.USER_CREATED.code,
            ),
        )
    }

    @PostMapping("/provider")
    fun createUserByProvider(
        @RequestBody request: CreateUserByProviderRequest,
    ): ResponseEntity<ApiResponse<UserResponse>> {
        val user = useCase.createUserByProvider(userWebMapper.toCreateUserByProviderInput(request))
        return ResponseEntity.ok(
            ApiResponse.success(
                data = userWebMapper.toUserResponse(user),
                code = ResponseCode.USER_CREATED.code,
            ),
        )
    }

    @GetMapping("/{id}")
    fun getUser(
        @PathVariable id: Long,
    ): ResponseEntity<ApiResponse<UserResponse>> {
        val user = useCase.getUser(id)
        return user?.let {
            ResponseEntity.ok(ApiResponse.success(data = userWebMapper.toUserResponse(it)))
        }
            ?: ResponseEntity.ok(ApiResponse.error(code = ResponseCode.USER_NOT_FOUND.code))
    }
}
