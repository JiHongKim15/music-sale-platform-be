package com.music.sale.web.image.common;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 허용되는 이미지 Content-Type
 */
public enum ImageContentType {
    JPEG("image/jpeg"),
    PNG("image/png"),
    JPG("image/jpg");

    private final String mimeType;

    ImageContentType(String mimeType) {
        this.mimeType = mimeType;
    }

    public String getMimeType() {
        return mimeType;
    }

    public static boolean isSupported(String mimeType) {
        if (mimeType == null) {
            return false;
        }
        return Arrays.stream(values())
                .anyMatch(type -> type.mimeType.equals(mimeType));
    }

    public static Set<String> getSupportedTypes() {
        return Arrays.stream(values())
                .map(ImageContentType::getMimeType)
                .collect(Collectors.toSet());
    }

    public static String getSupportedTypesString() {
        return String.join(", ", getSupportedTypes());
    }

}
