package com.music.sale.api.auth;

import com.music.sale.api.auth.dto.RefreshTokenRequest;
import com.music.sale.application.auth.dto.RefreshTokenCommand;
import com.music.sale.application.auth.dto.TokenResponse;
import com.music.sale.application.auth.dto.UserInfo;
import com.music.sale.application.auth.port.in.GetUserUseCase;
import com.music.sale.application.auth.port.in.LogoutUseCase;
import com.music.sale.application.auth.port.in.RefreshTokenUseCase;
import com.music.sale.common.ApiResponse;
import com.music.sale.infrastructure.security.AuthUser;
import com.music.sale.infrastructure.security.LoginUser;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

  private final RefreshTokenUseCase refreshTokenUseCase;
  private final LogoutUseCase logoutUseCase;
  private final GetUserUseCase getUserUseCase;

  @PostMapping("/refresh")
  public ApiResponse<TokenResponse> refreshToken(
      @RequestBody RefreshTokenRequest request, HttpServletRequest httpRequest) {

    String ip = getClientIP(httpRequest);
    String deviceInfo = getUserAgent(httpRequest);

    RefreshTokenCommand command = RefreshTokenCommand.of(request.refreshToken(), ip, deviceInfo);

    TokenResponse response = refreshTokenUseCase.refreshToken(command);
    return ApiResponse.success(response);
  }

  @PostMapping("/logout")
  public ApiResponse<Void> logout(@RequestHeader("Authorization") String authHeader) {
    String token = authHeader.replace("Bearer ", "");
    logoutUseCase.logout(token);
    return ApiResponse.success();
  }

  @GetMapping("/me")
  public ApiResponse<UserInfo> getCurrentUser(@AuthUser LoginUser loginUser) {
    UserInfo userInfo = getUserUseCase.getUserInfo(loginUser.getUserId());
    return ApiResponse.success(userInfo);
  }

  private String getClientIP(HttpServletRequest request) {
    String ip = request.getHeader("X-Forwarded-For");
    if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
      ip = request.getHeader("Proxy-Client-IP");
    }
    if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
      ip = request.getHeader("WL-Proxy-Client-IP");
    }
    if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
      ip = request.getHeader("HTTP_CLIENT_IP");
    }
    if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
      ip = request.getHeader("HTTP_X_FORWARDED_FOR");
    }
    if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
      ip = request.getRemoteAddr();
    }
    return ip;
  }

  private String getUserAgent(HttpServletRequest request) {
    return request.getHeader("User-Agent");
  }
}
