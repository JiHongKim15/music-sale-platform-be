package com.music.sale.domain.image;

import java.util.Objects;

public record ProductImage(
    Long id,
    String fileName,
    String fileType,
    long fileSize,
    boolean isThumbnail,
    int imageOrder
) {

}
