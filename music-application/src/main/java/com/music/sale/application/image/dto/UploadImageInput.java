package com.music.sale.application.image.dto;

public record UploadImageInput(
    Long productId,
    String fileName,
    String fileType,
    long fileSize,
    boolean isThumbnail,
    int imageOrder,
    byte[] content
) {}
