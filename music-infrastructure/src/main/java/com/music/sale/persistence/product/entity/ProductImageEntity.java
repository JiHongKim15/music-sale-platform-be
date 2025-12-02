package com.music.sale.persistence.product.entity;

import com.music.sale.persistence.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "ProductImageEntity")
@Table(name = "product_image")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductImageEntity extends BaseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "product_item_id", nullable = false)
  private Long productItemId;

  @Column(name = "url", nullable = false, length = 500)
  private String url;

  @Column(name = "is_thumbnail", nullable = false)
  private boolean isThumbnail = false;

  @Column(name = "image_order", nullable = false)
  private Integer imageOrder = 1;

  @Column(name = "file_size", nullable = false)
  private Long fileSize;

  @Column(name = "file_name", nullable = false, length = 255)
  private String fileName;

  @Column(name = "file_type", nullable = false, length = 100)
  private String fileType;
}
