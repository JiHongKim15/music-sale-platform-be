package com.music.sale.domain.product;

public record ProductImage(
    Long id,
    String fileName,
    String fileType,
    long fileSize,
    boolean isThumbnail,
    int imageOrder
) {
}

