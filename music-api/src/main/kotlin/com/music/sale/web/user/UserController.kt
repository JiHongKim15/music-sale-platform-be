package com.music.sale.web.user

import com.music.sale.application.user.port.inport.UserUseCase
import com.music.sale.common.ApiResponse
import com.music.sale.web.user.mapper.UserWebMapper
import com.music.sale.web.user.request.*
import com.music.sale.web.user.response.*
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/users")
@Tag(name = "User", description = "사용자 관리 API")
class UserController(
    private val userUseCase: UserUseCase,
    private val mapper: UserWebMapper,
) {
    @PostMapping("/register/email")
    @Operation(summary = "이메일로 회원가입", description = "이메일과 비밀번호로 새로운 사용자를 등록합니다.")
    fun createUserByEmail(
        @RequestBody request: CreateUserByEmailRequest,
    ): ResponseEntity<ApiResponse<UserResponse>> {
        val input = mapper.toCreateUserByEmailInput(request)
        val output = userUseCase.createUserByEmail(input)
        val response = mapper.toUserResponse(output)

        return ResponseEntity.ok(
            ApiResponse.success(response),
        )
    }

    @PostMapping("/register/phone")
    @Operation(summary = "휴대폰번호로 회원가입", description = "휴대폰번호와 비밀번호로 새로운 사용자를 등록합니다.")
    fun createUserByPhone(
        @RequestBody request: CreateUserByPhoneRequest,
    ): ResponseEntity<ApiResponse<UserResponse>> {
        val input = mapper.toCreateUserByPhoneInput(request)
        val output = userUseCase.createUserByPhone(input)
        val response = mapper.toUserResponse(output)

        return ResponseEntity.ok(
            ApiResponse.success(response),
        )
    }

    @PostMapping("/register/social")
    @Operation(summary = "소셜 로그인으로 회원가입", description = "소셜 플랫폼을 통해 새로운 사용자를 등록합니다.")
    fun createUserByProvider(
        @RequestBody request: CreateUserByProviderRequest,
    ): ResponseEntity<ApiResponse<UserResponse>> {
        val input = mapper.toCreateUserByProviderInput(request)
        val output = userUseCase.createUserByProvider(input)
        val response = mapper.toUserResponse(output)

        return ResponseEntity.ok(
            ApiResponse.success(response),
        )
    }

    @GetMapping("/me")
    @Operation(summary = "내 정보 조회", description = "현재 로그인한 사용자의 정보를 조회합니다.")
    fun getMyInfo(
        @AuthenticationPrincipal userDetails: UserDetails?,
    ): ResponseEntity<ApiResponse<UserResponse>> {
        if (userDetails == null) {
            throw org.springframework.security.authentication
                .AuthenticationCredentialsNotFoundException("로그인이 필요합니다.")
        }
        val userId =
            userDetails.username.toLongOrNull()
                ?: throw IllegalArgumentException("Invalid user ID")
        val output = userUseCase.getUserById(userId)
        val response = mapper.toUserResponse(output)

        return ResponseEntity.ok(
            ApiResponse.success(response),
        )
    }

    @GetMapping("/me/detail")
    @Operation(
        summary = "내 상세 정보 조회",
        description = "현재 로그인한 사용자의 상세 정보(소셜 연동, 관심 카테고리 포함)를 조회합니다.",
    )
    fun getMyDetailInfo(
        @AuthenticationPrincipal userDetails: UserDetails?,
    ): ResponseEntity<ApiResponse<UserDetailResponse>> {
        if (userDetails == null) {
            throw org.springframework.security.authentication
                .AuthenticationCredentialsNotFoundException("로그인이 필요합니다.")
        }
        val userId =
            userDetails.username.toLongOrNull()
                ?: throw IllegalArgumentException("Invalid user ID")
        val output = userUseCase.getUserById(userId)
        val response = mapper.toUserDetailResponse(output)

        return ResponseEntity.ok(
            ApiResponse.success(response),
        )
    }

    @GetMapping("/{userId}")
    @Operation(summary = "사용자 정보 조회", description = "특정 사용자의 공개 정보를 조회합니다.")
    fun getUserById(
        @PathVariable userId: Long,
    ): ResponseEntity<ApiResponse<UserResponse>> {
        val output = userUseCase.getUserById(userId)
        val response = mapper.toUserResponse(output)

        return ResponseEntity.ok(
            ApiResponse.success(response),
        )
    }

    @PutMapping("/me/profile")
    @Operation(summary = "프로필 정보 수정", description = "현재 로그인한 사용자의 프로필 정보를 수정합니다.")
    fun updateMyProfile(
        @AuthenticationPrincipal userDetails: UserDetails?,
        @RequestBody request: UpdateUserProfileRequest,
    ): ResponseEntity<ApiResponse<UserProfileUpdateResponse>> {
        if (userDetails == null) {
            throw org.springframework.security.authentication
                .AuthenticationCredentialsNotFoundException("로그인이 필요합니다.")
        }
        val userId =
            userDetails.username.toLongOrNull()
                ?: throw IllegalArgumentException("Invalid user ID")
        val result = userUseCase.updateUserProfile(userId, request.toInput())
        val response =
            mapper.toUserProfileUpdateResponse(
                success = result.success,
                message = result.message,
                updatedFields = result.updatedFields,
            )

        return ResponseEntity.ok(
            ApiResponse.success(response),
        )
    }

    @PostMapping("/phone/verification/send")
    @Operation(summary = "휴대폰 인증 코드 발송", description = "휴대폰 번호로 인증 코드를 발송합니다.")
    fun sendPhoneVerificationCode(
        @RequestBody request: SendPhoneVerificationRequest,
    ): ResponseEntity<ApiResponse<PhoneVerificationResponse>> {
        val result =
            userUseCase.sendPhoneVerificationCode(request.phoneNumber, request.verificationType)
        val response = mapper.toPhoneVerificationResponse(result)

        return ResponseEntity.ok(
            ApiResponse.success(response),
        )
    }

    @PostMapping("/phone/verification/verify")
    @Operation(summary = "휴대폰 인증 코드 확인", description = "휴대폰 인증 코드를 확인합니다.")
    fun verifyPhoneCode(
        @RequestBody request: VerifyPhoneCodeRequest,
    ): ResponseEntity<ApiResponse<PhoneVerificationResultResponse>> {
        val result =
            userUseCase.verifyPhoneCode(
                request.phoneNumber,
                request.verificationCode,
                request.verificationType,
            )
        val response =
            mapper.toPhoneVerificationResultResponse(
                success = result.success,
                message = result.message,
                phoneNumber = request.phoneNumber,
                verifiedAt = result.verifiedAt,
            )

        return ResponseEntity.ok(
            ApiResponse.success(response),
        )
    }

    @PostMapping("/social/connect")
    @Operation(summary = "소셜 계정 연동", description = "기존 계정에 소셜 플랫폼을 연동합니다.")
    fun connectSocialAccount(
        @AuthenticationPrincipal userDetails: UserDetails?,
        @RequestBody request: ConnectSocialAccountRequest,
    ): ResponseEntity<ApiResponse<SocialConnectionResultResponse>> {
        if (userDetails == null) {
            throw org.springframework.security.authentication
                .AuthenticationCredentialsNotFoundException("로그인이 필요합니다.")
        }
        val userId =
            userDetails.username.toLongOrNull()
                ?: throw IllegalArgumentException("Invalid user ID")
        val result = userUseCase.connectSocialAccount(userId, request.toInput())
        val response =
            mapper.toSocialConnectionResultResponse(
                success = result.success,
                message = result.message,
                provider = result.provider,
                connectedAt = result.connectedAt,
            )

        return ResponseEntity.ok(
            ApiResponse.success(response),
        )
    }

    @DeleteMapping("/social/disconnect/{provider}")
    @Operation(summary = "소셜 계정 연동 해제", description = "연동된 소셜 플랫폼을 해제합니다.")
    fun disconnectSocialAccount(
        @AuthenticationPrincipal userDetails: UserDetails?,
        @PathVariable provider: String,
    ): ResponseEntity<ApiResponse<SocialConnectionResultResponse>> {
        if (userDetails == null) {
            throw org.springframework.security.authentication
                .AuthenticationCredentialsNotFoundException("로그인이 필요합니다.")
        }
        val userId =
            userDetails.username.toLongOrNull()
                ?: throw IllegalArgumentException("Invalid user ID")
        val result = userUseCase.disconnectSocialAccount(userId, provider)
        val response =
            mapper.toSocialConnectionResultResponse(
                success = result.success,
                message = result.message,
                provider = result.provider,
                connectedAt = null,
            )

        return ResponseEntity.ok(
            ApiResponse.success(response),
        )
    }

    @GetMapping("/me/stats")
    @Operation(summary = "내 통계 정보 조회", description = "현재 로그인한 사용자의 통계 정보를 조회합니다.")
    fun getMyStats(
        @AuthenticationPrincipal userDetails: UserDetails?,
    ): ResponseEntity<ApiResponse<UserStatsResponse>> {
        if (userDetails == null) {
            throw org.springframework.security.authentication
                .AuthenticationCredentialsNotFoundException("로그인이 필요합니다.")
        }
        val userId =
            userDetails.username.toLongOrNull()
                ?: throw IllegalArgumentException("Invalid user ID")
        val stats = userUseCase.getUserStats(userId)
        val response = mapper.toUserStatsResponse(stats)

        return ResponseEntity.ok(
            ApiResponse.success(response),
        )
    }

    @PostMapping("/2fa/setup")
    @Operation(summary = "2단계 인증 설정", description = "2단계 인증을 설정합니다.")
    fun setupTwoFactorAuth(
        @AuthenticationPrincipal userDetails: UserDetails?,
    ): ResponseEntity<ApiResponse<TwoFactorSetupResponse>> {
        if (userDetails == null) {
            throw org.springframework.security.authentication
                .AuthenticationCredentialsNotFoundException("로그인이 필요합니다.")
        }
        val userId =
            userDetails.username.toLongOrNull()
                ?: throw IllegalArgumentException("Invalid user ID")
        val setup = userUseCase.setupTwoFactorAuth(userId)
        val response = mapper.toTwoFactorSetupResponse(setup)

        return ResponseEntity.ok(
            ApiResponse.success(response),
        )
    }

    @PostMapping("/2fa/verify")
    @Operation(summary = "2단계 인증 확인", description = "2단계 인증 코드를 확인합니다.")
    fun verifyTwoFactorAuth(
        @AuthenticationPrincipal userDetails: UserDetails?,
        @RequestBody request: VerifyTwoFactorRequest,
    ): ResponseEntity<ApiResponse<Map<String, String>>> {
        if (userDetails == null) {
            throw org.springframework.security.authentication
                .AuthenticationCredentialsNotFoundException("로그인이 필요합니다.")
        }
        val userId =
            userDetails.username.toLongOrNull()
                ?: throw IllegalArgumentException("Invalid user ID")
        val result = userUseCase.verifyTwoFactorAuth(userId, request.twoFactorCode)

        return ResponseEntity.ok(
            ApiResponse.success(mapOf("message" to result.message)),
        )
    }

    @DeleteMapping("/me")
    @Operation(summary = "회원 탈퇴", description = "현재 로그인한 사용자의 계정을 삭제합니다.")
    fun deleteMyAccount(
        @AuthenticationPrincipal userDetails: UserDetails?,
    ): ResponseEntity<ApiResponse<Map<String, String>>> {
        if (userDetails == null) {
            throw org.springframework.security.authentication
                .AuthenticationCredentialsNotFoundException("로그인이 필요합니다.")
        }
        val userId =
            userDetails.username.toLongOrNull()
                ?: throw IllegalArgumentException("Invalid user ID")
        userUseCase.deleteUser(userId)

        return ResponseEntity.ok(
            ApiResponse.success(mapOf("message" to "회원 탈퇴가 완료되었습니다.")),
        )
    }

    @PostMapping("/me/interests")
    @Operation(summary = "관심 카테고리 설정", description = "사용자의 관심 카테고리를 설정합니다.")
    fun setInterestedCategories(
        @AuthenticationPrincipal userDetails: UserDetails?,
        @RequestBody request: SetInterestedCategoriesRequest,
    ): ResponseEntity<ApiResponse<Map<String, String>>> {
        if (userDetails == null) {
            throw org.springframework.security.authentication
                .AuthenticationCredentialsNotFoundException("로그인이 필요합니다.")
        }
        val userId =
            userDetails.username.toLongOrNull()
                ?: throw IllegalArgumentException("Invalid user ID")
        userUseCase.setInterestedCategories(userId, request.categoryIds)

        return ResponseEntity.ok(
            ApiResponse.success(mapOf("message" to "관심 카테고리가 설정되었습니다.")),
        )
    }

    @GetMapping("/me/interests")
    @Operation(summary = "관심 카테고리 조회", description = "사용자의 관심 카테고리를 조회합니다.")
    fun getInterestedCategories(
        @AuthenticationPrincipal userDetails: UserDetails?,
    ): ResponseEntity<ApiResponse<List<CategoryResponse>>> {
        if (userDetails == null) {
            throw org.springframework.security.authentication
                .AuthenticationCredentialsNotFoundException("로그인이 필요합니다.")
        }
        val userId =
            userDetails.username.toLongOrNull()
                ?: throw IllegalArgumentException("Invalid user ID")
        val categories = userUseCase.getInterestedCategories(userId)
        val response = categories.map { mapper.toCategoryResponse(it) }

        return ResponseEntity.ok(
            ApiResponse.success(response),
        )
    }
}
