package com.music.sale.application.user.mapper;

import com.music.sale.application.user.dto.output.UserOutput;
import com.music.sale.application.user.dto.output.UserSocialOutput;
import com.music.sale.application.user.dto.output.UserTermsOutput;
import com.music.sale.domain.user.User;
import com.music.sale.domain.user.UserSocial;
import com.music.sale.domain.user.UserTerms;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserOutput toOutput(User user) {
        return UserOutput.builder()
                .id(user.getId())
                .nickname(user.getNickname())
                .profileImageUrl(user.getProfileImageUrl())
                .email(user.getEmail())
                .role(user.getRole())
                .status(user.getStatus())
                .isVerified(user.isVerified())
                .createdAt(user.getCreatedAt())
                .build();
    }

    public UserSocialOutput toOutput(UserSocial userSocial) {
        return UserSocialOutput.builder()
                .provider(userSocial.getProvider())
                .providerId(userSocial.getProviderId())
                .build();
    }

    public UserTermsOutput toOutput(UserTerms userTerms) {
        return UserTermsOutput.builder()
                .title(userTerms.getTitle())
                .version(userTerms.getVersion())
                .isAgreed(userTerms.getIsAgreed())
                .agreedAt(userTerms.getUpdatedAt()) // Or createdAt, depending on business logic
                .build();
    }
}
