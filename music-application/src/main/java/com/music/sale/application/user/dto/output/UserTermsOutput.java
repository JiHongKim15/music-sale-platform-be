package com.music.sale.application.user.dto.output;

import java.time.LocalDateTime;
import lombok.Builder;

@Builder
public record UserTermsOutput(
    String title, String version, Boolean isAgreed, LocalDateTime agreedAt) {}
