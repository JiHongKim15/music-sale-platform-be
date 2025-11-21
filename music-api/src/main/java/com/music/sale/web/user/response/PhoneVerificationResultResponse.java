package com.music.sale.web.user.response;

import java.time.LocalDateTime;

public record PhoneVerificationResultResponse(
        Boolean success,
        String message,
        String phoneNumber,
        LocalDateTime verifiedAt) {}

