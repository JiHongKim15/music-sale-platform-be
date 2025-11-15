import com.music.sale.domain.product.enums.ProductCondition;
import com.music.sale.domain.product.enums.ProductConditionGrade;
import com.music.sale.domain.product.enums.ProductStatus;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;
import java.util.Map;

@Value
@Builder
public class ProductOutput {
    Long id;
    Long catalogId;
    Long sellerId;
    Long storeId;

    String name;
    String brand;
    Long price;

    ProductCondition condition;
    ProductConditionGrade conditionGrade;
    int stockQuantity;
    ProductStatus status;

    Map<String, Object> attributes;
    String description;
    Long viewCount;

    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}
