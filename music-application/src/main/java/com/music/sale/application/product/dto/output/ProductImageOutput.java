package com.music.sale.application.product.dto.output;

public record ProductImageOutput(
    Long id,
    Long productId,
    String url,
    Boolean isThumbnail,
    Integer imageOrder,
    Long fileSize,
    String fileName,
    String fileType
) {}
