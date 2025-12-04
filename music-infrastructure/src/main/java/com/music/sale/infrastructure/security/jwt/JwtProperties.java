package com.music.sale.infrastructure.security.jwt;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {
  private String secret;
  private Long accessTokenValidity; // milliseconds (1시간 = 3600000)
  private Long refreshTokenValidity; // milliseconds (14일 = 1209600000)
}
