package com.music.sale.persistence.user.adapter;

import com.music.sale.application.auth.port.out.LoadSocialAccountPort;
import com.music.sale.application.auth.port.out.SaveSocialAccountPort;
import com.music.sale.domain.user.User;
import com.music.sale.domain.user.enums.SocialProvider;
import com.music.sale.persistence.user.entity.UserEntity;
import com.music.sale.persistence.user.entity.UserSocialEntity;
import com.music.sale.persistence.user.mapper.UserMapper;
import com.music.sale.persistence.user.repository.UserRepository;
import com.music.sale.persistence.user.repository.UserSocialRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SocialAccountPersistenceAdapter
    implements LoadSocialAccountPort, SaveSocialAccountPort {

  private final UserSocialRepository userSocialRepository;
  private final UserRepository userRepository;
  private final UserMapper userMapper;

  @Override
  public Optional<User> loadUserBySocialAccount(SocialProvider provider, String providerId) {
    com.music.sale.persistence.user.enums.SocialProvider entityProvider =
        com.music.sale.persistence.user.enums.SocialProvider.valueOf(provider.name());

    return userSocialRepository
        .findByProviderAndProviderId(entityProvider, providerId)
        .map(UserSocialEntity::getUser)
        .map(userMapper::toDomain);
  }

  @Override
  public void saveSocialAccount(Long userId, SocialProvider provider, String providerId) {
    UserEntity userEntity =
        userRepository
            .findById(userId)
            .orElseThrow(() -> new IllegalArgumentException("User not found"));

    com.music.sale.persistence.user.enums.SocialProvider entityProvider =
        com.music.sale.persistence.user.enums.SocialProvider.valueOf(provider.name());

    UserSocialEntity entity =
        UserSocialEntity.builder()
            .user(userEntity)
            .provider(entityProvider)
            .providerId(providerId)
            .build();

    userSocialRepository.save(entity);
  }
}
