package com.music.sale.application.product.exception;

import com.music.sale.common.ErrorDefinition;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ProductErrorCode implements ErrorDefinition {
    PRODUCT_NOT_FOUND("error.PRODUCT_NOT_FOUND");

    private final String key;
}
