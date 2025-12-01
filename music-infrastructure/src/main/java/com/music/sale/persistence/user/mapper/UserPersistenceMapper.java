package com.music.sale.persistence.user.mapper;

import com.music.sale.domain.user.User;
import com.music.sale.domain.user.UserTerms;
import com.music.sale.domain.user.enums.UserRole;
import com.music.sale.domain.user.enums.UserStatus;
import com.music.sale.persistence.user.entity.UserEntity;
import com.music.sale.persistence.user.entity.UserTermsEntity;
import com.music.sale.persistence.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserPersistenceMapper {

    private final UserRepository userRepository;

    public UserEntity toEntity(User domain) {
        if (domain == null) {
            return null;
        }
        return UserEntity.builder()
                .id(domain.getId())
                .nickname(domain.getNickname())
                .profileImageUrl(domain.getProfileImageUrl())
                .email(domain.getEmail())
                .role(com.music.sale.persistence.user.enums.UserRole.valueOf(domain.getRole().name()))
                .status(com.music.sale.persistence.user.enums.UserStatus.valueOf(domain.getStatus().name()))
                .ci(domain.getCi())
                .realName(domain.getRealName())
                .phoneNumber(domain.getPhoneNumber())
                .birthDate(domain.getBirthDate())
                .isVerified(domain.isVerified())
                .build();
    }

    public User toDomain(UserEntity entity) {
        if (entity == null) {
            return null;
        }
        return User.builder()
                .id(entity.getId())
                .nickname(entity.getNickname())
                .profileImageUrl(entity.getProfileImageUrl())
                .email(entity.getEmail())
                .role(UserRole.valueOf(entity.getRole().name()))
                .status(UserStatus.valueOf(entity.getStatus().name()))
                .ci(entity.getCi())
                .realName(entity.getRealName())
                .phoneNumber(entity.getPhoneNumber())
                .birthDate(entity.getBirthDate())
                .isVerified(entity.isVerified())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }
    
    public UserTermsEntity toEntity(UserTerms domain) {
        if (domain == null) {
            return null;
        }
        // UserTermsEntity requires a UserEntity, not just a userId.
        // We need to fetch the UserEntity from the database.
        UserEntity userEntity = userRepository.findById(domain.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found for UserTerms mapping: " + domain.getUserId()));

        return UserTermsEntity.builder()
                .id(domain.getId())
                .user(userEntity)
                .title(domain.getTitle())
                .version(domain.getVersion())
                .isAgreed(domain.getIsAgreed())
                .build();
    }

    public UserTerms toDomain(UserTermsEntity entity) {
        if (entity == null) {
            return null;
        }
        return UserTerms.builder()
                .id(entity.getId())
                .userId(entity.getUser().getId())
                .title(entity.getTitle())
                .version(entity.getVersion())
                .isAgreed(entity.getIsAgreed())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }

    public UserSocialEntity toEntity(UserSocial domain) {
        if (domain == null) {
            return null;
        }
        UserEntity userEntity = userRepository.findById(domain.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found for UserSocial mapping: " + domain.getUserId()));

        return UserSocialEntity.builder()
                .id(domain.getId())
                .user(userEntity)
                .provider(com.music.sale.persistence.user.enums.SocialProvider.valueOf(domain.getProvider().name()))
                .providerId(domain.getProviderId())
                .build();
    }

    public UserSocial toDomain(UserSocialEntity entity) {
        if (entity == null) {
            return null;
        }
        return UserSocial.builder()
                .id(entity.getId())
                .userId(entity.getUser().getId())
                .provider(com.music.sale.domain.user.enums.SocialProvider.valueOf(entity.getProvider().name()))
                .providerId(entity.getProviderId())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }
}
