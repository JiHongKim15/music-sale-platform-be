package com.music.sale.application.image.service;

import com.music.sale.application.image.dto.ImageOutput;
import com.music.sale.application.image.dto.UploadImageInput;
import com.music.sale.application.image.port.inport.ImageUseCase;
import com.music.sale.application.image.port.outport.ImagePort;
import com.music.sale.domain.image.ProductImage;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ImageService implements ImageUseCase {

    private final ImagePort imagePort;

    @Override
    public List<ImageOutput> uploadImage(List<UploadImageInput> inputs) {
        // 저장 (Adapter에 위임)
        List<ProductImage> saved = imagePort.saveAll(inputs);

        // 저장된 ID 포함해서 결과 반환
        return zipToOutput(saved, inputs);
    } // uploadImage

    private List<ImageOutput> zipToOutput(List<ProductImage> saved, List<UploadImageInput> inputs) {
        if (saved.size() != inputs.size()) {
            throw new IllegalStateException("저장된 이미지 수와 요청 수가 일치하지 않습니다.");
        }

        List<ImageOutput> result = new java.util.ArrayList<>();
        for (int i = 0; i < saved.size(); i++) {
            ProductImage image = saved.get(i);
            UploadImageInput input = inputs.get(i);

            result.add(new ImageOutput(
                image.id(),
                input.productId(),
                "/url/url/to/image/",
//                image.get url(), // repo에서 id로 url찾아 가져오기
                input.isThumbnail(),
                input.imageOrder(),
                input.fileSize(),
                input.fileName(),
                input.fileType()
            ));
        }
        return result;
    } // zipToOutput


} // class
