package com.music.sale.infrastructure.security.oauth2;

import com.music.sale.application.auth.dto.UserInfo;
import com.music.sale.domain.user.enums.UserRole;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

@Getter
public class OAuth2UserPrincipal implements OAuth2User {
  private final UserInfo userInfo;
  private final Map<String, Object> attributes;

  public OAuth2UserPrincipal(UserInfo userInfo, Map<String, Object> attributes) {
    this.userInfo = userInfo;
    this.attributes = attributes;
  }

  @Override
  public Map<String, Object> getAttributes() {
    return attributes;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    UserRole role = userInfo.role();
    return Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + role.name()));
  }

  @Override
  public String getName() {
    return userInfo.email();
  }

  public Long getUserId() {
    return userInfo.userId();
  }

  public String getEmail() {
    return userInfo.email();
  }

  public String getNickname() {
    return userInfo.nickname();
  }

  public UserRole getRole() {
    return userInfo.role();
  }
}
