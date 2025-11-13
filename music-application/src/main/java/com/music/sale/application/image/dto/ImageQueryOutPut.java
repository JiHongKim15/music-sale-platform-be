package com.music.sale.application.image.dto;

import com.music.sale.domain.image.ProductImage;
import lombok.Builder;

@Builder
public record ImageQueryOutPut(
        Long id,
        Long productId,
        String url,
        Boolean isThumbnail,
        Integer imageOrder,
        Long fileSize,
        String fileName,
        String fileType
) {}
