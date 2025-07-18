package com.music.sale.web.user.request

import com.music.sale.application.user.dto.UpdateUserProfileInput
import com.music.sale.domain.user.enum.Gender

data class UpdateUserProfileRequest(
    val name: String? = null,
    val phoneNumber: String? = null,
    val gender: Gender? = null,
    val birthDate: String? = null,
    val profileImage: String? = null,
    val bio: String? = null,
) {
    fun toInput(): UpdateUserProfileInput {
        return UpdateUserProfileInput(
            name = name,
            phoneNumber = phoneNumber,
            gender = gender,
            birthDate = birthDate?.let { java.time.LocalDate.parse(it) },
            profileImageUrl = profileImage,
            bio = bio,
        )
    }
}
