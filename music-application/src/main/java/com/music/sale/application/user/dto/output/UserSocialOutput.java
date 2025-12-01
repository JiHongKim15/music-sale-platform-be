package com.music.sale.application.user.dto.output;

import com.music.sale.domain.user.enums.SocialProvider;
import lombok.Builder;

@Builder
public record UserSocialOutput(
        SocialProvider provider,
        String providerId
) {
}
