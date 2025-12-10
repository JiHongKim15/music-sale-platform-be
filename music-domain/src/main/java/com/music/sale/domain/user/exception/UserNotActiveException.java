package com.music.sale.domain.user.exception;

import com.music.sale.domain.exception.DomainException;

public class UserNotActiveException extends DomainException {

  private static final String ERROR_KEY = "error.USER_NOT_ACTIVE";

  public UserNotActiveException() {
    super(ERROR_KEY);
  }
}
