package com.music.sale.domain.user;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class UserTerms {
    private final Long id;
    private final Long userId;
    private final String title;
    private final String version;
    private final Boolean isAgreed;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public static UserTerms of(Long userId, String title, String version, Boolean isAgreed) {
        return UserTerms.builder()
                .userId(userId)
                .title(title)
                .version(version)
                .isAgreed(isAgreed)
                .build();
    }
}
