package com.music.sale.web.product.request;

import static com.music.sale.web.product.enums.ProductImageConstants.*;

import com.music.sale.web.product.enums.ProductImageContentType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UploadProductImageRequest {

  @NotEmpty(message = "이미지 파일을 최소 " + MIN_IMAGE_ORDER + "개 이상 업로드해주세요.")
  @Size(max = MAX_IMAGE_ORDER, message = "이미지는 최대 " + MAX_IMAGE_ORDER + "개까지 업로드할 수 있습니다.")
  private List<MultipartFile> images;

  @NotEmpty(message = "메타데이터는 최소 " + MIN_IMAGE_ORDER + "개 이상 포함되어야 합니다.")
  @Size(max = MAX_IMAGE_ORDER, message = "메타데이터는 최대 " + MAX_IMAGE_ORDER + "개까지 허용됩니다.")
  @Valid
  private List<UploadProductImageMetaRequest> meta;

  public void validate() {
    if (images.size() != meta.size()) {
      throw new IllegalArgumentException("파일과 메타정보의 개수가 일치하지 않습니다.");
    }

    for (MultipartFile image : images) {
      validateFile(image);
    }
  }

  private void validateFile(MultipartFile file) {
    if (file.isEmpty()) {
      throw new IllegalArgumentException("빈 파일은 업로드할 수 없습니다.");
    }

    String contentType = file.getContentType();
    if (!ProductImageContentType.isSupported(contentType)) {
      throw new IllegalArgumentException(
          String.format(
              "지원하지 않는 이미지 형식입니다. 허용 타입: %s", ProductImageContentType.getSupportedTypesString()));
    }

    if (file.getSize() > MAX_FILE_SIZE) {
      throw new IllegalArgumentException(
          String.format("이미지 파일 크기는 최대 %dMB까지 허용됩니다.", MAX_FILE_SIZE));
    }
  }
}
