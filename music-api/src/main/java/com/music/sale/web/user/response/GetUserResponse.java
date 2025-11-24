package com.music.sale.web.user.response;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record GetUserResponse(
        Long id,
        String email,
        String name,
        String nickname,
        String role,
        String phoneNumber,
        Boolean phoneVerified,
        LocalDate birthDate,
        String gender,
        String zipcode,
        String baseAddress,
        String detailAddress,
        String profileImageUrl,
        String bio,
        String userType,
        Integer preferredPriceRangeMin,
        Integer preferredPriceRangeMax,
        Integer totalPurchases,
        Integer totalSales,
        Double rating,
        Integer reviewCount,
        Boolean isActive,
        Boolean isVerified,
        LocalDateTime lastLoginAt,
        Boolean marketingAgreed,
        LocalDateTime createdAt,
        LocalDateTime updatedAt) {}
