package com.music.sale.web.product.request;

public record ProductImageMetaRequest(
    String filename,
    String contentType,
    long size,
    boolean isThumbnail,
    int ordering
) {
}
