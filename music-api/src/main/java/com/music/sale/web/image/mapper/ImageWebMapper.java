package com.music.sale.web.image.mapper;

import com.music.sale.application.image.dto.UploadImageInput;
import com.music.sale.web.image.request.ImageMetaRequest;
import java.io.IOException;
import java.util.List;
import java.util.stream.IntStream;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class ImageWebMapper {

    public List<UploadImageInput> toUploadImageInputs(Long productId, List<MultipartFile> files, List<ImageMetaRequest> metas) {
        return IntStream.range(0, files.size())
                .mapToObj(i -> toUploadImageInput(productId, files.get(i), metas.get(i)))
                .toList();
    }

    private UploadImageInput toUploadImageInput(Long productId, MultipartFile file, ImageMetaRequest meta) {
        try {
            return new UploadImageInput(
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
