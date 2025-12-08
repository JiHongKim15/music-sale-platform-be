package com.music.sale.infrastructure.security.oauth2;

import com.music.sale.infrastructure.security.oauth2.userinfo.OAuth2UserInfo;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

@Getter
public class OAuth2AuthenticatedUser implements OAuth2User {
  private final OAuth2UserInfo userInfo;
  private final Map<String, Object> attributes;

  public OAuth2AuthenticatedUser(OAuth2UserInfo userInfo, Map<String, Object> attributes) {
    this.userInfo = userInfo;
    this.attributes = attributes;
  }

  @Override
  public Map<String, Object> getAttributes() {
    return attributes;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return Collections.emptyList();
  }

  @Override
  public String getName() {
    return userInfo.getEmail();
  }
}
