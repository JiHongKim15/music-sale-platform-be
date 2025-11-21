package com.music.sale.application.product.service;

import com.music.sale.application.product.port.inport.ProdcutImageQueryUseCase;
import com.music.sale.application.product.port.outport.ProductImageQueryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProdcutImageQueryService implements ProdcutImageQueryUseCase {

    private final ProductImageQueryPort productImageQueryPort;

    public List<ImageQueryOutPut> getImagesByProductId(Long productId) {
        return productImageQueryPort.findImagesByProductId(productId);
    }

}

