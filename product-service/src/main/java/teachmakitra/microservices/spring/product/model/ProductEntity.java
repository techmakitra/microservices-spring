package teachmakitra.microservices.spring.product.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ProductEntity {

    private String id;
    private String title;
    private String description;


}
