package com.music.sale.application.image.service;

import com.music.sale.application.image.dto.ImageQueryOutPut;
import com.music.sale.application.image.port.inport.ImageQueryUseCase;
import com.music.sale.application.image.port.outport.ImageQueryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ImageQueryService implements ImageQueryUseCase {

    private final ImageQueryPort imageQueryPort;

    public List<ImageQueryOutPut> getImagesByProductId(Long productId) {
        return imageQueryPort.findImagesByProductId(productId);
    }

}

