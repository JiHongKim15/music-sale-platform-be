package com.music.sale.persistence.user.entity;

import com.music.sale.persistence.common.BaseEntity;
import com.music.sale.persistence.user.enums.SocialProvider;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "user_socials", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"provider", "providerId"}) // 같은 소셜ID 중복 가입 방지
})
public class UserSocialEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SocialProvider provider;

    @Column(nullable = false)
    private String providerId;
}
