package com.music.sale.application.user.port.inport;

import com.music.sale.application.user.dto.output.UserOutput;
import com.music.sale.application.user.dto.output.UserSocialOutput;
import com.music.sale.application.user.dto.output.UserTermsOutput;
import com.music.sale.domain.user.User;
import com.music.sale.domain.user.enums.SocialProvider;
import java.util.List;
import java.util.Optional;

public interface UserQueryUseCase {
  UserOutput getUserById(Long userId);

  Optional<User> findById(Long userId);

  Optional<User> findBySocialAccount(SocialProvider provider, String providerId);

  List<UserSocialOutput> getSocialsByUserId(Long userId);

  List<UserTermsOutput> getTermsByUserId(Long userId);
}
