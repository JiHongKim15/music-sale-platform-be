package com.music.sale.domain.exception;

public class DomainException extends RuntimeException {

  private final String errorKey;

  public DomainException(String errorKey) {
    super(errorKey);
    this.errorKey = errorKey;
  }

  public DomainException(String errorKey, String message) {
    super(message);
    this.errorKey = errorKey;
  }

  public String getErrorKey() {
    return errorKey;
  }
}
