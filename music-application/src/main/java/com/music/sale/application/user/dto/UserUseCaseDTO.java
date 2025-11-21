// Copyright (C) 2024 Your Name or Company
package com.music.sale.application.user.dto;

import com.music.sale.domain.user.User;
import com.music.sale.domain.user.enum.Gender;
import com.music.sale.domain.user.enum.SocialProvider;
import com.music.sale.domain.user.enum.UserRole;
import com.music.sale.domain.user.enum.UserType;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

// UseCase의 출력을 나타내는 DTO
@Value
@Builder
public class UserOutput {
    Long id;
    String email;
    String name;
    String nickname;
    UserRole role;
    String phoneNumber;
    Boolean phoneVerified;
    LocalDate birthDate;
    Gender gender;
    String zipcode;
    String baseAddress;
    String detailAddress;
    String profileImageUrl;
    String bio;
    UserType userType;
    Integer preferredPriceRangeMin;
    Integer preferredPriceRangeMax;
    Integer totalPurchases;
    Integer totalSales;
    Double rating;
    Integer reviewCount;
    Boolean isActive;
    Boolean isVerified;
    LocalDateTime lastLoginAt;
    Boolean marketingAgreed;
    @Builder.Default
    List<SocialConnectionOutput> socialConnections = Collections.emptyList();
    @Builder.Default
    List<Object> interestedCategories = Collections.emptyList();
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}

// 이메일 회원가입 입력
@Value
@Builder
public class CreateUserByEmailInput {
    String email;
    String password;
    String name;
    String nickname;
    UserRole role;
    String phoneNumber;
    LocalDate birthDate;
    Gender gender;
    String zipcode;
    String baseAddress;
    String detailAddress;
    @Builder.Default
    UserType userType = UserType.BUYER;
    @Builder.Default
    Boolean marketingAgreed = false;

    public User toDomain() {
        return User.create(
                email,
                password,
                name,
                nickname,
                role,
                phoneNumber,
                birthDate,
                gender != null ? gender.name() : null,
                zipcode,
                baseAddress,
                detailAddress,
                userType,
                marketingAgreed
        );
    }
}

// 휴대폰번호 회원가입 입력
@Value
@Builder
public class CreateUserByPhoneInput {
    String phoneNumber;
    String password;
    String name;
    String nickname;
    UserRole role;
    LocalDate birthDate;
    Gender gender;
    String zipcode;
    String baseAddress;
    String detailAddress;
    @Builder.Default
    UserType userType = UserType.BUYER;
    @Builder.Default
    Boolean marketingAgreed = false;

    public User toDomain() {
        return User.create(
                phoneNumber,
                password,
                name,
                nickname,
                role,
                birthDate,
                gender != null ? gender.name() : null,
                zipcode,
                baseAddress,
                detailAddress,
                userType,
                marketingAgreed
        );
    }
}

// 소셜 로그인 회원가입 입력
@Value
@Builder
public class CreateUserByProviderInput {
    String email;
    SocialProvider provider;
    String providerId;
    String name;
    String nickname;
    UserRole role;
    String phoneNumber;
    LocalDate birthDate;
    Gender gender;
    String zipcode;
    String baseAddress;
    String detailAddress;
    @Builder.Default
    UserType userType = UserType.BUYER;
    @Builder.Default
    Boolean marketingAgreed = false;
    String providerEmail;
    String providerName;
    String providerProfileImage;

    public User toDomain() {
        return User.create(
                email,
                provider.name(),
                providerId,
                name,
                nickname,
                role,
                phoneNumber,
                birthDate,
                gender != null ? gender.name() : null,
                zipcode,
                baseAddress,
                detailAddress,
                userType,
                marketingAgreed
        );
    }
}

// 프로필 수정 입력
@Value
@Builder
public class UpdateUserProfileInput {
    String name;
    String nickname;
    String phoneNumber;
    LocalDate birthDate;
    Gender gender;
    String zipcode;
    String baseAddress;
    String detailAddress;
    String profileImageUrl;
    String bio;
    UserType userType;
    Integer preferredPriceRangeMin;
    Integer preferredPriceRangeMax;
    Boolean marketingAgreed;
}

// 소셜 계정 연동 입력
@Value
@Builder
public class ConnectSocialAccountInput {
    SocialProvider provider;
    String providerId;
    String providerEmail;
    String providerName;
    String providerProfileImage;
    String accessToken;
    String refreshToken;
    LocalDateTime tokenExpiresAt;
}

// 소셜 연동 출력
@Value
@Builder
public class SocialConnectionOutput {
    Long id;
    SocialProvider provider;
    String providerId;
    String providerEmail;
    String providerName;
    String providerProfileImage;
    LocalDateTime connectedAt;
    LocalDateTime lastUsedAt;
    Boolean isActive;
}

// 휴대폰 인증 출력
@Value
@Builder
public class PhoneVerificationOutput {
    String phoneNumber;
    String verificationCode;
    com.music.sale.domain.user.enum.VerificationType verificationType;
    LocalDateTime expiresAt;
    Integer attemptCount;
    Boolean isUsed;
}

// 휴대폰 인증 결과
@Value
@Builder
public class PhoneVerificationResult {
    Boolean success;
    String message;
    LocalDateTime verifiedAt;
}

// 소셜 연동 결과
@Value
@Builder
public class SocialConnectionResult {
    Boolean success;
    String message;
    SocialProvider provider;
    LocalDateTime connectedAt;
}

// 사용자 통계 출력
@Value
@Builder
public class UserStatsOutput {
    Long userId;
    Integer totalPurchases;
    Integer totalSales;
    Double rating;
    Integer reviewCount;
    LocalDateTime lastLoginAt;
    LocalDateTime joinDate;
}

// 2단계 인증 설정 출력
@Value
@Builder
public class TwoFactorSetupOutput {
    Boolean enabled;
    String secret;
    String qrCodeUrl;
    List<String> backupCodes;
}

// 2단계 인증 확인 결과
@Value
@Builder
public class TwoFactorVerificationResult {
    Boolean success;
    String message;
}

// 프로필 업데이트 결과
@Value
@Builder
public class UserProfileUpdateResult {
    Boolean success;
    String message;
    List<String> updatedFields;
}

