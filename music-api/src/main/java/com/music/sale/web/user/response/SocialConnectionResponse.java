package com.music.sale.web.user.response;

import java.time.LocalDateTime;

public record SocialConnectionResponse(
        Long id,
        String provider,
        String providerId,
        String providerEmail,
        String providerName,
        String providerProfileImage,
        LocalDateTime connectedAt,
        LocalDateTime lastUsedAt,
        Boolean isActive) {}

