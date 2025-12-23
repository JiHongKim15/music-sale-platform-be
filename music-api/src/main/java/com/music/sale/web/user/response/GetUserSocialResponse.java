package com.music.sale.web.user.response;

import com.music.sale.domain.user.enums.SocialProvider;

public record GetUserSocialResponse(SocialProvider provider, String providerId) {}
