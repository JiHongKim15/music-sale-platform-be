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
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserPersistenceAdapter implements UserQueryPort, UserCommandPort {

  private final UserRepository userRepository;
  private final UserSocialRepository userSocialRepository;
  private final UserTermsRepository userTermsRepository;
  private final UserPersistenceMapper mapper;

  @Override
  public Optional<User> findById(Long userId) {
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
  public List<UserSocial> findSocialsByUserId(Long userId) {
    return userSocialRepository.findByUserId(userId).stream().map(mapper::toDomain).toList();
  }

  @Override
  public List<UserTerms> findTermsByUserId(Long userId) {
    return userTermsRepository.findByUserId(userId).stream().map(mapper::toDomain).toList();
  }

  @Override
  public User save(User user) {
    UserEntity entity = mapper.toEntity(user);
    UserEntity savedEntity = userRepository.save(entity);
    return mapper.toDomain(savedEntity);
  }

  @Override
  public UserSocial saveSocialAccount(UserSocial userSocial) {
    UserSocialEntity entity = mapper.toEntity(userSocial);
    UserSocialEntity savedEntity = userSocialRepository.save(entity);
    return mapper.toDomain(savedEntity);
  }

  @Override
  public UserTerms saveTerms(UserTerms userTerms) {
    UserTermsEntity entity = mapper.toEntity(userTerms);
    UserTermsEntity savedEntity = userTermsRepository.save(entity);
    return mapper.toDomain(savedEntity);
  }
}
