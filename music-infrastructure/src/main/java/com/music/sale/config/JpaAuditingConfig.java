package com.music.sale.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class JpaAuditingConfig {
    @Bean
    public AuditorAware<Long> auditorAware() {
        return () -> Optional.of(-1L); // TODO: 추후 인증 붙이면 SecurityContext에서 실제 userId 읽도록 수정
    }
}
