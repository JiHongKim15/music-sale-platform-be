package com.music.sale.application.auth.service;

import com.music.sale.application.auth.dto.RefreshTokenCommand;
import com.music.sale.application.auth.dto.SocialLoginCommand;
import com.music.sale.application.auth.dto.TokenResponse;
import com.music.sale.application.auth.dto.UserInfo;
import com.music.sale.application.auth.port.in.GetUserUseCase;
import com.music.sale.application.auth.port.in.LogoutUseCase;
import com.music.sale.application.auth.port.in.RefreshTokenUseCase;
import com.music.sale.application.auth.port.in.SocialLoginUseCase;
import com.music.sale.application.auth.port.out.JwtTokenPort;
import com.music.sale.application.auth.port.out.LoadSocialAccountPort;
import com.music.sale.application.auth.port.out.LoadUserPort;
import com.music.sale.application.auth.port.out.RefreshTokenPort;
import com.music.sale.application.auth.port.out.SaveSocialAccountPort;
import com.music.sale.application.auth.port.out.SaveUserPort;
import com.music.sale.common.BusinessException;
import com.music.sale.common.ErrorDefinition;
import com.music.sale.domain.user.User;
import com.music.sale.domain.user.enums.UserRole;
import com.music.sale.domain.user.enums.UserStatus;
import java.util.concurrent.TimeUnit;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthService
    implements SocialLoginUseCase, RefreshTokenUseCase, LogoutUseCase, GetUserUseCase {

  private final LoadUserPort loadUserPort;
  private final SaveUserPort saveUserPort;
  private final LoadSocialAccountPort loadSocialAccountPort;
  private final SaveSocialAccountPort saveSocialAccountPort;
  private final JwtTokenPort jwtTokenPort;
  private final RefreshTokenPort refreshTokenPort;

  private static final long REFRESH_TOKEN_RENEWAL_THRESHOLD_DAYS = 3;

  @Override
  public TokenResponse socialLogin(SocialLoginCommand command, String ip, String deviceInfo) {
    // 1. 소셜 계정으로 사용자 조회
    User user =
        loadSocialAccountPort
            .loadUserBySocialAccount(command.provider(), command.providerId())
            .orElseGet(() -> createNewUser(command));

    // 2. 사용자 상태 확인
    if (!user.isActive()) {
      throw new BusinessException(AuthErrorCode.USER_NOT_ACTIVE);
    }

    // 3. JWT 토큰 생성
    String accessToken = jwtTokenPort.generateAccessToken(user);
    String refreshToken = jwtTokenPort.generateRefreshToken(user);

    // 4. RefreshToken Redis 저장
    refreshTokenPort.saveRefreshToken(refreshToken, user.getId(), ip, deviceInfo);

    return TokenResponse.of(accessToken, refreshToken, jwtTokenPort.getAccessTokenValidity());
  }

  private User createNewUser(SocialLoginCommand command) {
    // 신규 사용자 생성
    User newUser =
        User.builder()
            .email(command.email())
            .nickname(command.nickname())
            .profileImageUrl(command.profileImageUrl())
            .role(UserRole.USER)
            .status(UserStatus.ACTIVE)
            .isVerified(false)
            .build();

    User savedUser = saveUserPort.saveUser(newUser);

    // 소셜 계정 연결
    saveSocialAccountPort.saveSocialAccount(
        savedUser.getId(), command.provider(), command.providerId());

    return savedUser;
  }

  @Override
  public TokenResponse refreshToken(RefreshTokenCommand command) {
    // 1. RefreshToken으로 userId 조회
    Long userId =
        refreshTokenPort
            .getUserIdByRefreshToken(command.refreshToken())
            .orElseThrow(() -> new BusinessException(AuthErrorCode.INVALID_REFRESH_TOKEN));

    // 2. 사용자 조회
    User user =
        loadUserPort
            .loadUserById(userId)
            .orElseThrow(() -> new BusinessException(AuthErrorCode.USER_NOT_FOUND));

    // 3. 사용자 상태 확인
    if (!user.isActive()) {
      throw new BusinessException(AuthErrorCode.USER_NOT_ACTIVE);
    }

    // 4. 새 AccessToken 생성
    String newAccessToken = jwtTokenPort.generateAccessToken(user);

    // 5. Sliding Session: RefreshToken 남은 기간 확인
    Long ttl = refreshTokenPort.getRefreshTokenTTL(command.refreshToken());
    Long thresholdMillis = TimeUnit.DAYS.toMillis(REFRESH_TOKEN_RENEWAL_THRESHOLD_DAYS);

    if (ttl != null && ttl < thresholdMillis) {
      // 남은 기간이 3일 미만이면 RefreshToken도 갱신
      String newRefreshToken = jwtTokenPort.generateRefreshToken(user);
      refreshTokenPort.deleteRefreshToken(command.refreshToken());
      refreshTokenPort.saveRefreshToken(
          newRefreshToken, user.getId(), command.ip(), command.deviceInfo());

      return TokenResponse.of(
          newAccessToken, newRefreshToken, jwtTokenPort.getAccessTokenValidity());
    }

    // RefreshToken은 그대로 유지
    return TokenResponse.of(
        newAccessToken, command.refreshToken(), jwtTokenPort.getAccessTokenValidity());
  }

  @Override
  public void logout(String refreshToken) {
    refreshTokenPort.deleteRefreshToken(refreshToken);
  }

  @Override
  @Transactional(readOnly = true)
  public UserInfo getUserInfo(Long userId) {
    User user =
        loadUserPort
            .loadUserById(userId)
            .orElseThrow(() -> new BusinessException(AuthErrorCode.USER_NOT_FOUND));

    return UserInfo.of(user.getId(), user.getEmail(), user.getNickname(), user.getRole());
  }

  // 에러 코드 정의
  private enum AuthErrorCode implements ErrorDefinition {
    USER_NOT_FOUND("error.USER_NOT_FOUND"),
    USER_NOT_ACTIVE("error.USER_NOT_ACTIVE"),
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
