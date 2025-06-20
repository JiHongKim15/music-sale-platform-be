package com.music.sale.adapter.web.user.mapper

import com.music.sale.adapter.web.user.reqeust.CreateUserByEmailRequest
import com.music.sale.adapter.web.user.reqeust.CreateUserByProviderRequest
import com.music.sale.adapter.web.user.response.UserResponse
import com.music.sale.application.user.dto.CreateUserByEmailInput
import com.music.sale.application.user.dto.CreateUserByProviderInput
import com.music.sale.application.user.dto.UserOutput
import org.springframework.stereotype.Component

@Component
class UserWebMapper {


    fun toCreateUserByEmailInput(request: CreateUserByEmailRequest): CreateUserByEmailInput {
        return CreateUserByEmailInput(
            email = request.email,
            password = request.password,
            name = request.name,
            role = request.role
        )
    }

    fun toCreateUserByProviderInput(request: CreateUserByProviderRequest): CreateUserByProviderInput {
        return CreateUserByProviderInput(
            email = request.email,
            provider = request.provider,
            providerId = request.providerId,
            name = request.name,
            role = request.role
        )
    }

    fun toUserResponse(output: UserOutput): UserResponse {
        return UserResponse(
            id = output.id,
            email = output.email,
            name = output.name,
            role = output.role.toString()
        )
    }
} 