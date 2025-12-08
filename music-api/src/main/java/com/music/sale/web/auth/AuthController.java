package com.music.sale.web.auth;

import static com.music.sale.infrastructure.common.HttpRequestUtils.getClientIP;
import static com.music.sale.infrastructure.common.HttpRequestUtils.getUserAgent;

import com.music.sale.application.auth.dto.RefreshTokenCommand;
import com.music.sale.application.auth.port.in.LogoutUseCase;
import com.music.sale.application.auth.port.in.RefreshTokenUseCase;
import com.music.sale.common.ApiResponse;
import com.music.sale.web.auth.mapper.AuthWebMapper;
import com.music.sale.web.auth.request.RefreshTokenRequest;
import com.music.sale.web.auth.response.TokenResponseDto;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

  private final RefreshTokenUseCase refreshTokenUseCase;
  private final LogoutUseCase logoutUseCase;
  private final AuthWebMapper mapper;

  @PostMapping("/refresh")
  public ApiResponse<TokenResponseDto> refreshToken(
      @RequestBody RefreshTokenRequest request, HttpServletRequest httpRequest) {

    String ip = getClientIP(httpRequest);
    String deviceInfo = getUserAgent(httpRequest);

    RefreshTokenCommand command =
        mapper.toRefreshTokenCommand(request.refreshToken(), ip, deviceInfo);

    TokenResponseDto response =
        mapper.toTokenResponseDto(refreshTokenUseCase.refreshToken(command));
    return ApiResponse.success(response);
  }

  @PostMapping("/logout")
  public ApiResponse<Void> logout(@RequestHeader("Authorization") String authHeader) {
    String token = authHeader.replace("Bearer ", "");
    logoutUseCase.logout(token);
    return ApiResponse.success();
  }
}
