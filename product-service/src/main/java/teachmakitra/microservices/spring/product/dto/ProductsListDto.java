package teachmakitra.microservices.spring.product.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ProductsListDto {
    private List<ProductDetailsDto> products;
}
