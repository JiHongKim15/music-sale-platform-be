package com.music.sale.web.user.response;

import java.time.LocalDateTime;

public record PhoneVerificationResponse(
        String phoneNumber,
        String verificationType,
        LocalDateTime expiresAt,
        Integer attemptCount,
        Boolean isUsed) {}

