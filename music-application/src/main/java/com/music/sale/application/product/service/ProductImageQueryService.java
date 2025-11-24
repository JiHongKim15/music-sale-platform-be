package com.music.sale.application.product.service;

import com.music.sale.application.product.port.inport.ProductImageQueryUseCase;
import com.music.sale.application.product.port.outport.ProductImageQueryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProductImageQueryService implements ProductImageQueryUseCase {

    private final ProductImageQueryPort productImageQueryPort;

    public List<ProductImageQueryOutPut> getImagesByProductId(Long productId) {
        return productImageQueryPort.findImagesByProductId(productId);
    }

}

