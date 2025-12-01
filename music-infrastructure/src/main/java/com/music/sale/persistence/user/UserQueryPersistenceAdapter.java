package com.music.sale.persistence.user;

import com.music.sale.application.user.port.outport.UserQueryPort;
import com.music.sale.domain.user.User;
import com.music.sale.domain.user.UserSocial;
import com.music.sale.domain.user.UserTerms;
import com.music.sale.persistence.user.mapper.UserPersistenceMapper;
import com.music.sale.persistence.user.repository.UserRepository;
import com.music.sale.persistence.user.repository.UserSocialRepository;
import com.music.sale.persistence.user.repository.UserTermsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserQueryPersistenceAdapter implements UserQueryPort {

    private final UserRepository userRepository;
    private final UserSocialRepository userSocialRepository;
    private final UserTermsRepository userTermsRepository;
    private final UserPersistenceMapper userMapper;

    @Override
    public Optional<User> findById(Long userId) {
        return userRepository.findById(userId).map(userMapper::toDomain);
    }

    @Override
    public List<UserSocial> findSocialsByUserId(Long userId) {
        return userSocialRepository.findByUser_Id(userId).stream()
                .map(userMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<UserTerms> findTermsByUserId(Long userId) {
        return userTermsRepository.findByUser_Id(userId).stream()
                .map(userMapper::toDomain)
                .collect(Collectors.toList());
    }
}
