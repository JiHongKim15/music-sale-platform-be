// Copyright (C) 2024 Your Name or Company
package com.music.sale.application.user.port.inport;

import com.music.sale.application.user.dto.*;
import com.music.sale.domain.user.enum.VerificationType;

import java.util.List;

public interface UserUseCase {
    // 기본 사용자 관리
    UserOutput createUserByEmail(CreateUserByEmailInput input);

    UserOutput createUserByPhone(CreateUserByPhoneInput input);

    UserOutput createUserByProvider(CreateUserByProviderInput input);

    UserOutput getUserById(Long userId);

    UserProfileUpdateResult updateUserProfile(Long userId, UpdateUserProfileInput input);

    void deleteUser(Long userId);

    // 휴대폰 인증
    PhoneVerificationOutput sendPhoneVerificationCode(String phoneNumber, VerificationType verificationType);

    PhoneVerificationResult verifyPhoneCode(String phoneNumber, String verificationCode, VerificationType verificationType);

    // 소셜 계정 연동
    SocialConnectionResult connectSocialAccount(Long userId, ConnectSocialAccountInput input);

    SocialConnectionResult disconnectSocialAccount(Long userId, String provider);

    // 사용자 통계
    UserStatsOutput getUserStats(Long userId);

    // 2단계 인증
    TwoFactorSetupOutput setupTwoFactorAuth(Long userId);

    TwoFactorVerificationResult verifyTwoFactorAuth(Long userId, String code);

    // 관심 카테고리
    void setInterestedCategories(Long userId, List<Long> categoryIds);

    List<Object> getInterestedCategories(Long userId);
}

