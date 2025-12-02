package com.music.sale.common;

import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {
  private final ErrorDefinition errorDefinition;
  private final Object[] args;

  public BusinessException(ErrorDefinition errorDefinition) {
    super(errorDefinition.getKey());
    this.errorDefinition = errorDefinition;
    this.args = new Object[] {};
  }

  public BusinessException(ErrorDefinition errorDefinition, String customMessage) {
    super(customMessage);
    this.errorDefinition = errorDefinition;
    this.args = new Object[] {};
  }

  public BusinessException(ErrorDefinition errorDefinition, Object[] args) {
    super(errorDefinition.getKey());
    this.errorDefinition = errorDefinition;
    this.args = args;
  }
}
