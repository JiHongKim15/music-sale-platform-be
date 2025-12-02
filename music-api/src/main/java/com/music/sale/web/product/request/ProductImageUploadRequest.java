package com.music.sale.web.product.request;

import java.util.List;
import org.springframework.web.multipart.MultipartFile;

public record ProductImageUploadRequest(
    List<MultipartFile> files, List<ProductImageMetaRequest> metas) {}
