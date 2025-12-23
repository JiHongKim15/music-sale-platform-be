package com.music.sale.persistence.user.mapper;

import com.music.sale.domain.user.User;
import com.music.sale.domain.user.enums.UserRole;
import com.music.sale.domain.user.enums.UserStatus;
import com.music.sale.persistence.user.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component("userEntityMapper")
public class UserMapper {

  public User toDomain(UserEntity entity) {
    if (entity == null) {
      return null;
    }

    return User.builder()
        .id(entity.getId())
        .nickname(entity.getNickname())
        .profileImageUrl(entity.getProfileImageUrl())
        .email(entity.getEmail())
        .role(convertRole(entity.getRole()))
        .status(convertStatus(entity.getStatus()))
        .ci(entity.getCi())
        .realName(entity.getRealName())
        .phoneNumber(entity.getPhoneNumber())
        .birthDate(entity.getBirthDate())
        .isVerified(entity.isVerified())
        .build();
  }

  public UserEntity toEntity(User domain) {
    if (domain == null) {
      return null;
    }

    return UserEntity.builder()
        .id(domain.getId())
        .nickname(domain.getNickname())
        .profileImageUrl(domain.getProfileImageUrl())
        .email(domain.getEmail())
        .role(convertRoleToEntity(domain.getRole()))
        .status(convertStatusToEntity(domain.getStatus()))
        .ci(domain.getCi())
        .realName(domain.getRealName())
        .phoneNumber(domain.getPhoneNumber())
        .birthDate(domain.getBirthDate())
        .isVerified(domain.isVerified())
        .build();
  }

  private UserRole convertRole(com.music.sale.persistence.user.enums.UserRole entityRole) {
    if (entityRole == null) {
      return null;
    }
    return UserRole.valueOf(entityRole.name());
  }

  private com.music.sale.persistence.user.enums.UserRole convertRoleToEntity(UserRole domainRole) {
    if (domainRole == null) {
      return null;
    }
    return com.music.sale.persistence.user.enums.UserRole.valueOf(domainRole.name());
  }

  private UserStatus convertStatus(com.music.sale.persistence.user.enums.UserStatus entityStatus) {
    if (entityStatus == null) {
      return null;
    }
    return UserStatus.valueOf(entityStatus.name());
  }

  private com.music.sale.persistence.user.enums.UserStatus convertStatusToEntity(
      UserStatus domainStatus) {
    if (domainStatus == null) {
      return null;
    }
    return com.music.sale.persistence.user.enums.UserStatus.valueOf(domainStatus.name());
  }
}
