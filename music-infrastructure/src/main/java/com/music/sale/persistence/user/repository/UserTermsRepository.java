package com.music.sale.persistence.user.repository;

import com.music.sale.persistence.user.entity.UserTermsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserTermsRepository extends JpaRepository<UserTermsEntity, Long> {
    List<UserTermsEntity> findByUser_Id(Long userId);
}

