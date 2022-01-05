package teachmakitra.microservices.spring.product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import teachmakitra.microservices.spring.common.dto.ListResponseDto;
import teachmakitra.microservices.spring.common.dto.ResponseDto;
import teachmakitra.microservices.spring.common.exception.ServiceException;
import teachmakitra.microservices.spring.common.model.PageableResult;
import teachmakitra.microservices.spring.product.dto.ProductDto;
import teachmakitra.microservices.spring.product.enums.ProductErrors;
import teachmakitra.microservices.spring.product.enums.ProductServiceErrors;
import teachmakitra.microservices.spring.product.mapper.ProductModelMapper;
import teachmakitra.microservices.spring.product.model.ProductModel;
import teachmakitra.microservices.spring.product.service.ProductService;

import java.util.List;

@RestController
public class ProductsController {

    @Autowired
    ProductService productService;

    @GetMapping("/products/{productId}")
    public ResponseDto<ProductDto> findById(@PathVariable("productId") String productId) {
        ProductModel product =
                productService.findById(productId)
                              .orElseThrow(() -> ServiceException.notFound()
                                                                 .message("Product can't be found")
                                                                 .code(ProductServiceErrors.PRODUCT_NOT_FOUND)
                                                                 .error(ProductErrors.UNKNOWN_ID)
                                                                 .attribute("productId", productId)
                                                                 .build());
        ProductDto productDetails = ProductModelMapper.INSTANCE.toProductDto(product);
        return ResponseDto.of(productDetails);
    }

    @GetMapping("/products")
    public ListResponseDto<ProductDto> getProducts(
            @RequestParam(value = "offset", defaultValue = "0") Long offset,
            @RequestParam(value = "limit", defaultValue = "100") Long limit) {
        PageableResult<ProductModel> products = productService.findAllProductsAsPageable(offset, limit);

        List<ProductDto> productDtoList = products.getData()
                                                  .stream()
                                                  .map(ProductModelMapper.INSTANCE::toProductDto)
                                                  .toList();

        return ListResponseDto.of(productDtoList, offset, limit, products.getTotalCount());
    }
}
