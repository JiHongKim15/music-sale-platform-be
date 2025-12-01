package com.music.sale.domain.user;

import com.music.sale.domain.user.enums.SocialProvider;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class UserSocial {
    private final Long id;
    private final Long userId;
    private final SocialProvider provider;
    private final String providerId;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;
}
