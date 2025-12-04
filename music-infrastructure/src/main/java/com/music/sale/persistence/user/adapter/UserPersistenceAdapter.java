package com.music.sale.persistence.user.adapter;

import com.music.sale.application.auth.port.out.LoadUserPort;
import com.music.sale.application.auth.port.out.SaveUserPort;
import com.music.sale.domain.user.User;
import com.music.sale.persistence.user.entity.UserEntity;
import com.music.sale.persistence.user.mapper.UserMapper;
import com.music.sale.persistence.user.repository.UserRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserPersistenceAdapter implements LoadUserPort, SaveUserPort {

  private final UserRepository userRepository;
  private final UserMapper userMapper;

  @Override
  public Optional<User> loadUserByEmail(String email) {
    return userRepository.findByEmail(email).map(userMapper::toDomain);
  }

  @Override
  public Optional<User> loadUserById(Long userId) {
    return userRepository.findById(userId).map(userMapper::toDomain);
  }

  @Override
  public User saveUser(User user) {
    UserEntity entity = userMapper.toEntity(user);
    UserEntity savedEntity = userRepository.save(entity);
    return userMapper.toDomain(savedEntity);
  }
}
