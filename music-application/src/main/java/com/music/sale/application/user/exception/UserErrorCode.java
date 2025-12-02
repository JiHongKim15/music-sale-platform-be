package com.music.sale.application.user.exception;

import com.music.sale.common.ErrorDefinition;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserErrorCode implements ErrorDefinition {
  USER_NOT_FOUND("error.USER_NOT_FOUND"),
  USER_PERMISSION_DENIED("error.USER_PERMISSION_DENIED");

  private final String key;
}
