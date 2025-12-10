package com.music.sale.infrastructure.security.jwt;

import java.time.Duration;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {

  private String secret;
  private Long accessTokenValidity;
  private Long refreshTokenValidity;

  public Duration getAccessTokenDuration() {
    return Duration.ofMillis(accessTokenValidity);
  }

  public Duration getRefreshTokenDuration() {
    return Duration.ofMillis(refreshTokenValidity);
  }
}
