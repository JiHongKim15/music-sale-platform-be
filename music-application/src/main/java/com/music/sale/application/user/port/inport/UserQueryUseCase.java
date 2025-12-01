package com.music.sale.application.user.port.inport;

import com.music.sale.application.user.dto.output.UserOutput;
import com.music.sale.application.user.dto.output.UserSocialOutput;
import com.music.sale.application.user.dto.output.UserTermsOutput;

import java.util.List;

public interface UserQueryUseCase {
    UserOutput getUserById(Long userId);

    List<UserSocialOutput> getSocialsByUserId(Long userId);

    List<UserTermsOutput> getTermsByUserId(Long userId);
}
