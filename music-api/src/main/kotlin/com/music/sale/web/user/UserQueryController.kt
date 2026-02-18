package com.music.sale.web.user

import com.music.sale.application.auth.security.AuthenticatedUser
import com.music.sale.application.auth.security.CurrentUser
import com.music.sale.application.user.port.inport.UserQueryUseCase
import com.music.sale.common.ApiResponse
import com.music.sale.web.user.mapper.UserWebMapper
import com.music.sale.web.user.response.GetUserResponse
import com.music.sale.web.user.response.GetUserSocialResponse
import com.music.sale.web.user.response.GetUserTermsResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import io.swagger.v3.oas.annotations.responses.ApiResponse as SwaggerApiResponse

@Tag(name = "User", description = "사용자 정보 API")
@RestController
@RequestMapping("/api/v1/users")
class UserQueryController(
    private val userQueryUseCase: UserQueryUseCase,
    private val mapper: UserWebMapper,
) {
    @Operation(summary = "내 정보 조회", description = "현재 로그인한 사용자의 정보를 조회합니다.")
    @ApiResponses(
        SwaggerApiResponse(responseCode = "200", description = "조회 성공"),
        SwaggerApiResponse(responseCode = "401", description = "인증 실패"),
    )
    @GetMapping("/me")
    fun getCurrentUser(
        @Parameter(hidden = true) @CurrentUser user: AuthenticatedUser,
    ): ApiResponse<GetUserResponse> {
        val response = mapper.toGetUserResponse(userQueryUseCase.getUserById(user.userId()))
        return ApiResponse.success(response)
    }

    @Operation(summary = "내 소셜 연동 정보 조회", description = "현재 로그인한 사용자의 소셜 연동 정보를 조회합니다.")
    @GetMapping("/me/socials")
    fun getMySocials(
        @Parameter(hidden = true) @CurrentUser user: AuthenticatedUser,
    ): ApiResponse<List<GetUserSocialResponse>> {
        val response = mapper.toGetUserSocialResponses(userQueryUseCase.getSocialsByUserId(user.userId()))
        return ApiResponse.success(response)
    }

    @Operation(summary = "내 약관 동의 정보 조회", description = "현재 로그인한 사용자의 약관 동의 정보를 조회합니다.")
    @GetMapping("/me/terms")
    fun getMyTerms(
        @Parameter(hidden = true) @CurrentUser user: AuthenticatedUser,
    ): ApiResponse<List<GetUserTermsResponse>> {
        val response = mapper.toGetUserTermsResponses(userQueryUseCase.getTermsByUserId(user.userId()))
        return ApiResponse.success(response)
    }
}
