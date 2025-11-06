package com.music.sale.persistence.image.repository;

import com.music.sale.persistence.image.entity.ImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<ImageEntity, Long> {

}
