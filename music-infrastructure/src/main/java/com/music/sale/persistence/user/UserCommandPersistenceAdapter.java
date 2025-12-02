package com.music.sale.persistence.user;

import com.music.sale.application.user.port.outport.UserCommandPort;
import com.music.sale.domain.user.User;
import com.music.sale.domain.user.UserTerms;
import com.music.sale.persistence.user.entity.UserEntity;
import com.music.sale.persistence.user.entity.UserTermsEntity;
import com.music.sale.persistence.user.mapper.UserPersistenceMapper;
import com.music.sale.persistence.user.repository.UserRepository;
import com.music.sale.persistence.user.repository.UserTermsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserCommandPersistenceAdapter implements UserCommandPort {

  private final UserRepository userRepository;
  private final UserTermsRepository userTermsRepository;
  private final UserPersistenceMapper userMapper;

  @Override
  public User save(User user) {
    UserEntity entity = userMapper.toEntity(user);
    UserEntity savedEntity = userRepository.save(entity);
    return userMapper.toDomain(savedEntity);
  }

  @Override
  public UserTerms saveTerms(UserTerms userTerms) {
    UserTermsEntity entity = userMapper.toEntity(userTerms);
    UserTermsEntity savedEntity = userTermsRepository.save(entity);
    return userMapper.toDomain(savedEntity);
  }
}
