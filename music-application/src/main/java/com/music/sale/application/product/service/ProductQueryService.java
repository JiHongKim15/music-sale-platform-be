package com.music.sale.application.product.service;

import com.music.sale.application.product.dto.output.GetProductOutput;
import com.music.sale.application.product.mapper.ProductMapper;
import com.music.sale.application.product.port.inport.ProductQueryUseCase;
import com.music.sale.application.product.port.outport.ProductCommandPort;
import com.music.sale.application.product.port.outport.ProductQueryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductQueryService implements ProductQueryUseCase {
    private final ProductQueryPort queryPort;
    private final ProductCommandPort productCommandPort;
    private final ProductMapper mapper;


    @Override
    @Transactional
    public GetProductOutput getByProductId(Long productId) {
        return queryPort.findByProductId(productId)
                .map(mapper::toGetOutput)
                .orElseThrow(() -> new IllegalArgumentException("상품을 찾을 수 없습니다. productId=" + productId));
    }

    @Override
    public Page<GetProductOutput> getBySellerId(Long sellerId, Pageable pageable) {
        return queryPort.findBySellerId(sellerId, pageable)
                .map(mapper::toGetOutput);
    }

    @Override
    public Page<GetProductOutput> getByStoreId(Long storeId, Pageable pageable) {
        return queryPort.findByStoreId(storeId, pageable)
                .map(mapper::toGetOutput);
    }

    @Override
    public Page<GetProductOutput> searchByKeyword(String keyword, Pageable pageable) {
        return queryPort.searchByKeyword(keyword, pageable)
                .map(mapper::toGetOutput);
    }
}
