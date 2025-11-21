package com.music.sale.web.product.mapper;

import java.io.IOException;
import java.util.List;
import java.util.stream.IntStream;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.music.sale.application.product.dto.input.UpdateProductImageInput;
import com.music.sale.web.product.request.ProductImageMetaRequest;

@Component
public class ProductImageWebMapper {

    public List<UpdateProductImageInput> toUploadImageInputs(Long productId, List<MultipartFile> files, List<ProductImageMetaRequest> metas) {
        return IntStream.range(0, files.size())
                .mapToObj(i -> toUploadImageInput(productId, files.get(i), metas.get(i)))
                .toList();
    }

    private UpdateProductImageInput toUploadImageInput(Long productId, MultipartFile file, ProductImageMetaRequest meta) {
        try {
            return new UpdateProductImageInput(
                    productId,
                    file.getOriginalFilename(),
                    file.getContentType(),
                    file.getSize(),
                    meta.isThumbnail(),
                    meta.imageOrder(),
                    file.getBytes()
            );
        } catch (IOException e) {
            throw new RuntimeException("파일 읽기에 실패했습니다: " + file.getOriginalFilename(), e);
        }
    }
}

