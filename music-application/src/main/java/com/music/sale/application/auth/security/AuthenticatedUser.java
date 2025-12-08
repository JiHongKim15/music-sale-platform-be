package com.music.sale.application.auth.security;

import com.music.sale.domain.user.enums.UserRole;
import java.util.Collection;
import java.util.Collections;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Getter
public class AuthenticatedUser implements UserDetails {
  private final Long userId;
  private final String email;
  private final String nickname;
  private final UserRole role;

  private AuthenticatedUser(Long userId, String email, String nickname, UserRole role) {
    this.userId = userId;
    this.email = email;
    this.nickname = nickname;
    this.role = role;
  }

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

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
