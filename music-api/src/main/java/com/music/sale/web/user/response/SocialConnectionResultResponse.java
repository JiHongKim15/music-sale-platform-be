package com.music.sale.web.user.response;

import java.time.LocalDateTime;

public record SocialConnectionResultResponse(
        Boolean success,
        String message,
        String provider,
        LocalDateTime connectedAt) {}

