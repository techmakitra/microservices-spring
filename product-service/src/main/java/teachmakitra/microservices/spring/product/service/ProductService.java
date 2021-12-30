package teachmakitra.microservices.spring.product.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import teachmakitra.microservices.spring.common.model.PageableResult;
import teachmakitra.microservices.spring.product.model.ProductModel;
import teachmakitra.microservices.spring.product.repository.ProductsRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    ProductsRepository productsRepository;

    private static final long MAX_COUNT = 1000;

    public Optional<ProductModel> findById(String id) {
        return productsRepository.findById(id);
    }

    public List<ProductModel> findAllProductsAsList(long offset, long limit) {
        return productsRepository.findAllProducts(offset, limit);
    }

    public PageableResult<ProductModel> findAllProductsAsPageable(long offset, long limit) {
        long totalCount = productsRepository.countAllProducts();
        List<ProductModel> products = productsRepository.findAllProducts(offset, limit);
        return PageableResult.create(offset, limit, totalCount, products);
    }


    public long countAllProducts() {
        return MAX_COUNT;
    }
}
