package com.music.sale.web.image.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record ImageMetaRequest(
    @NotNull(message = "썸네일 여부는 필수입니다.")
    boolean isThumbnail,

    @NotNull(message = "이미지 순서는 필수입니다.")
    @Min(value = 1, message = "이미지 순서는 1부터 시작합니다.")
    @Max(value = 5, message = "이미지 순서는 최대 5까지 가능합니다.")
    int imageOrder
) {}
