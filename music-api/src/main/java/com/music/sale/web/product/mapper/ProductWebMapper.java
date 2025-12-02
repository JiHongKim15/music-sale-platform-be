package com.music.sale.web.product.mapper;

import com.music.sale.application.product.dto.output.ProductOutput;
import com.music.sale.web.product.response.ProductResponse;
import org.springframework.stereotype.Component;

@Component
public class ProductWebMapper {

  public ProductResponse toProductResponse(ProductOutput output) {
    return ProductResponse.builder()
        .id(output.id())
        .title(output.name())
        .content(output.description())
        .storeName(null)
        .sellerId(output.sellerId())
        .storeId(output.storeId())
        .build();
  }
}
