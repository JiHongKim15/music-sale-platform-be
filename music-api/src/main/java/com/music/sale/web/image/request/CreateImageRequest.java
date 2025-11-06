package com.music.sale.web.image.request;

import java.util.List;
import org.springframework.web.multipart.MultipartFile;

public class CreateImageRequest {
    private List<MultipartFile> files;

    public CreateImageRequest() {}

    public CreateImageRequest(List<MultipartFile> files) {
        this.files = files;
    }

    public List<MultipartFile> getFiles() {
        return files;
    }

    public void setFiles(List<MultipartFile> files) {
        this.files = files;
    }
}
