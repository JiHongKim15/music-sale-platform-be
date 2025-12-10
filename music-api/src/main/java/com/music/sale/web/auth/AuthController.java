package com.music.sale.web.auth;

import static com.music.sale.infrastructure.common.HttpRequestUtils.getClientIP;
import static com.music.sale.infrastructure.common.HttpRequestUtils.getUserAgent;

import com.music.sale.application.auth.port.in.AuthUseCase;
import com.music.sale.common.ApiResponse;
import com.music.sale.web.auth.mapper.AuthWebMapper;
import com.music.sale.web.auth.request.RefreshTokenRequest;
import com.music.sale.web.auth.response.TokenResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Auth", description = "인증 API")
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

  private final AuthUseCase authUseCase;
  private final AuthWebMapper mapper;

  @Operation(summary = "토큰 갱신", description = "Refresh Token으로 새로운 Access Token을 발급받습니다.")
  @PostMapping("/refresh")
  public ApiResponse<TokenResponse> refreshToken(
      @RequestBody RefreshTokenRequest request, HttpServletRequest httpRequest) {
    String ip = getClientIP(httpRequest);
    String deviceInfo = getUserAgent(httpRequest);

    TokenResponse response =
        mapper.toTokenResponse(
            authUseCase.renewAccessToken(
                mapper.toRefreshTokenCommand(request.refreshToken(), ip, deviceInfo)));
    return ApiResponse.success(response);
  }

  @Operation(summary = "로그아웃", description = "Refresh Token을 무효화합니다.")
  @PostMapping("/logout")
  public ApiResponse<Void> logout(@RequestHeader("Authorization") String authHeader) {
    String refreshToken = authHeader.replace("Bearer ", "");
    authUseCase.logout(refreshToken);
    return ApiResponse.success();
  }
}
