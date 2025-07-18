package com.music.sale.web.user.request

import com.music.sale.domain.user.enum.Gender
import com.music.sale.domain.user.enum.UserType
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern

data class CreateUserByEmailRequest(
    @field:NotBlank(message = "이름은 필수입니다") val name: String,
    @field:NotBlank(message = "이메일은 필수입니다")
    @field:Email(message = "올바른 이메일 형식이 아닙니다")
    val email: String,
    @field:NotBlank(message = "비밀번호는 필수입니다")
    @field:Pattern(
        regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
        message = "비밀번호는 8자 이상이며, 영문, 숫자, 특수문자를 포함해야 합니다",
    )
    val password: String,
    val phoneNumber: String? = null,
    val gender: Gender? = null,
    val birthDate: String? = null,
    val userType: UserType = UserType.BUYER,
)
