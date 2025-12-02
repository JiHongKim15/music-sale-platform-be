package com.music.sale.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ApiResponse<T> {

  private final boolean success;
  private final T data;
  private final String message;

  public static <T> ApiResponse<T> success(T data) {
    return new ApiResponse<>(true, data, null);
  }

  public static ApiResponse<Void> success() {
    return new ApiResponse<>(true, null, null);
  }

  public static ApiResponse<Void> error(String message) {
    return new ApiResponse<>(false, null, message);
  }
}
