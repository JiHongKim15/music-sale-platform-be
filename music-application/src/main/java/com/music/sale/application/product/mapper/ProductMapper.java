package com.music.sale.application.product.mapper;

import com.music.sale.application.product.dto.input.CreateProductInput;
import com.music.sale.application.product.dto.input.UpdateProductImageInput;
import com.music.sale.application.product.dto.input.UpdateProductInput;
import com.music.sale.application.product.dto.output.ProductImageOutput;
import com.music.sale.application.product.dto.output.ProductOutput;
import com.music.sale.domain.product.ProductImage;
import com.music.sale.domain.product.ProductItem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductMapper {
  public ProductItem toDomainForCreate(CreateProductInput input, Long createdBy) {
    return ProductItem.builder()
        .catalogId(input.catalogId())
        .sellerId(input.sellerId())
        .storeId(input.storeId())
        .name(input.name())
        .brand(input.brand())
        .price(input.price())
        .condition(input.condition())
        .conditionGrade(input.conditionGrade())
        .stockQuantity(input.stockQuantity())
        .status(input.status())
        .attributes(input.attributes())
        .description(input.description())
        .viewCount(0L)
        .build();
  }

  public ProductItem toDomainForUpdate(ProductItem existing, UpdateProductInput input) {
    return existing.toBuilder()
        .name(getValueOrDefault(input.name(), existing.getName()))
        .brand(getValueOrDefault(input.brand(), existing.getBrand()))
        .catalogId(getValueOrDefault(input.catalogId(), existing.getCatalogId()))
        .storeId(getValueOrDefault(input.storeId(), existing.getStoreId()))
        .price(getValueOrDefault(input.price(), existing.getPrice()))
        .condition(getValueOrDefault(input.condition(), existing.getCondition()))
        .conditionGrade(getValueOrDefault(input.conditionGrade(), existing.getConditionGrade()))
        .stockQuantity(getValueOrDefault(input.stockQuantity(), existing.getStockQuantity()))
        .status(getValueOrDefault(input.status(), existing.getStatus()))
        .attributes(getValueOrDefault(input.attributes(), existing.getAttributes()))
        .description(getValueOrDefault(input.description(), existing.getDescription()))
        .build();
  }

  private <T> T getValueOrDefault(T value, T defaultValue) {
    return value != null ? value : defaultValue;
  }

  public ProductOutput toOutput(ProductItem item) {
    return new ProductOutput(
        item.getId(),
        item.getCatalogId(),
        item.getSellerId(),
        item.getStoreId(),
        item.getName(),
        item.getBrand(),
        item.getPrice(),
        item.getCondition(),
        item.getConditionGrade(),
        item.getStockQuantity(),
        item.getStatus(),
        item.getAttributes(),
        item.getDescription(),
        item.getViewCount());
  }

  public ProductImageOutput toProductImageOutput(ProductImage image) {
    return new ProductImageOutput(
        image.getId(),
        image.getProductId(),
        image.getUrl(),
        image.isThumbnail(),
        image.getImageOrder(),
        image.getFileSize(),
        image.getFileName(),
        image.getFileType());
  }

  public ProductImageOutput toProductImageOutput(
      ProductImage savedImage, UpdateProductImageInput input) {
    return new ProductImageOutput(
        savedImage.getId(),
        savedImage.getProductId(),
        savedImage.getUrl(),
        savedImage.isThumbnail(),
        savedImage.getImageOrder(),
        input.fileSize(),
        input.fileName(),
        input.fileType());
  }
}
