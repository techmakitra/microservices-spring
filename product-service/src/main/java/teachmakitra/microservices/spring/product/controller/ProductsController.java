package teachmakitra.microservices.spring.product.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import teachmakitra.microservices.spring.product.dto.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductsController {

    private static final long MAX_COUNT = 1000;

    @GetMapping("/products/{productId}")
    public ResponseDto<ProductDetailsDto> findById(@PathVariable("productId") String productId) {
        return ResponseDto.of(ProductDetailsDto
                .builder()
                .id(productId)
                .title("Product " + productId)
                .description("Product " + productId)
                .build());
    }

    @GetMapping("/products")
    public ListResponseDto<ProductDetailsDto> getProducts(
            @RequestParam(value = "offset", defaultValue = "0") Long offset,
            @RequestParam(value = "limit", defaultValue = "100") Long limit) {
        List<ProductDetailsDto> products = new ArrayList<>();
        long maxId = Math.min(MAX_COUNT, offset + limit);
        for (long id = offset; id < maxId; id++) {
            products.add(ProductDetailsDto.builder()
                    .id(Long.toString(id))
                    .title("Product " + id)
                    .description("Product " + id)
                    .build());
        }
        return ListResponseDto.of(products, offset, limit, MAX_COUNT);
    }
}
