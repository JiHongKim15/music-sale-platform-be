// Copyright (C) 2024 Your Name or Company
package com.music.sale.domain.user;

import com.music.sale.domain.user.enums.UserRole;
import com.music.sale.domain.user.enums.UserType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Value;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Getter
@AllArgsConstructor
public class User {
    private Long id;
    private String email;
    private String provider;
    private String providerId;
    private String password;
    private String name;
    private String nickname;
    private UserRole role;
    private String phoneNumber;
    private LocalDate birthDate;
    private String gender;
    private String zipcode;
    private String baseAddress;
    private String detailAddress;
    private String profileImageUrl;
    private String bio;
    private UserType userType;
    private Integer preferredPriceRangeMin;
    private Integer preferredPriceRangeMax;
    private Boolean marketingAgreed;
    private Boolean phoneVerified;
    private Integer totalPurchases;
    private Integer totalSales;
    private Double rating;
    private Integer reviewCount;
    private LocalDateTime lastLoginAt;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

