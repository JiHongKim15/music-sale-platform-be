// Copyright (C) 2024 Your Name or Company
package com.music.sale.domain.user;

import com.music.sale.domain.user.enum.UserRole;
import com.music.sale.domain.user.enum.UserType;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;
import java.time.LocalDateTime;

/** 사용자 도메인 모델 */
@Value
@Builder(toBuilder = true)
public class User {
    Long id;
    Email email;
    String provider;
    String providerId;
    Password password;
    Name name;
    Nickname nickname;
    UserRole role;
    String phoneNumber;
    LocalDate birthDate;
    String gender;
    String zipcode;
    String baseAddress;
    String detailAddress;
    String profileImageUrl;
    String bio;
    UserType userType;
    Integer preferredPriceRangeMin;
    Integer preferredPriceRangeMax;
    Boolean marketingAgreed;
    Boolean phoneVerified;
    Integer totalPurchases;
    Integer totalSales;
    Double rating;
    Integer reviewCount;
    LocalDateTime lastLoginAt;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;

    public User {
        if (marketingAgreed == null) {
            marketingAgreed = false;
        }
        if (phoneVerified == null) {
            phoneVerified = false;
        }
        if (totalPurchases == null) {
            totalPurchases = 0;
        }
        if (totalSales == null) {
            totalSales = 0;
        }
        if (rating == null) {
            rating = 0.0;
        }
        if (reviewCount == null) {
            reviewCount = 0;
        }
        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }
        if (updatedAt == null) {
            updatedAt = LocalDateTime.now();
        }
    }

    public static User create(String email, String password, String name, String nickname, UserRole role,
                             String phoneNumber, LocalDate birthDate, String gender, String zipcode,
                             String baseAddress, String detailAddress, UserType userType, Boolean marketingAgreed) {
        return User.builder()
                .email(new Email(email))
                .password(new Password(password))
                .name(new Name(name))
                .nickname(new Nickname(nickname))
                .role(role)
                .phoneNumber(phoneNumber)
                .birthDate(birthDate)
                .gender(gender)
                .zipcode(zipcode)
                .baseAddress(baseAddress)
                .detailAddress(detailAddress)
                .userType(userType)
                .marketingAgreed(marketingAgreed != null && marketingAgreed)
                .build();
    }

    public static User create(String phoneNumber, String password, String name, String nickname, UserRole role,
                             LocalDate birthDate, String gender, String zipcode, String baseAddress,
                             String detailAddress, UserType userType, Boolean marketingAgreed) {
        return User.builder()
                .phoneNumber(phoneNumber)
                .password(new Password(password))
                .name(new Name(name))
                .nickname(new Nickname(nickname))
                .role(role)
                .birthDate(birthDate)
                .gender(gender)
                .zipcode(zipcode)
                .baseAddress(baseAddress)
                .detailAddress(detailAddress)
                .userType(userType)
                .marketingAgreed(marketingAgreed != null && marketingAgreed)
                .build();
    }

    public static User create(String email, String provider, String providerId, String name, String nickname,
                             UserRole role, String phoneNumber, LocalDate birthDate, String gender,
                             String zipcode, String baseAddress, String detailAddress, UserType userType,
                             Boolean marketingAgreed) {
        return User.builder()
                .email(new Email(email))
                .provider(provider)
                .providerId(providerId)
                .name(new Name(name))
                .nickname(new Nickname(nickname))
                .role(role)
                .phoneNumber(phoneNumber)
                .birthDate(birthDate)
                .gender(gender)
                .zipcode(zipcode)
                .baseAddress(baseAddress)
                .detailAddress(detailAddress)
                .userType(userType)
                .marketingAgreed(marketingAgreed != null && marketingAgreed)
                .build();
    }

    public User changeRole(UserRole newRole) {
        return this.toBuilder()
                .role(newRole)
                .build();
    }

    public User updateLastLogin() {
        return this.toBuilder()
                .lastLoginAt(LocalDateTime.now())
                .build();
    }

    public User incrementPurchaseCount() {
        return this.toBuilder()
                .totalPurchases(this.totalPurchases + 1)
                .build();
    }

    public User incrementSalesCount() {
        return this.toBuilder()
                .totalSales(this.totalSales + 1)
                .build();
    }

    public User updateRating(Double newRating, Integer reviewCount) {
        return this.toBuilder()
                .rating(newRating)
                .reviewCount(reviewCount)
                .build();
    }

    // Value Objects
    public static class Email {
        private final String value;

        public Email(String value) {
            if (value == null || !value.contains("@")) {
                throw new IllegalArgumentException("이메일 형식이 올바르지 않습니다");
            }
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    public static class Name {
        private final String value;

        public Name(String value) {
            if (value == null || value.isBlank()) {
                throw new IllegalArgumentException("이름은 비어있을 수 없습니다");
            }
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    public static class Nickname {
        private final String value;

        public Nickname(String value) {
            if (value == null || value.isBlank()) {
                throw new IllegalArgumentException("닉네임은 비어있을 수 없습니다");
            }
            if (value.length() < 2 || value.length() > 20) {
                throw new IllegalArgumentException("닉네임은 2-20자 사이여야 합니다");
            }
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    public static class Password {
        private final String value;

        public Password(String value) {
            if (value == null || value.isBlank()) {
                throw new IllegalArgumentException("비밀번호는 비어있을 수 없습니다");
            }
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }
}

