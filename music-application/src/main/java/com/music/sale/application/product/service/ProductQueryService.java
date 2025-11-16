package com.music.sale.application.product.service;

import com.music.sale.application.product.dto.ProductOutput;
import com.music.sale.application.product.mapper.ProductMapper;
import com.music.sale.application.product.port.inport.ProductQueryUseCase;
import com.music.sale.application.product.port.outport.ProductCommandPort;
import com.music.sale.application.product.port.outport.ProductQueryPort;
import com.music.sale.domain.product.ProductItem;
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
    public ProductOutput getById(Long productId) {
        ProductItem item = queryPort.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("상품을 찾을 수 없습니다. id=" + productId));
        productCommandPort.increaseViewCount(productId);

        ProductItem incremented = item.toBuilder()
                .viewCount(item.getViewCount() + 1)
                .build();

        return mapper.toOutput(incremented);
    }

    @Override
    public Page<ProductOutput> getBySeller(Long sellerId, Pageable pageable) {
        return queryPort.findBySellerId(sellerId, pageable)
                .map(mapper::toOutput);
    }

    @Override
    public Page<ProductOutput> getByStore(Long storeId, Pageable pageable) {
        return queryPort.findByStoreId(storeId, pageable)
                .map(mapper::toOutput);
    }

    @Override
    public Page<ProductOutput> searchByName(String keyword, Pageable pageable) {
        return queryPort.searchByName(keyword, pageable)
                .map(mapper::toOutput);
    }
}
