package com.music.sale.application.product.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.music.sale.application.product.dto.input.CreateProductInput;
import com.music.sale.application.product.dto.input.UpdateProductInput;
import com.music.sale.application.product.dto.output.ProductOutput;
import com.music.sale.application.product.mapper.ProductMapper;
import com.music.sale.application.product.port.inport.ProductCommandUseCase;
import com.music.sale.application.product.port.outport.ProductCommandPort;
import com.music.sale.application.product.port.outport.ProductQueryPort;
import com.music.sale.domain.product.ProductItem;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductCommandService implements ProductCommandUseCase {
    private final ProductCommandPort commandPort;
    private final ProductQueryPort queryPort;
    private final ProductMapper mapper;

    @Override
    public ProductOutput createProduct(CreateProductInput input, Long currentUserId) {
        ProductItem item = mapper.toDomainForCreate(input, currentUserId);
        ProductItem saved = commandPort.saveProduct(item);
        return mapper.toOutput(saved);
    }

    @Override
    public ProductOutput updateProduct(Long productId, UpdateProductInput input, Long currentUserId) {
        ProductItem existing = queryPort.findByProductId(productId)
                .orElseThrow(() -> new IllegalArgumentException("상품을 찾을 수 없습니다. productId=" + productId));

        if (!existing.getSellerId().equals(currentUserId)) {
            throw new IllegalArgumentException("수정 권한이 없습니다.");
        }

        ProductItem updated = mapper.toDomainForUpdate(existing, input);
        ProductItem saved = commandPort.updateProduct(updated);
        return mapper.toOutput(saved);
    }

    @Override
    public void deleteProduct(Long productId, Long currentUserId) {
        ProductItem existing = queryPort.findByProductId(productId)
                .orElseThrow(() -> new IllegalArgumentException("상품을 찾을 수 없습니다. productId=" + productId));

        if (!existing.getSellerId().equals(currentUserId)) {
            throw new IllegalArgumentException("삭제 권한이 없습니다.");
        }

        commandPort.deleteByProductId(productId);
    }
}
