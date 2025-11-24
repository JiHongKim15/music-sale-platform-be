package com.music.sale.web.user.response;

import java.time.LocalDateTime;

public record GetUserStatsResponse(
        Long userId,
        Integer totalPurchases,
        Integer totalSales,
        Double rating,
        Integer reviewCount,
        LocalDateTime lastLoginAt,
        LocalDateTime joinDate) {}
