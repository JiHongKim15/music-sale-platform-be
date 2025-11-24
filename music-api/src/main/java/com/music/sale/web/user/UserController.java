package com.music.sale.web.user;

import com.music.sale.application.user.dto.PhoneVerificationOutput;
import com.music.sale.application.user.dto.PhoneVerificationResult;
import com.music.sale.application.user.dto.SocialConnectionResult;
import com.music.sale.application.user.dto.TwoFactorVerificationResult;
import com.music.sale.application.user.dto.UserProfileUpdateResult;
import com.music.sale.application.user.port.inport.UserUseCase;
import com.music.sale.domain.user.enums.SocialProvider;
import com.music.sale.domain.user.enums.VerificationType;
import com.music.sale.common.ApiResponse;
import com.music.sale.web.user.mapper.UserWebMapper;
import com.music.sale.web.user.request.ConnectSocialAccountRequest;
import com.music.sale.web.user.request.CreateUserByEmailRequest;
import com.music.sale.web.user.request.CreateUserByPhoneRequest;
import com.music.sale.web.user.request.CreateUserByProviderRequest;
import com.music.sale.web.user.request.SendPhoneVerificationRequest;
import com.music.sale.web.user.request.SetInterestedCategoriesRequest;
import com.music.sale.web.user.request.UpdateUserProfileRequest;
import com.music.sale.web.user.request.VerifyPhoneCodeRequest;
import com.music.sale.web.user.request.VerifyTwoFactorRequest;
import com.music.sale.web.user.response.CategoryResponse;
import com.music.sale.web.user.response.PhoneVerificationResponse;
import com.music.sale.web.user.response.PhoneVerificationResultResponse;
import com.music.sale.web.user.response.SocialConnectionResultResponse;
import com.music.sale.web.user.response.TwoFactorSetupResponse;
import com.music.sale.web.user.response.UserDetailResponse;
import com.music.sale.web.user.response.UserProfileUpdateResponse;
import com.music.sale.web.user.response.UserResponse;
import com.music.sale.web.user.response.UserStatsResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
@Tag(name = "User", description = "사용자 관리 API")
public class UserController {

    private final UserUseCase userUseCase;
    private final UserWebMapper mapper;

    public UserController(UserUseCase userUseCase, UserWebMapper mapper) {
        this.userUseCase = userUseCase;
        this.mapper = mapper;
    }

    @PostMapping("/register/email")
    @Operation(summary = "이메일로 회원가입", description = "이메일과 비밀번호로 새로운 사용자를 등록합니다.")
    public ApiResponse<UserResponse> createUserByEmail(
            @RequestBody CreateUserByEmailRequest request) {
        var input = mapper.toCreateUserByEmailInput(request);
        var output = userUseCase.createUserByEmail(input);
        return ApiResponse.success(mapper.toUserResponse(output), "USER_CREATED");
    }

    @PostMapping("/register/phone")
    @Operation(summary = "휴대폰번호로 회원가입", description = "휴대폰번호와 비밀번호로 새로운 사용자를 등록합니다.")
    public ApiResponse<UserResponse> createUserByPhone(
            @RequestBody CreateUserByPhoneRequest request) {
        var input = mapper.toCreateUserByPhoneInput(request);
        var output = userUseCase.createUserByPhone(input);
        return ApiResponse.success(mapper.toUserResponse(output), "USER_CREATED");
    }

    @PostMapping("/register/social")
    @Operation(summary = "소셜 로그인으로 회원가입", description = "소셜 플랫폼을 통해 새로운 사용자를 등록합니다.")
    public ApiResponse<UserResponse> createUserByProvider(
            @RequestBody CreateUserByProviderRequest request) {
        var input = mapper.toCreateUserByProviderInput(request);
        var output = userUseCase.createUserByProvider(input);
        return ApiResponse.success(mapper.toUserResponse(output), "USER_CREATED");
    }

    @GetMapping("/me")
    @Operation(summary = "내 정보 조회", description = "현재 로그인한 사용자의 정보를 조회합니다.")
    public ApiResponse<UserResponse> getMyInfo(
            @AuthenticationPrincipal UserDetails userDetails) {
        Long userId = requireUserId(userDetails);
        var output = userUseCase.getUserById(userId);
        return ApiResponse.success(mapper.toUserResponse(output), "USER_FOUND");
    }

    @GetMapping("/me/detail")
    @Operation(
            summary = "내 상세 정보 조회",
            description = "현재 로그인한 사용자의 상세 정보(소셜 연동, 관심 카테고리 포함)를 조회합니다.")
    public ApiResponse<UserDetailResponse> getMyDetailInfo(
            @AuthenticationPrincipal UserDetails userDetails) {
        Long userId = requireUserId(userDetails);
        var output = userUseCase.getUserById(userId);
        return ApiResponse.success(mapper.toUserDetailResponse(output), "USER_DETAIL_FOUND");
    }

    @GetMapping("/{userId}")
    @Operation(summary = "사용자 정보 조회", description = "특정 사용자의 공개 정보를 조회합니다.")
    public ApiResponse<UserResponse> getUserById(
            @PathVariable Long userId) {
        var output = userUseCase.getUserById(userId);
        return ApiResponse.success(mapper.toUserResponse(output), "USER_FOUND");
    }

    @PutMapping("/me/profile")
    @Operation(summary = "프로필 정보 수정", description = "현재 로그인한 사용자의 프로필 정보를 수정합니다.")
    public ApiResponse<UserProfileUpdateResponse> updateMyProfile(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestBody UpdateUserProfileRequest request) {
        Long userId = requireUserId(userDetails);
        UserProfileUpdateResult result =
                userUseCase.updateUserProfile(userId, request.toInput());
        var response = mapper.toUserProfileUpdateResponse(
                result.getSuccess(),
                result.getMessage(),
                result.getUpdatedFields());
        return ApiResponse.success(response, "PROFILE_UPDATED");
    }

    @PostMapping("/phone/verification/send")
    @Operation(summary = "휴대폰 인증 코드 발송", description = "휴대폰 번호로 인증 코드를 발송합니다.")
    public ApiResponse<PhoneVerificationResponse> sendPhoneVerificationCode(
            @RequestBody SendPhoneVerificationRequest request) {
        VerificationType verificationType = request.verificationType();
        PhoneVerificationOutput result = userUseCase.sendPhoneVerificationCode(
                request.phoneNumber(),
                verificationType);
        return ApiResponse.success(
                mapper.toPhoneVerificationResponse(result), "VERIFICATION_CODE_SENT");
    }

    @PostMapping("/phone/verification/verify")
    @Operation(summary = "휴대폰 인증 코드 확인", description = "휴대폰 인증 코드를 확인합니다.")
    public ApiResponse<PhoneVerificationResultResponse> verifyPhoneCode(
            @RequestBody VerifyPhoneCodeRequest request) {
        VerificationType verificationType = request.verificationType();
        PhoneVerificationResult result = userUseCase.verifyPhoneCode(
                request.phoneNumber(),
                request.verificationCode(),
                verificationType);
        PhoneVerificationResultResponse response = mapper.toPhoneVerificationResultResponse(
                result.getSuccess(),
                result.getMessage(),
                request.phoneNumber(),
                result.getVerifiedAt());
        return ApiResponse.success(response, "VERIFICATION_COMPLETED");
    }

    @PostMapping("/social/connect")
    @Operation(summary = "소셜 계정 연동", description = "기존 계정에 소셜 플랫폼을 연동합니다.")
    public ApiResponse<SocialConnectionResultResponse> connectSocialAccount(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestBody ConnectSocialAccountRequest request) {
        Long userId = requireUserId(userDetails);
        SocialConnectionResult result =
                userUseCase.connectSocialAccount(userId, request.toInput());
        SocialConnectionResultResponse response = mapper.toSocialConnectionResultResponse(
                result.getSuccess(),
                result.getMessage(),
                result.getProvider(),
                result.getConnectedAt());
        return ApiResponse.success(response, "SOCIAL_ACCOUNT_CONNECTED");
    }

    @DeleteMapping("/social/disconnect/{provider}")
    @Operation(summary = "소셜 계정 연동 해제", description = "연동된 소셜 플랫폼을 해제합니다.")
    public ApiResponse<SocialConnectionResultResponse> disconnectSocialAccount(
            @AuthenticationPrincipal UserDetails userDetails,
            @PathVariable String provider) {
        Long userId = requireUserId(userDetails);
        SocialConnectionResult result =
                userUseCase.disconnectSocialAccount(userId, provider);
        SocialConnectionResultResponse response = mapper.toSocialConnectionResultResponse(
                result.getSuccess(),
                result.getMessage(),
                result.getProvider(),
                null);
        return ApiResponse.success(response, "SOCIAL_ACCOUNT_DISCONNECTED");
    }

    @GetMapping("/me/stats")
    @Operation(summary = "내 통계 정보 조회", description = "현재 로그인한 사용자의 통계 정보를 조회합니다.")
    public ApiResponse<UserStatsResponse> getMyStats(
            @AuthenticationPrincipal UserDetails userDetails) {
        Long userId = requireUserId(userDetails);
        var stats = userUseCase.getUserStats(userId);
        return ApiResponse.success(
                mapper.toUserStatsResponse(stats), "USER_STATS_FOUND");
    }

    @PostMapping("/2fa/setup")
    @Operation(summary = "2단계 인증 설정", description = "2단계 인증을 설정합니다.")
    public ApiResponse<TwoFactorSetupResponse> setupTwoFactorAuth(
            @AuthenticationPrincipal UserDetails userDetails) {
        Long userId = requireUserId(userDetails);
        var setup = userUseCase.setupTwoFactorAuth(userId);
        return ApiResponse.success(
                mapper.toTwoFactorSetupResponse(setup), "TWO_FACTOR_SETUP");
    }

    @PostMapping("/2fa/verify")
    @Operation(summary = "2단계 인증 확인", description = "2단계 인증 코드를 확인합니다.")
    public ApiResponse<Map<String, String>> verifyTwoFactorAuth(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestBody VerifyTwoFactorRequest request) {
        Long userId = requireUserId(userDetails);
        TwoFactorVerificationResult result =
                userUseCase.verifyTwoFactorAuth(userId, request.twoFactorCode());
        return ApiResponse.success(
                Map.of("message", result.getMessage()), "TWO_FACTOR_VERIFIED");
    }

    @DeleteMapping("/me")
    @Operation(summary = "회원 탈퇴", description = "현재 로그인한 사용자의 계정을 삭제합니다.")
    public ApiResponse<Map<String, String>> deleteMyAccount(
            @AuthenticationPrincipal UserDetails userDetails) {
        Long userId = requireUserId(userDetails);
        userUseCase.deleteUser(userId);
        return ApiResponse.success(
                Map.of("message", "회원 탈퇴가 완료되었습니다."), "USER_DELETED");
    }

    @PostMapping("/me/interests")
    @Operation(summary = "관심 카테고리 설정", description = "사용자의 관심 카테고리를 설정합니다.")
    public ApiResponse<Map<String, String>> setInterestedCategories(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestBody SetInterestedCategoriesRequest request) {
        Long userId = requireUserId(userDetails);
        userUseCase.setInterestedCategories(userId, request.categoryIds());
        return ApiResponse.success(
                Map.of("message", "관심 카테고리가 설정되었습니다."), "INTERESTS_SET");
    }

    @GetMapping("/me/interests")
    @Operation(summary = "관심 카테고리 조회", description = "사용자의 관심 카테고리를 조회합니다.")
    public ApiResponse<List<CategoryResponse>> getInterestedCategories(
            @AuthenticationPrincipal UserDetails userDetails) {
        Long userId = requireUserId(userDetails);
        List<CategoryResponse> categories =
                userUseCase.getInterestedCategories(userId).stream()
                        .map(mapper::toCategoryResponse)
                        .collect(Collectors.toList());
        return ApiResponse.success(categories, "INTERESTS_FOUND");
    }

    private Long requireUserId(UserDetails userDetails) {
        if (userDetails == null) {
            throw new AuthenticationCredentialsNotFoundException("로그인이 필요합니다.");
        }
        try {
            return Long.parseLong(userDetails.getUsername());
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException("Invalid user ID");
        }
    }
}

