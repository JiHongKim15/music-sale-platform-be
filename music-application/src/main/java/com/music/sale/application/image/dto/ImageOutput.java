package com.music.sale.application.image.dto;

public record ImageOutput(
    Long id,
    Long productId,
    String url,
    boolean isThumbnail,
    int imageOrder,
    Long fileSize,
    String fileName,
    String fileType
) {}
