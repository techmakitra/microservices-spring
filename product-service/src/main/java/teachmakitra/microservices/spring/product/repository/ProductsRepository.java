package teachmakitra.microservices.spring.product.repository;

import io.opentelemetry.api.trace.Span;
import io.opentelemetry.extension.annotations.WithSpan;
import org.springframework.stereotype.Repository;
import teachmakitra.microservices.spring.product.model.ProductModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ProductsRepository {

    private static final long MAX_COUNT = 1000;

    @WithSpan
    public Optional<ProductModel> findById(String id) {
        if (Long.parseLong(id) >= MAX_COUNT) {
            return Optional.empty();
        }
        return Optional.of(ProductModel.builder()
                                   .id(id)
                                   .title("Product " + id)
                                   .description("Product " + id)
                                   .build());
    }

    public List<ProductModel> findAllProducts(long offset, long limit) {
        long maxId = Math.min(MAX_COUNT, offset + limit);
        List<ProductModel> products = new ArrayList<>();
        for (long id = offset; id < maxId; id++) {
            products.add(ProductModel.builder()
                                 .id(Long.toString(id))
                                 .title("Product " + id)
                                 .description("The best product " + id)
                                 .build());
        }
        return products;
    }

    public long countAllProducts() {
        return MAX_COUNT;
    }
}
