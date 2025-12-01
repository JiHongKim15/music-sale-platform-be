package com.music.sale.application.user.port.inport;

import com.music.sale.application.user.dto.input.AgreeTermsInput;
import com.music.sale.application.user.dto.input.UpdateUserInput;
import com.music.sale.application.user.dto.output.UserOutput;

public interface UserCommandUseCase {
    UserOutput updateUser(Long userId, UpdateUserInput input, Long currentUserId);

    void withdrawUser(Long userId, Long currentUserId);

    void agreeToTerms(AgreeTermsInput input, Long currentUserId);
}
