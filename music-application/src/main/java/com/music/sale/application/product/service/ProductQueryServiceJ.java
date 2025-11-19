package com.music.sale.application.product.service;

import com.music.sale.application.product.dto.ProductOutputJ;
import com.music.sale.application.product.mapper.ProductMapperJ;
import com.music.sale.application.product.port.inport.ProductQueryUseCaseJ;
import com.music.sale.application.product.port.outport.ProductCommandPortJ;
import com.music.sale.application.product.port.outport.ProductQueryPortJ;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductQueryServiceJ implements ProductQueryUseCaseJ {
    private final ProductQueryPortJ queryPort;
    private final ProductCommandPortJ productCommandPortJ;
    private final ProductMapperJ mapper;


    @Override
    @Transactional
    public ProductOutputJ getByProductId(Long productId) {
        return queryPort.findByProductId(productId)
                .map(mapper::toOutput)
                .orElseThrow(() -> new IllegalArgumentException("상품을 찾을 수 없습니다. productId=" + productId));
    }

    @Override
    public Page<ProductOutputJ> getBySellerId(Long sellerId, Pageable pageable) {
        return queryPort.findBySellerId(sellerId, pageable)
                .map(mapper::toOutput);
    }

    @Override
    public Page<ProductOutputJ> getByStoreId(Long storeId, Pageable pageable) {
        return queryPort.findByStoreId(storeId, pageable)
                .map(mapper::toOutput);
    }

    @Override
    public Page<ProductOutputJ> searchByKeyword(String keyword, Pageable pageable) {
        return queryPort.searchByKeyword(keyword, pageable)
                .map(mapper::toOutput);
    }
}
