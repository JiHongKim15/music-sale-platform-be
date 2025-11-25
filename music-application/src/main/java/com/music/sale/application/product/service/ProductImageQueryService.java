package com.music.sale.application.product.service;

import com.music.sale.application.product.dto.output.ProductImageOutput;
import com.music.sale.application.product.port.inport.ProductImageQueryUseCase;
import com.music.sale.application.product.port.outport.ProductImageQueryPort;
import com.music.sale.domain.product.ProductImage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProductImageQueryService implements ProductImageQueryUseCase {

    private final ProductImageQueryPort productImageQueryPort;

    public List<ProductImageOutput> getImagesByProductId(Long productId) {
        List<ProductImage> images = productImageQueryPort.findImagesByProductId(productId);
        return images.stream()
                .map(this::toOutput)
                .collect(Collectors.toList());
    }

    private ProductImageOutput toOutput(ProductImage image) {
        return new ProductImageOutput(
                image.getId(),
                image.getProductId(),
                image.getUrl(),
                image.isThumbnail(),
                image.getImageOrder(),
                image.getFileSize(),
                image.getFileName(),
                image.getFileType()
        );
    }
}

