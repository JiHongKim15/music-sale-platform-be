package com.music.sale.persistence.user.repository;

import com.music.sale.persistence.user.entity.UserEntity;
import com.music.sale.persistence.user.entity.UserSocialEntity;
import com.music.sale.persistence.user.enums.SocialProvider;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserSocialRepository extends JpaRepository<UserSocialEntity, Long> {
  List<UserSocialEntity> findByUserId(Long userId);

  Optional<UserSocialEntity> findByProviderAndProviderId(
      SocialProvider provider, String providerId);

  @Modifying
  @Query("UPDATE UserSocialEntity s SET s.user = :toUser WHERE s.user = :fromUser")
  void updateUserForSocials(
      @Param("fromUser") UserEntity fromUser, @Param("toUser") UserEntity toUser);
}
