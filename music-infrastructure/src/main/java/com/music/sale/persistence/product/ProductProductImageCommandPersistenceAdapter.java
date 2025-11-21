package com.music.sale.persistence.product;

import com.music.sale.application.product.dto.ProductImageSaveResult;
import com.music.sale.application.product.dto.input.UpdateProductImageInput;
import com.music.sale.application.product.port.outport.ProductImageCommandPort;
import com.music.sale.domain.product.ProductImage;
import com.music.sale.persistence.product.entity.ProductImageEntity;
import com.music.sale.persistence.product.mapper.ProductImagePersistenceMapper;
import com.music.sale.persistence.product.repository.ProductImageCommandRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ProductProductImageCommandPersistenceAdapter implements ProductImageCommandPort {

    private final ProductImageCommandRepository productImageCommandRepository;
    private final ProductImagePersistenceMapper mapper;

    @Override
    public String generateUrl(Long productId, String fileName) {
        String safeFileName = fileName.replaceAll("[^a-zA-Z0-9.-]", "_");
        return String.format("https://cdn.music-sale.com/products/%d/%s", productId, safeFileName);
    }

    @Override
    public List<ProductImageSaveResult> saveAll(List<UpdateProductImageInput> inputs) {
        List<ProductImageEntity> entities = inputs.stream()
                .map(input -> {
                    String url = generateUrl(input.productId(), input.fileName());
                    ProductImage domain = new ProductImage(
                            null,
                            input.fileName(),
                            input.fileType(),
                            input.fileSize(),
                            input.isThumbnail(),
                            input.imageOrder()
                    );
                    return mapper.toEntity(domain, url, input.productId());
                })
                .collect(Collectors.toList());

        List<ProductImageEntity> saved = productImageCommandRepository.saveAll(entities);

        return saved.stream()
                .map(mapper::toSaveResult)
                .collect(Collectors.toList());
    }

    @Override
    public boolean existsByIdAndProductId(Long imageId, Long productId) {
        return productImageCommandRepository.existsByIdAndProductId(imageId, productId);
    }

    @Override
    public void deleteById(Long imageId) {
        productImageCommandRepository.deleteById(imageId);
    }

}
