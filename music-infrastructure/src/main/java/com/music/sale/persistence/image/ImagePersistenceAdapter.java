package com.music.sale.persistence.image;

import com.music.sale.application.image.dto.ImageOutput;
import com.music.sale.application.image.dto.ImageSaveResult;
import com.music.sale.application.image.dto.UploadImageInput;
import com.music.sale.application.image.port.outport.ImagePort;
import com.music.sale.domain.image.ProductImage;
import com.music.sale.persistence.image.entity.ProductImageEntity;
import com.music.sale.persistence.image.mapper.ImagePersistenceMapper;
import com.music.sale.persistence.image.repository.ImageRepository;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * ImagePort의 Infrastructure 구현체 (Adapter)
 * 1. URL 생성 책임 (현재는 Mock)
 * 2. ProductImage 영속화 책임 (JPA)
 */
@Repository
@RequiredArgsConstructor
public class ImagePersistenceAdapter implements ImagePort {

    private final ImageRepository imageRepository;
    private final ImagePersistenceMapper mapper;

    /**
     * 1. URL 생성 Mock 구현
     */
    @Override
    public String generateUrl(Long productId, String fileName) {
        String safeFileName = fileName.replaceAll("[^a-zA-Z0-9.-]", "_");
        return String.format("https://cdn.music-sale.com/products/%d/%s", productId, safeFileName);
    }

    /**
     * 2. DB 영속화 후 ID + URL 반환
     */
    @Override
    public List<ImageSaveResult> saveAll(List<UploadImageInput> inputs) {
        // 1. Entity 생성 (URL 포함)
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

        // 2. DB 저장
        List<ProductImageEntity> saved = imageRepository.saveAll(entities);

        // 3. ImageSaveResult로 변환 (ID + URL)
        return saved.stream()
                .map(mapper::toSaveResult)
                .collect(Collectors.toList());
    }

    @Override
    public boolean existsByIdAndProductId(Long imageId, Long productId) {
        return imageRepository.existsByIdAndProductId(imageId, productId);
    }

    @Override
    public void deleteById(Long imageId) {
        imageRepository.deleteById(imageId);
    }

    @Override
    @Transactional
    public List<ProductImage> updateThumbnail(Long productId, Long newThumbnailImageId, boolean isThumbnail) {
        List<ProductImageEntity> images = imageRepository.findAllByProductIdOrderByImageOrderAsc(productId);
        if (images.isEmpty()) {
            return List.of();
        }

        ProductImageEntity selected = images.stream()
            .filter(e -> e.getId().equals(newThumbnailImageId))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("image not found for product"));

        List<ProductImageEntity> reordered = new ArrayList<>();
        OffsetDateTime now = OffsetDateTime.now();

        // 선택된 이미지를 새 인스턴스로 생성 (order=1, isThumbnail=true)
        ProductImageEntity newThumbnail = new ProductImageEntity(
            selected.getId(),
            productId,
            selected.getUrl(),
            true,  // isThumbnail
            1,     // order
            selected.getFileSize(),
            selected.getFileName(),
            selected.getFileType(),
            selected.getCreatedAt(),
            now    // updatedAt
        );
        reordered.add(newThumbnail);

        // 나머지 이미지들을 새 인스턴스로 생성 (order=2..N, isThumbnail=false)
        int order = 2;
        for (ProductImageEntity e : images) {
            if (e.getId().equals(selected.getId())) continue;

            ProductImageEntity updated = new ProductImageEntity(
                e.getId(),
                productId,
                e.getUrl(),
                false,  // isThumbnail
                order++,
                e.getFileSize(),
                e.getFileName(),
                e.getFileType(),
                e.getCreatedAt(),
                now
            );
            reordered.add(updated);
        }

        // 저장 후 도메인 객체로 변환하여 반환
        List<ProductImageEntity> saved = imageRepository.saveAll(reordered);
        return saved.stream()
            .map(mapper::toDomain)
            .collect(Collectors.toList());
    }


} // class
