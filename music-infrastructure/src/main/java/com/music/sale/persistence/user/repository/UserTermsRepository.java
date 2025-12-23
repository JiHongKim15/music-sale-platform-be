package com.music.sale.persistence.user.repository;

import com.music.sale.persistence.user.entity.UserTermsEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserTermsRepository extends JpaRepository<UserTermsEntity, Long> {
  List<UserTermsEntity> findByUserId(Long userId);
}
