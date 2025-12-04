package com.music.sale.persistence.user.mapper;

import com.music.sale.domain.user.enums.SocialProvider;
import com.music.sale.domain.user.vo.SocialAccount;
import com.music.sale.persistence.user.entity.UserEntity;
import com.music.sale.persistence.user.entity.UserSocialEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserSocialMapper {

  private final UserMapper userMapper;

  public SocialAccount toDomain(UserSocialEntity entity) {
    if (entity == null) {
      return null;
    }

    return SocialAccount.builder()
        .id(entity.getId())
        .userId(entity.getUser().getId())
        .provider(convertProvider(entity.getProvider()))
        .providerId(entity.getProviderId())
        .build();
  }

  public UserSocialEntity toEntity(SocialAccount domain, UserEntity userEntity) {
    if (domain == null) {
      return null;
    }

    return UserSocialEntity.builder()
        .id(domain.getId())
        .user(userEntity)
        .provider(convertProviderToEntity(domain.getProvider()))
        .providerId(domain.getProviderId())
        .build();
  }

  private SocialProvider convertProvider(
      com.music.sale.persistence.user.enums.SocialProvider entityProvider) {
    if (entityProvider == null) {
      return null;
    }
    return SocialProvider.valueOf(entityProvider.name());
  }

  private com.music.sale.persistence.user.enums.SocialProvider convertProviderToEntity(
      SocialProvider domainProvider) {
    if (domainProvider == null) {
      return null;
    }
    return com.music.sale.persistence.user.enums.SocialProvider.valueOf(domainProvider.name());
  }
}
