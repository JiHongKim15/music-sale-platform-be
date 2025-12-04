package com.music.sale.persistence.user.repository;

import com.music.sale.persistence.user.entity.RefreshTokenEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface RefreshTokenRepository extends CrudRepository<RefreshTokenEntity, String> {
  Optional<RefreshTokenEntity> findByRefreshToken(String refreshToken);

  List<RefreshTokenEntity> findByUserId(Long userId);

  void deleteByRefreshToken(String refreshToken);

  void deleteByUserId(Long userId);
}
