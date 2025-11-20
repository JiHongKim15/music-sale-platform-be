package com.music.sale.web.user.mapper;

import com.music.sale.application.user.dto.CreateUserByEmailInput;
import com.music.sale.application.user.dto.CreateUserByPhoneInput;
import com.music.sale.application.user.dto.CreateUserByProviderInput;
import com.music.sale.application.user.dto.PhoneVerificationOutput;
import com.music.sale.application.user.dto.SocialConnectionOutput;
import com.music.sale.application.user.dto.TwoFactorSetupOutput;
import com.music.sale.application.user.dto.UserOutput;
import com.music.sale.application.user.dto.UserStatsOutput;
import com.music.sale.domain.user.enum.SocialProvider;
import com.music.sale.domain.user.enum.UserRole;
import com.music.sale.domain.user.enum.UserType;
import com.music.sale.web.user.request.CreateUserByEmailRequest;
import com.music.sale.web.user.request.CreateUserByPhoneRequest;
import com.music.sale.web.user.request.CreateUserByProviderRequest;
import com.music.sale.web.user.response.CategoryResponse;
import com.music.sale.web.user.response.PhoneVerificationResponse;
import com.music.sale.web.user.response.PhoneVerificationResultResponse;
import com.music.sale.web.user.response.SocialConnectionResponse;
import com.music.sale.web.user.response.SocialConnectionResultResponse;
import com.music.sale.web.user.response.TwoFactorSetupResponse;
import com.music.sale.web.user.response.UserDetailResponse;
import com.music.sale.web.user.response.UserProfileUpdateResponse;
import com.music.sale.web.user.response.UserResponse;
import com.music.sale.web.user.response.UserStatsResponse;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class UserWebMapper {

    public CreateUserByEmailInput toCreateUserByEmailInput(
            CreateUserByEmailRequest request) {
        return new CreateUserByEmailInput(
                request.email(),
                request.password(),
                request.name(),
                request.name(),
                UserRole.USER,
                request.phoneNumber(),
                parseDate(request.birthDate()),
                request.gender(),
                null,
                null,
                null,
                request.userType(),
                false);
    }

    public CreateUserByPhoneInput toCreateUserByPhoneInput(
            CreateUserByPhoneRequest request) {
        return new CreateUserByPhoneInput(
                request.phoneNumber(),
                request.password(),
                request.name(),
                request.nickname(),
                request.role(),
                request.birthDate(),
                request.gender(),
                request.zipcode(),
                request.baseAddress(),
                request.detailAddress(),
                request.userType(),
                Boolean.TRUE.equals(request.marketingAgreed()));
    }

    public CreateUserByProviderInput toCreateUserByProviderInput(
            CreateUserByProviderRequest request) {
        return new CreateUserByProviderInput(
                request.email(),
                request.provider(),
                request.socialId(),
                request.name(),
                request.name(),
                UserRole.USER,
                request.phoneNumber(),
                parseDate(request.birthDate()),
                request.gender(),
                null,
                null,
                null,
                request.userType(),
                false,
                request.email(),
                request.name(),
                null);
    }

    public UserResponse toUserResponse(UserOutput output) {
        return new UserResponse(
                output.getId(),
                output.getEmail(),
                output.getName(),
                output.getNickname(),
                output.getRole().name(),
                output.getPhoneNumber(),
                output.getPhoneVerified(),
                output.getBirthDate(),
                output.getGender() != null ? output.getGender().name() : null,
                output.getZipcode(),
                output.getBaseAddress(),
                output.getDetailAddress(),
                output.getProfileImageUrl(),
                output.getBio(),
                output.getUserType() != null
                        ? output.getUserType().name()
                        : UserType.BUYER.name(),
                output.getPreferredPriceRangeMin(),
                output.getPreferredPriceRangeMax(),
                output.getTotalPurchases(),
                output.getTotalSales(),
                output.getRating(),
                output.getReviewCount(),
                output.getIsActive(),
                output.getIsVerified(),
                output.getLastLoginAt(),
                output.getMarketingAgreed(),
                output.getCreatedAt(),
                output.getUpdatedAt());
    }

    public UserDetailResponse toUserDetailResponse(UserOutput output) {
        List<SocialConnectionResponse> socialConnections =
                output.getSocialConnections() == null
                        ? Collections.emptyList()
                        : output.getSocialConnections().stream()
                                .map(this::toSocialConnectionResponse)
                                .collect(Collectors.toList());

        List<CategoryResponse> interestedCategories =
                output.getInterestedCategories() == null
                        ? Collections.emptyList()
                        : output.getInterestedCategories().stream()
                                .map(this::toCategoryResponse)
                                .collect(Collectors.toList());

        return new UserDetailResponse(
                output.getId(),
                output.getEmail(),
                output.getName(),
                output.getNickname(),
                output.getRole().name(),
                output.getPhoneNumber(),
                output.getPhoneVerified(),
                output.getBirthDate(),
                output.getGender() != null ? output.getGender().name() : null,
                output.getZipcode(),
                output.getBaseAddress(),
                output.getDetailAddress(),
                output.getProfileImageUrl(),
                output.getBio(),
                output.getUserType() != null
                        ? output.getUserType().name()
                        : UserType.BUYER.name(),
                output.getPreferredPriceRangeMin(),
                output.getPreferredPriceRangeMax(),
                output.getTotalPurchases(),
                output.getTotalSales(),
                output.getRating(),
                output.getReviewCount(),
                output.getIsActive(),
                output.getIsVerified(),
                output.getLastLoginAt(),
                output.getMarketingAgreed(),
                socialConnections,
                interestedCategories,
                output.getCreatedAt(),
                output.getUpdatedAt());
    }

    public SocialConnectionResponse toSocialConnectionResponse(
            SocialConnectionOutput socialConnection) {
        return new SocialConnectionResponse(
                socialConnection.getId(),
                socialConnection.getProvider().name(),
                socialConnection.getProviderId(),
                socialConnection.getProviderEmail(),
                socialConnection.getProviderName(),
                socialConnection.getProviderProfileImage(),
                socialConnection.getConnectedAt(),
                socialConnection.getLastUsedAt(),
                socialConnection.getIsActive());
    }

    public CategoryResponse toCategoryResponse(Object category) {
        // TODO: Replace placeholder mapping once CategoryOutput is available
        return new CategoryResponse(0L, "카테고리", "/카테고리");
    }

    public PhoneVerificationResponse toPhoneVerificationResponse(
            PhoneVerificationOutput verification) {
        return new PhoneVerificationResponse(
                verification.getPhoneNumber(),
                verification.getVerificationType().name(),
                verification.getExpiresAt(),
                verification.getAttemptCount(),
                verification.getIsUsed());
    }

    public PhoneVerificationResultResponse toPhoneVerificationResultResponse(
            boolean success,
            String message,
            String phoneNumber,
            LocalDateTime verifiedAt) {
        return new PhoneVerificationResultResponse(
                success,
                message,
                phoneNumber,
                verifiedAt);
    }

    public SocialConnectionResultResponse toSocialConnectionResultResponse(
            boolean success,
            String message,
            SocialProvider provider,
            LocalDateTime connectedAt) {
        return new SocialConnectionResultResponse(
                success,
                message,
                provider.name(),
                connectedAt);
    }

    public UserStatsResponse toUserStatsResponse(UserStatsOutput stats) {
        return new UserStatsResponse(
                stats.getUserId(),
                stats.getTotalPurchases(),
                stats.getTotalSales(),
                stats.getRating(),
                stats.getReviewCount(),
                stats.getLastLoginAt(),
                stats.getJoinDate());
    }

    public TwoFactorSetupResponse toTwoFactorSetupResponse(
            TwoFactorSetupOutput setup) {
        return new TwoFactorSetupResponse(
                setup.getEnabled(),
                setup.getSecret(),
                setup.getQrCodeUrl(),
                setup.getBackupCodes());
    }

    public UserProfileUpdateResponse toUserProfileUpdateResponse(
            boolean success,
            String message,
            List<String> updatedFields) {
        return new UserProfileUpdateResponse(
                success,
                message,
                updatedFields);
    }

    private LocalDate parseDate(String date) {
        return date != null ? LocalDate.parse(date) : null;
    }
}

