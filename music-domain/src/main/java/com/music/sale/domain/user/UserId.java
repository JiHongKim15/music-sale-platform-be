package com.music.sale.domain.user;

public record UserId(Long value) {
  public static UserId of(Long value) {
    if (value == null || value <= 0) {
      throw new IllegalArgumentException("UserId must be positive");
    }
    return new UserId(value);
  }
}
