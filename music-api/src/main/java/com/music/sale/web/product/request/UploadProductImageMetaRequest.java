package com.music.sale.web.product.request;


import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import static com.music.sale.web.product.enums.ProductImageConstants.MAX_IMAGE_ORDER;
import static com.music.sale.web.product.enums.ProductImageConstants.MIN_IMAGE_ORDER;

public record UploadProductImageMetaRequest(
        @NotNull(message = "썸네일 여부는 필수입니다.") boolean isThumbnail,
        @NotNull(message = "이미지 순서는 필수입니다.")
        @Min(value = MIN_IMAGE_ORDER, message = "이미지 순서는 " + MIN_IMAGE_ORDER + "부터 시작합니다.")
        @Max(value = MAX_IMAGE_ORDER, message = "이미지 순서는 최대 " + MAX_IMAGE_ORDER + "까지 가능합니다.")
        int imageOrder
) {}
