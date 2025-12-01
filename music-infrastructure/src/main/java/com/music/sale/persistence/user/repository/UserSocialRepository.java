package com.music.sale.persistence.user.repository;

import com.music.sale.persistence.user.entity.UserSocialEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserSocialRepository extends JpaRepository<UserSocialEntity, Long> {
    List<UserSocialEntity> findByUser_Id(Long userId);
}
