package com.music.sale.application.user.service;

import com.music.sale.application.user.dto.input.AgreeTermsInput;
import com.music.sale.application.user.dto.input.UpdateUserInput;
import com.music.sale.application.user.dto.output.UserOutput;
import com.music.sale.application.user.exception.UserErrorCode;
import com.music.sale.application.user.mapper.UserMapper;
import com.music.sale.application.user.port.inport.UserCommandUseCase;
import com.music.sale.application.user.port.outport.UserCommandPort;
import com.music.sale.application.user.port.outport.UserQueryPort;
import com.music.sale.common.BusinessException;
import com.music.sale.domain.user.User;
import com.music.sale.domain.user.UserTerms;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@Transactional
@RequiredArgsConstructor
public class UserCommandService implements UserCommandUseCase {

    private final UserCommandPort userCommandPort;
    private final UserQueryPort userQueryPort;
    private final UserMapper userMapper;

    @Override
    public UserOutput updateUser(Long userId, UpdateUserInput input, Long currentUserId) {
        if (!Objects.equals(userId, currentUserId)) {
            throw new BusinessException(UserErrorCode.USER_PERMISSION_DENIED, "자신의 프로필만 수정할 수 있습니다.");
        }

        User user = userQueryPort.findById(userId)
                .orElseThrow(() -> new BusinessException(UserErrorCode.USER_NOT_FOUND, "사용자를 찾을 수 없습니다: " + userId));

        User updatedUser = user.updateProfile(input.nickname(), input.profileImageUrl());
        User savedUser = userCommandPort.save(updatedUser);

        return userMapper.toOutput(savedUser);
    }

    @Override
    public void withdrawUser(Long userId, Long currentUserId) {
        if (!Objects.equals(userId, currentUserId)) {
            throw new BusinessException(UserErrorCode.USER_PERMISSION_DENIED, "자신만 탈퇴할 수 있습니다.");
        }
        User user = userQueryPort.findById(userId)
                .orElseThrow(() -> new BusinessException(UserErrorCode.USER_NOT_FOUND, "사용자를 찾을 수 없습니다: " + userId));

        user.withdraw();
        userCommandPort.save(user);
    }

    @Override
    public void agreeToTerms(AgreeTermsInput input, Long currentUserId) {
        if (!Objects.equals(input.userId(), currentUserId)) {
            throw new BusinessException(UserErrorCode.USER_PERMISSION_DENIED, "자신의 약관만 동의할 수 있습니다.");
        }
        userQueryPort.findById(input.userId())
                .orElseThrow(() -> new BusinessException(UserErrorCode.USER_NOT_FOUND, "사용자를 찾을 수 없습니다: " + input.userId()));

        UserTerms userTerms = UserTerms.of(input.userId(), input.title(), input.version(), input.isAgreed());
        userCommandPort.saveTerms(userTerms);
    }
}
