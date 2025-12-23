package com.music.sale.web.user.response;

import java.time.LocalDateTime;

public record GetUserTermsResponse(
    String title, String version, Boolean isAgreed, LocalDateTime agreedAt) {}
