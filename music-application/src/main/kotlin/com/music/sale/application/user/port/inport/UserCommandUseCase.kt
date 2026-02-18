package com.music.sale.application.user.port.inport

import com.music.sale.application.user.dto.input.AgreeTermsInput
import com.music.sale.application.user.dto.input.CreateSocialUserInput
import com.music.sale.application.user.dto.input.UpdateUserInput
import com.music.sale.application.user.dto.output.UserOutput
import com.music.sale.domain.user.User

interface UserCommandUseCase {
    fun createSocialUser(input: CreateSocialUserInput): User

    fun updateUser(
        userId: Long,
        input: UpdateUserInput,
        currentUserId: Long,
    ): UserOutput

    fun withdrawUser(
        userId: Long,
        currentUserId: Long,
    )

    fun agreeToTerms(
        input: AgreeTermsInput,
        currentUserId: Long,
    )

    fun verifyIdentity(
        userId: Long,
        ci: String,
        realName: String,
        phoneNumber: String,
        birthDate: String,
    ): User
}
