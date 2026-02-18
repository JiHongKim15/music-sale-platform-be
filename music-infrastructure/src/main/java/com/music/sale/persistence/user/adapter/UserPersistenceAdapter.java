package com.music.sale.persistence.user.adapter;

import com.music.sale.application.user.port.outport.UserCommandPort;
import com.music.sale.application.user.port.outport.UserQueryPort;
import com.music.sale.domain.user.User;
import com.music.sale.domain.user.UserSocial;
import com.music.sale.domain.user.UserTerms;
import com.music.sale.domain.user.enums.SocialProvider;
import com.music.sale.persistence.user.entity.UserEntity;
import com.music.sale.persistence.user.entity.UserSocialEntity;
import com.music.sale.persistence.user.entity.UserTermsEntity;
import com.music.sale.persistence.user.mapper.UserPersistenceMapper;
import com.music.sale.persistence.user.repository.UserRepository;
import com.music.sale.persistence.user.repository.UserSocialRepository;
import com.music.sale.persistence.user.repository.UserTermsRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserPersistenceAdapter implements UserQueryPort, UserCommandPort {

  private final UserRepository userRepository;
  private final UserSocialRepository userSocialRepository;
  private final UserTermsRepository userTermsRepository;
  private final UserPersistenceMapper mapper;

  @Override
  public Optional<User> findById(long userId) {
    return userRepository.findById(userId).map(mapper::toDomain);
  }

  @Override
  public Optional<User> findBySocialAccount(SocialProvider provider, String providerId) {
    com.music.sale.persistence.user.enums.SocialProvider entityProvider =
        com.music.sale.persistence.user.enums.SocialProvider.valueOf(provider.name());

    return userSocialRepository
        .findByProviderAndProviderId(entityProvider, providerId)
        .map(UserSocialEntity::getUser)
        .map(mapper::toDomain);
  }

  @Override
  public List<UserSocial> findSocialsByUserId(long userId) {
    return userSocialRepository.findByUserId(userId).stream().map(mapper::toDomain).toList();
  }

  @Override
  public List<UserTerms> findTermsByUserId(long userId) {
    return userTermsRepository.findByUserId(userId).stream().map(mapper::toDomain).toList();
  }

  @Override
  public Optional<User> findByCi(String ci) {
    return userRepository.findByCi(ci).map(mapper::toDomain);
  }

  @Override
  public User save(User user) {
    UserEntity entity = mapper.toEntity(user);
    UserEntity savedEntity = userRepository.save(entity);
    return mapper.toDomain(savedEntity);
  }

  @NotNull
  @Override
  public UserSocial saveSocialAccount(@NotNull UserSocial userSocial) {
    UserSocialEntity entity = mapper.toEntity(userSocial);
    UserSocialEntity savedEntity = userSocialRepository.save(entity);
    return mapper.toDomain(savedEntity);
  }

  @NotNull
  @Override
  public UserTerms saveTerms(@NotNull UserTerms userTerms) {
    UserTermsEntity entity = mapper.toEntity(userTerms);
    UserTermsEntity savedEntity = userTermsRepository.save(entity);
    return mapper.toDomain(savedEntity);
  }

  @Override
  public void updateSocialAccountUserId(long fromUserId, long toUserId) {
    UserEntity fromUser =
        userRepository
            .findById(fromUserId)
            .orElseThrow(() -> new IllegalArgumentException("User not found: " + fromUserId));
    UserEntity toUser =
        userRepository
            .findById(toUserId)
            .orElseThrow(() -> new IllegalArgumentException("User not found: " + toUserId));
    userSocialRepository.updateUserForSocials(fromUser, toUser);
  }

  @Override
  public void deleteUser(long userId) {
    userRepository.deleteById(userId);
  }
}
