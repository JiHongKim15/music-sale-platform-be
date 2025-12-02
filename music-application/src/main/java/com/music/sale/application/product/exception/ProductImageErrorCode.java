package com.music.sale.application.product.exception;

import com.music.sale.common.ErrorDefinition;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ProductImageErrorCode implements ErrorDefinition {
  IMAGE_NOT_FOUND("error.IMAGE_NOT_FOUND"),
  IMAGE_DELETE_FAILED("error.IMAGE_DELETE_FAILED"),
  INVALID_IMAGE_ORDER("error.INVALID_IMAGE_ORDER"),
  FILE_SIZE_EXCEEDED("error.FILE_SIZE_EXCEEDED");

  private final String key;
}
