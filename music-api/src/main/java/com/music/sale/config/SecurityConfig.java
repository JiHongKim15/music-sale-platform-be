package com.music.sale.config;

import com.music.sale.infrastructure.security.jwt.JwtAuthenticationFilter;
import com.music.sale.infrastructure.security.oauth2.OAuth2UserLoader;
import com.music.sale.infrastructure.security.oauth2.handler.OAuth2LoginFailureHandler;
import com.music.sale.infrastructure.security.oauth2.handler.OAuth2LoginSuccessHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

  private final JwtAuthenticationFilter jwtAuthenticationFilter;

  @Autowired(required = false)
  private ClientRegistrationRepository clientRegistrationRepository;

  @Autowired(required = false)
  private OAuth2UserLoader oAuth2UserLoader;

  @Autowired(required = false)
  private OAuth2LoginSuccessHandler oAuth2LoginSuccessHandler;

  @Autowired(required = false)
  private OAuth2LoginFailureHandler oAuth2LoginFailureHandler;

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
        // CSRF 비활성화 (JWT 사용)
        .csrf(AbstractHttpConfigurer::disable)

        // CORS 설정
        .cors(cors -> cors.configure(http))

        // 세션 사용 안 함 (JWT 사용)
        .sessionManagement(
            session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

        // 요청 권한 설정
        .authorizeHttpRequests(
            auth ->
                auth
                    // Public endpoints
                    .requestMatchers(
                        "/swagger-ui/**", // Swagger UI
                        "/swagger-auth.html", // OAuth2 인증 후 리다이렉트 페이지
                        "/v3/api-docs/**", // API 문서
                        "/api-docs/**", // API 문서 (커스텀 경로)
                        "/swagger-resources/**", // Swagger 리소스
                        "/webjars/**", // Webjars
                        "/h2-console/**", // H2 콘솔
                        "/actuator/**", // Actuator
                        "/api/v1/auth/**",
                        "/api/v1/products/**",
                        "/api/v1/health",
                        "/error")
                    .permitAll()
                    // OAuth2 endpoints
                    .requestMatchers("/oauth2/**", "/login/oauth2/**")
                    .permitAll()
                    // Authenticated endpoints
                    .anyRequest()
                    .authenticated())

        // JWT 필터 추가
        .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

    // OAuth2 로그인 설정 (OAuth2 클라이언트가 등록되어 있을 때만 활성화)
    if (clientRegistrationRepository != null && oAuth2UserLoader != null) {
      http.oauth2Login(
          oauth2 ->
              oauth2
                  .userInfoEndpoint(userInfo -> userInfo.userService(oAuth2UserLoader))
                  .successHandler(oAuth2LoginSuccessHandler)
                  .failureHandler(oAuth2LoginFailureHandler));
    }

    return http.build();
  }
}
