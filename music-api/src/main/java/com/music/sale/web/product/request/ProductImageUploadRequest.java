package com.music.sale.web.product.request;

import org.springframework.web.multipart.MultipartFile;
import java.util.List;

public record ProductImageUploadRequest(
        List<MultipartFile> files,
        List<ProductImageMetaRequest> metas
) {
}
