package com.music.sale.persistence.user.entity;

import com.music.sale.persistence.common.BaseEntity;
import com.music.sale.persistence.user.enums.UserRole;
import com.music.sale.persistence.user.enums.UserStatus;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(
    name = "users",
    indexes = {
      @Index(name = "idx_user_ci", columnList = "ci"), // 본인인증으로 사람 찾기용
      @Index(name = "idx_user_email", columnList = "email") // 이메일 검색용
    })
public class UserEntity extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "user_id")
  private Long id;

  @Column(length = 50)
  private String nickname;

  @Column(length = 300)
  private String profileImageUrl;

  @Column(length = 100)
  private String email;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private UserRole role;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private UserStatus status;

  @Column(unique = true, length = 100)
  private String ci;

  @Column(length = 50)
  private String realName;

  @Column(length = 20)
  private String phoneNumber;

  @Column(length = 8)
  private String birthDate;

  @Column(nullable = false)
  private boolean isVerified;
}
