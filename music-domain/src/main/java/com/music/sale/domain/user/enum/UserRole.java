// Copyright (C) 2024 Your Name or Company
package com.music.sale.domain.user.enum;

public enum UserRole {
    ADMIN,
    SELLER,
    USER;

    public boolean hasPermission(UserRole requiredRole) {
        return switch (this) {
            case ADMIN -> true;
            case SELLER -> this == requiredRole;
            case USER -> this == requiredRole;
        };
    }
}

