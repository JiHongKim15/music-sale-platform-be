package com.music.sale.web.image.request;

import com.music.sale.web.image.ImageConstants;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record ImageMetaRequest(
    @NotNull(message = "썸네일 여부는 필수입니다.")
    boolean isThumbnail,

    @NotNull(message = "이미지 순서는 필수입니다.")
    @Min(value = ImageConstants.MIN_IMAGE_ORDER, message = "이미지 순서는 " + ImageConstants.MIN_IMAGE_ORDER + "부터 시작합니다.")
    @Max(value = ImageConstants.MAX_IMAGE_ORDER, message = "이미지 순서는 최대 " + ImageConstants.MAX_IMAGE_ORDER + "까지 가능합니다.")
    int imageOrder
) {}
