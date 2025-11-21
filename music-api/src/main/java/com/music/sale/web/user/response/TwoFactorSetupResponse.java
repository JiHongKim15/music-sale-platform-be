package com.music.sale.web.user.response;

import java.util.List;

public record TwoFactorSetupResponse(
        Boolean enabled,
        String secret,
        String qrCodeUrl,
        List<String> backupCodes) {}

