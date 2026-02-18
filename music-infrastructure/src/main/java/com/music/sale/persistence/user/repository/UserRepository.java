package com.music.sale.persistence.user.repository;

import com.music.sale.persistence.user.entity.UserEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
  Optional<UserEntity> findByEmail(String email);

  Optional<UserEntity> findByCi(String ci);
}
