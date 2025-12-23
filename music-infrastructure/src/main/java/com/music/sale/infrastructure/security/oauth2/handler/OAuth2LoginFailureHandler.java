package com.music.sale.infrastructure.security.oauth2.handler;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

@Slf4j
@Component
@ConditionalOnProperty(
    prefix = "spring.security.oauth2.client.registration.google",
    name = "client-id")
public class OAuth2LoginFailureHandler extends SimpleUrlAuthenticationFailureHandler {

  @Value("${oauth2.redirect-uri}")
  private String redirectUri;

  @Override
  public void onAuthenticationFailure(
      HttpServletRequest request, HttpServletResponse response, AuthenticationException exception)
      throws IOException {

    log.error("OAuth2 authentication failed", exception);

    String targetUrl =
        UriComponentsBuilder.fromUriString(redirectUri)
            .queryParam("error", "OAuth2 authentication failed")
            .build()
            .toUriString();

    getRedirectStrategy().sendRedirect(request, response, targetUrl);
  }
}
