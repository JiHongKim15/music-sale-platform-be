package com.music.sale.application.user.service;

import com.music.sale.application.user.dto.output.UserOutput;
import com.music.sale.application.user.dto.output.UserSocialOutput;
import com.music.sale.application.user.dto.output.UserTermsOutput;
import com.music.sale.application.user.exception.UserErrorCode;
import com.music.sale.application.user.mapper.UserMapper;
import com.music.sale.application.user.port.inport.UserQueryUseCase;
import com.music.sale.application.user.port.outport.UserQueryPort;
import com.music.sale.common.BusinessException;
import com.music.sale.domain.user.User;
import com.music.sale.domain.user.enums.SocialProvider;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserQueryService implements UserQueryUseCase {

  private final UserQueryPort userQueryPort;
  private final UserMapper userMapper;

  @Override
  public UserOutput getUserById(Long userId) {
    return userQueryPort
        .findById(userId)
        .map(userMapper::toOutput)
        .orElseThrow(() -> new BusinessException(UserErrorCode.USER_NOT_FOUND));
  }

  @Override
  public Optional<User> findById(Long userId) {
    return userQueryPort.findById(userId);
  }

  @Override
  public Optional<User> findBySocialAccount(SocialProvider provider, String providerId) {
    return userQueryPort.findBySocialAccount(provider, providerId);
  }

  @Override
  public List<UserSocialOutput> getSocialsByUserId(Long userId) {
    // Ensure user exists before fetching socials
    if (userQueryPort.findById(userId).isEmpty()) {
      throw new BusinessException(UserErrorCode.USER_NOT_FOUND);
    }
    return userQueryPort.findSocialsByUserId(userId).stream()
        .map(userMapper::toOutput)
        .collect(Collectors.toList());
  }

  @Override
  public List<UserTermsOutput> getTermsByUserId(Long userId) {
    // Ensure user exists before fetching terms
    if (userQueryPort.findById(userId).isEmpty()) {
      throw new BusinessException(UserErrorCode.USER_NOT_FOUND);
    }
    return userQueryPort.findTermsByUserId(userId).stream()
        .map(userMapper::toOutput)
        .collect(Collectors.toList());
  }
}
