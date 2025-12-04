package com.music.sale.config;

import com.music.sale.infrastructure.security.jwt.JwtAuthenticationFilter;
import com.music.sale.infrastructure.security.oauth2.CustomOAuth2UserService;
import com.music.sale.infrastructure.security.oauth2.OAuth2FailureHandler;
import com.music.sale.infrastructure.security.oauth2.OAuth2SuccessHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

  private final JwtAuthenticationFilter jwtAuthenticationFilter;
  private final CustomOAuth2UserService customOAuth2UserService;
  private final OAuth2SuccessHandler oAuth2SuccessHandler;
  private final OAuth2FailureHandler oAuth2FailureHandler;

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
                        "/v3/api-docs/**", // API 문서
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

        // OAuth2 로그인 설정
        .oauth2Login(
            oauth2 ->
                oauth2
                    .userInfoEndpoint(userInfo -> userInfo.userService(customOAuth2UserService))
                    .successHandler(oAuth2SuccessHandler)
                    .failureHandler(oAuth2FailureHandler))

        // JWT 필터 추가
        .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

    return http.build();
  }
}
