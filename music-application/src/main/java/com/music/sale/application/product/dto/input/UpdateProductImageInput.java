package com.music.sale.application.product.dto.input;

public record UpdateProductImageInput(
    Long productId,
    String fileName,
    String fileType,
    long fileSize,
    boolean isThumbnail,
    int imageOrder,
    byte[] content) {}
