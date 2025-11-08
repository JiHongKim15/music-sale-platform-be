package com.music.sale.application.image.dto;

public record UploadImageInput(
    String originalFilename,
    byte[] content,
    String contentType,
    long size,
    boolean isThumbnail,
    int imageOrder
) {}
