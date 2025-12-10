package com.music.sale.application.user.port.outport;

import com.music.sale.domain.user.User;
import com.music.sale.domain.user.UserSocial;
import com.music.sale.domain.user.UserTerms;
import com.music.sale.domain.user.enums.SocialProvider;
import java.util.List;
import java.util.Optional;

public interface UserQueryPort {
  Optional<User> findById(Long userId);

  Optional<User> findBySocialAccount(SocialProvider provider, String providerId);

  List<UserSocial> findSocialsByUserId(Long userId);

  List<UserTerms> findTermsByUserId(Long userId);
}
