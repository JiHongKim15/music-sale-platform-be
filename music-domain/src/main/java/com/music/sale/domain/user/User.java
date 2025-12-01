package com.music.sale.domain.user;

import com.music.sale.domain.user.enums.UserRole;
import com.music.sale.domain.user.enums.UserStatus;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder(toBuilder = true)
public class User {
    private final Long id;
    private final String nickname;
    private final String profileImageUrl;
    private final String email;
    private final UserRole role;
    private UserStatus status;
    private final String ci;
    private final String realName;
    private final String phoneNumber;
    private final String birthDate;
    private final boolean isVerified;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public User updateProfile(String nickname, String profileImageUrl) {
        return this.toBuilder()
                .nickname(nickname)
                .profileImageUrl(profileImageUrl)
                .build();
    }

    public void withdraw() {
        this.status = UserStatus.WITHDRAWAL;
    }
}