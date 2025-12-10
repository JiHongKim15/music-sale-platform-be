package com.music.sale.application.auth.security;

import com.music.sale.domain.user.enums.UserRole;
import java.util.Collection;
import java.util.Collections;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public record AuthenticatedUser(Long userId, String email, String nickname, UserRole role)
    implements UserDetails {

  public static AuthenticatedUser of(Long userId, String email, String nickname, UserRole role) {
    return new AuthenticatedUser(userId, email, nickname, role);
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + role.name()));
  }

  @Override
  public String getPassword() {
    return null;
  }

  @Override
  public String getUsername() {
    return email;
  }
}
