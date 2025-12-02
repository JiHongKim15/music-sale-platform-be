package com.music.sale.web.product.enums;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ProductImageContentType {
  JPEG("image/jpeg"),
  PNG("image/png"),
  JPG("image/jpg");

  private final String mimeType;

  public static boolean isSupported(String mimeType) {
    if (mimeType == null) {
      return false;
    }
    return Arrays.stream(values()).anyMatch(type -> type.mimeType.equals(mimeType));
  }

  public static Set<String> getSupportedTypes() {
    return Arrays.stream(values())
        .map(ProductImageContentType::getMimeType)
        .collect(Collectors.toSet());
  }

  public static String getSupportedTypesString() {
    return String.join(", ", getSupportedTypes());
  }
}
