package com.music.sale.persistence.user.repository;

import com.music.sale.persistence.user.entity.UserSocialEntity;
import com.music.sale.persistence.user.enums.SocialProvider;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserSocialRepository extends JpaRepository<UserSocialEntity, Long> {
  List<UserSocialEntity> findByUser_Id(Long userId);

  Optional<UserSocialEntity> findByProviderAndProviderId(
      SocialProvider provider, String providerId);
}
