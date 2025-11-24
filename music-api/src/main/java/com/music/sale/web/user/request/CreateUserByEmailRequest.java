package com.music.sale.web.user.request;

import com.music.sale.domain.user.enums.Gender;
import com.music.sale.domain.user.enums.UserType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record CreateUserByEmailRequest(
        @NotBlank(message = "이름은 필수입니다") String name,
        @NotBlank(message = "이메일은 필수입니다")
        @Email(message = "올바른 이메일 형식이 아닙니다")
        String email,
        @NotBlank(message = "비밀번호는 필수입니다")
        @Pattern(
                regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
                message = "비밀번호는 8자 이상이며, 영문, 숫자, 특수문자를 포함해야 합니다")
        String password,
        String phoneNumber,
        Gender gender,
        String birthDate,
        UserType userType) {

    public CreateUserByEmailRequest {
        if (userType == null) {
            userType = UserType.BUYER;
        }
    }
}

