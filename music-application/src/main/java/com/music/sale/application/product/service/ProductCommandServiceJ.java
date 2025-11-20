package com.music.sale.application.product.service;

import com.music.sale.application.product.dto.CreateProductInput;
import com.music.sale.application.product.dto.ProductOutputJ;
import com.music.sale.application.product.dto.UpdateProductInput;
import com.music.sale.application.product.mapper.ProductMapperJ;
import com.music.sale.application.product.port.inport.ProductCommandUseCaseJ;
import com.music.sale.application.product.port.outport.ProductCommandPortJ;
import com.music.sale.application.product.port.outport.ProductQueryPortJ;
import com.music.sale.domain.product.ProductItem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductCommandServiceJ implements ProductCommandUseCaseJ {
    private final ProductCommandPortJ commandPort;
    private final ProductQueryPortJ queryPort;
    private final ProductMapperJ mapper;

    @Override
    public ProductOutputJ createProduct(CreateProductInput input, Long currentUserId) {
        ProductItem item = mapper.toDomainForCreate(input, currentUserId);
        ProductItem saved = commandPort.saveProduct(item);
        return mapper.toOutput(saved);
    }

    @Override
    public ProductOutputJ updateProduct(Long productId, UpdateProductInput input, Long currentUserId) {
        ProductItem existing = queryPort.findByProductId(productId)
                .orElseThrow(() -> new IllegalArgumentException("상품을 찾을 수 없습니다. productId=" + productId));

        if (!existing.getSellerId().equals(currentUserId)) {
            throw new IllegalArgumentException("수정 권한이 없습니다.");
        }

        ProductItem updated = existing.toBuilder()
                .name(input.getName() != null ? input.getName() : existing.getName())
                .brand(input.getBrand() != null ? input.getBrand() : existing.getBrand())
                .storeId(input.getStoreId() != null ? input.getStoreId() : existing.getStoreId())
                .price(input.getPrice() != null ? input.getPrice() : existing.getPrice())
                .condition(input.getCondition() != null ? input.getCondition() : existing.getCondition())
                .conditionGrade(input.getConditionGrade() != null ? input.getConditionGrade() : existing.getConditionGrade())
                .stockQuantity(input.getStockQuantity() != null ? input.getStockQuantity() : existing.getStockQuantity())
                .status(input.getStatus() != null ? input.getStatus() : existing.getStatus())
                .attributes(input.getAttributes() != null ? input.getAttributes() : existing.getAttributes())
                .description(input.getDescription() != null ? input.getDescription() : existing.getDescription())
                .build();

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
