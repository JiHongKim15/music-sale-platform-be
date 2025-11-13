package com.music.sale.web.product;

import com.music.sale.application.product.dto.CreateProductInput;
import com.music.sale.application.product.dto.ProductOutput;
import com.music.sale.application.product.dto.UpdateProductInput;
import com.music.sale.application.product.port.inport.ProductCommandUseCase;
import com.music.sale.web.product.command.CreateProductCommand;
import com.music.sale.web.product.command.UpdateProductCommand;
import com.music.sale.web.product.mapper.ProductWebMapper;
import com.music.sale.web.product.response.ProductResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductCommandController {

    private final ProductCommandUseCase productCommandUseCase;
    private final ProductWebMapper productWebMapper;

    // 상품 등록
    @PostMapping
    public ProductResponse create(@RequestBody @Valid CreateProductCommand command) {
        Long currentUserId = command.getSellerId();

        CreateProductInput input = productWebMapper.toCreateInput(command);
        ProductOutput output = productCommandUseCase.create(input, currentUserId);

        return productWebMapper.toResponse(output);
    }

    // 상품 수정
    @PutMapping("/{productId}")
    public ProductResponse update(
            @PathVariable Long productId,
            @RequestBody @Valid UpdateProductCommand command
    ) {
        Long currentUserId = command.getUpdatedBy(); // 임시

        UpdateProductInput input = productWebMapper.toUpdateInput(command);
        ProductOutput output = productCommandUseCase.update(productId, input, currentUserId);

        return productWebMapper.toResponse(output);
    }

    // 상품 삭제
    @DeleteMapping("/{productId}")
    public void delete(@PathVariable Long productId,
                       @RequestParam Long currentUserId) { // or header / auth
        productCommandUseCase.delete(productId, currentUserId);
    }
}
