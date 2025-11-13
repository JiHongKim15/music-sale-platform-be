package com.music.sale.application.product.service;

import com.music.sale.application.product.dto.CreateProductInput;
import com.music.sale.application.product.dto.ProductOutput;
import com.music.sale.application.product.dto.UpdateProductInput;
import com.music.sale.application.product.mapper.ProductMapper;
import com.music.sale.application.product.port.inport.ProductCommandUseCase;
import com.music.sale.application.product.port.outport.ProductCommandPort;
import com.music.sale.application.product.port.outport.ProductQueryPort;
import com.music.sale.domain.product.ProductItem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductCommandService implements ProductCommandUseCase {
    private final ProductCommandPort commandPort;
    private final ProductQueryPort queryPort;   // update 시 조회용
    private final ProductMapper mapper;

    @Override
    public ProductOutput create(CreateProductInput input, Long currentUserId) {
        ProductItem item = mapper.toDomainForCreate(input, currentUserId);
        ProductItem saved = commandPort.save(item);
        return mapper.toOutput(saved);
    }

    @Override
    public ProductOutput update(Long productId, UpdateProductInput input, Long currentUserId) {
        ProductItem existing = queryPort.findById(currentUserId)
                .orElseThrow(() -> new IllegalArgumentException("상품을 찾을 수 없습니다. id=" + currentUserId));

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
                .updatedBy(input.getUpdatedBy() != null ? input.getUpdatedBy() : existing.getUpdatedBy())
                .build();

        ProductItem saved = commandPort.update(updated);
        return mapper.toOutput(saved);
    }

    @Override
    public void delete(Long productId, Long currentUserId) {
        commandPort.deleteById(productId);
    }
}
