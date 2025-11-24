package com.music.sale.domain.user.enums;
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

