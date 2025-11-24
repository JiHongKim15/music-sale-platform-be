package com.music.sale.web.user.request;

import com.music.sale.application.user.dto.UpdateUserProfileInput;
import com.music.sale.domain.user.enums.Gender;
import java.time.LocalDate;

public record UpdateUserProfileRequest(
        String name,
        String phoneNumber,
        Gender gender,
        String birthDate,
        String profileImage,
        String bio) {

    public UpdateUserProfileInput toInput() {
        LocalDate parsedBirthDate = birthDate != null ? LocalDate.parse(birthDate) : null;
        return new UpdateUserProfileInput(
                name,
                phoneNumber,
                gender,
                parsedBirthDate,
                profileImage,
                bio);
    }
}

