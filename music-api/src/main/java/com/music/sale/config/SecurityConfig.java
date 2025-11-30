package com.music.sale.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(AbstractHttpConfigurer::disable)  // CSRF 비활성화 (개발 환경)
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(
                    "/swagger-ui/**",           // Swagger UI
                    "/v3/api-docs/**",          // API 문서
                    "/swagger-resources/**",    // Swagger 리소스
                    "/webjars/**",              // Webjars
                    "/h2-console/**",           // H2 콘솔
                    "/actuator/**",             // Actuator
                    "/api/**"                   // 모든 API (임시로 열어둠)
                ).permitAll()
                .anyRequest().authenticated()   // 나머지는 인증 필요
            );

        return http.build();
    }
}
