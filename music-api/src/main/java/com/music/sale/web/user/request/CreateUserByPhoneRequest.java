package com.music.sale.web.user.request;

import com.music.sale.domain.user.enum.Gender;
import com.music.sale.domain.user.enum.UserRole;
import com.music.sale.domain.user.enum.UserType;
import java.time.LocalDate;

public record CreateUserByPhoneRequest(
        String phoneNumber,
        String password,
        String name,
        String nickname,
        UserRole role,
        LocalDate birthDate,
        Gender gender,
        String zipcode,
        String baseAddress,
        String detailAddress,
        UserType userType,
        Boolean marketingAgreed) {

    public CreateUserByPhoneRequest {
        if (role == null) {
            role = UserRole.USER;
        }
        if (userType == null) {
            userType = UserType.BUYER;
        }
        if (marketingAgreed == null) {
            marketingAgreed = Boolean.FALSE;
        }
    }
}

