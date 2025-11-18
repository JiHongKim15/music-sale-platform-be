package com.music.sale.application.image.port.inport;

import com.music.sale.application.image.dto.ImageOutput;
import com.music.sale.application.image.dto.UploadImageInput;
import java.util.List;

public interface ImageUseCase {
    List<ImageOutput> uploadImage(List<UploadImageInput> inputs);
    void deleteImage(Long productId, Long imageId);

}
