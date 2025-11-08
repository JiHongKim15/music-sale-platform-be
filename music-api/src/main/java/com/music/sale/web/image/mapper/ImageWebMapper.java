package com.music.sale.web.image.mapper;

import com.music.sale.application.image.dto.UploadImageInput;
import com.music.sale.web.image.request.ImageMetaRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

/**
 * 바인딩 데이터를 그대로
 * */
@Component
public class ImageWebMapper {

    public List<UploadImageInput> toUploadImageInputs(List<MultipartFile> files, Long productId, List<ImageMetaRequest> metas) {
        List<UploadImageInput> result = new ArrayList<>();
        for (int i = 0; i < files.size(); i++) {
            MultipartFile file = files.get(i);
            ImageMetaRequest meta = metas.get(i);

            try {
                result.add(new UploadImageInput(
                    file.getOriginalFilename(),
                    file.getBytes(),
                    file.getContentType(),
                    file.getSize(),
                    meta.isThumbnail(),
                    meta.imageOrder()
                ));
            } catch (IOException e) {
                throw new RuntimeException("파일 읽기에 실패했습니다: " + file.getOriginalFilename(), e);
            }
        }
        return result;
    }

} // class
