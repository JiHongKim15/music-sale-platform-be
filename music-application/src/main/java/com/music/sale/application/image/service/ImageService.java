package com.music.sale.application.image.service;

import com.music.sale.application.image.dto.ImageOutput;
import com.music.sale.application.image.dto.ImageSaveResult;
import com.music.sale.application.image.dto.UploadImageInput;
import com.music.sale.application.image.port.inport.ImageUseCase;
import com.music.sale.application.image.port.outport.ImagePort;
import java.util.List;
import java.util.stream.IntStream;

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
        // 저장 후 ID + URL 받기
        List<ImageSaveResult> saved = imagePort.saveAll(inputs);
        return zipToOutput(saved, inputs);
    }

    private List<ImageOutput> zipToOutput(
            List<ImageSaveResult> saved,
            List<UploadImageInput> inputs
    ) {
        if (saved.size() != inputs.size()) {
            throw new IllegalStateException("저장된 이미지 수와 요청 수가 일치하지 않습니다.");
        }

        return IntStream.range(0, saved.size())
                .mapToObj(i -> createImageOutput(saved.get(i), inputs.get(i)))
                .toList();
    }

    private ImageOutput createImageOutput(
            ImageSaveResult result,
            UploadImageInput input
    ) {
        return new ImageOutput(
                result.id(),
                input.productId(),
                result.url(),
                input.isThumbnail(),
                input.imageOrder(),
                input.fileSize(),
                input.fileName(),
                input.fileType()
        );
    }

}
