package com.music.sale.application.auth.service;

import com.music.sale.application.auth.dto.RefreshTokenCommand;
import com.music.sale.application.auth.dto.SocialLoginCommand;
import com.music.sale.application.auth.dto.TokenResponse;
import com.music.sale.application.auth.port.in.AuthUseCase;
import com.music.sale.application.auth.port.out.JwtTokenPort;
import com.music.sale.application.auth.port.out.RefreshTokenPort;
import com.music.sale.application.user.dto.input.CreateSocialUserInput;
import com.music.sale.application.user.port.inport.UserCommandUseCase;
import com.music.sale.application.user.port.inport.UserQueryUseCase;
import com.music.sale.common.BusinessException;
import com.music.sale.common.ErrorDefinition;
import com.music.sale.domain.user.User;
import java.time.Duration;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthService implements AuthUseCase {

  private static final Duration REFRESH_TOKEN_RENEWAL_THRESHOLD = Duration.ofDays(3);

  private final UserQueryUseCase userQueryUseCase;
  private final UserCommandUseCase userCommandUseCase;
  private final JwtTokenPort jwtTokenPort;
  private final RefreshTokenPort refreshTokenPort;

  @Override
  public TokenResponse loginWithSocialAccount(
      SocialLoginCommand command, String ip, String deviceInfo) {
    User user =
        userQueryUseCase
            .findBySocialAccount(command.provider(), command.providerId())
            .orElseGet(() -> createNewSocialUser(command));

    user.validateActive();

    String accessToken = jwtTokenPort.generateAccessToken(user);
    String refreshToken = jwtTokenPort.generateRefreshToken(user);

    refreshTokenPort.saveRefreshToken(refreshToken, user.getId(), ip, deviceInfo);

    return TokenResponse.of(accessToken, refreshToken, jwtTokenPort.getAccessTokenValidity());
  }

  @Override
  public TokenResponse renewAccessToken(RefreshTokenCommand command) {
    Long userId = findUserIdByRefreshToken(command.refreshToken());
    User user = findActiveUserById(userId);

    String newAccessToken = jwtTokenPort.generateAccessToken(user);

    if (shouldRenewRefreshToken(command.refreshToken())) {
      return renewBothTokens(user, command, newAccessToken);
    }

    return TokenResponse.of(
        newAccessToken, command.refreshToken(), jwtTokenPort.getAccessTokenValidity());
  }

  @Override
  public void logout(String refreshToken) {
    refreshTokenPort.deleteRefreshToken(refreshToken);
  }

  private User createNewSocialUser(SocialLoginCommand command) {
    CreateSocialUserInput input =
        CreateSocialUserInput.of(
            command.provider(),
            command.providerId(),
            command.email(),
            command.nickname(),
            command.profileImageUrl());

    return userCommandUseCase.createSocialUser(input);
  }

  private Long findUserIdByRefreshToken(String refreshToken) {
    return refreshTokenPort
        .getUserIdByRefreshToken(refreshToken)
        .orElseThrow(() -> new BusinessException(AuthErrorCode.INVALID_REFRESH_TOKEN));
  }

  private User findActiveUserById(Long userId) {
    User user =
        userQueryUseCase
            .findById(userId)
            .orElseThrow(() -> new BusinessException(AuthErrorCode.USER_NOT_FOUND));

    user.validateActive();
    return user;
  }

  private boolean shouldRenewRefreshToken(String refreshToken) {
    Long remainingTimeMillis = refreshTokenPort.getRefreshTokenTTL(refreshToken);
    return remainingTimeMillis != null
        && remainingTimeMillis < REFRESH_TOKEN_RENEWAL_THRESHOLD.toMillis();
  }

  private TokenResponse renewBothTokens(
      User user, RefreshTokenCommand command, String newAccessToken) {
    String newRefreshToken = jwtTokenPort.generateRefreshToken(user);

    refreshTokenPort.deleteRefreshToken(command.refreshToken());
    refreshTokenPort.saveRefreshToken(
        newRefreshToken, user.getId(), command.ip(), command.deviceInfo());

    return TokenResponse.of(newAccessToken, newRefreshToken, jwtTokenPort.getAccessTokenValidity());
  }

  private enum AuthErrorCode implements ErrorDefinition {
    USER_NOT_FOUND("error.USER_NOT_FOUND"),
    INVALID_REFRESH_TOKEN("error.INVALID_REFRESH_TOKEN");

    private final String key;

    AuthErrorCode(String key) {
      this.key = key;
    }

    @Override
    public String getKey() {
      return key;
    }
  }
}
