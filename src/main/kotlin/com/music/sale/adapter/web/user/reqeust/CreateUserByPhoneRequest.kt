package com.music.sale.adapter.web.user.reqeust

import com.music.sale.adapter.persistence.user.entity.Gender
import com.music.sale.adapter.persistence.user.entity.UserType
import com.music.sale.domain.user.enum.UserRole
import java.time.LocalDate

data class CreateUserByPhoneRequest(
    val phoneNumber: String,
    val password: String,
    val name: String,
    val nickname: String,
    val role: UserRole = UserRole.USER,
    val birthDate: LocalDate? = null,
    val gender: Gender? = null,
    val zipcode: String? = null,
    val baseAddress: String? = null,
    val detailAddress: String? = null,
    val userType: UserType = UserType.BUYER,
    val marketingAgreed: Boolean = false,
) 
