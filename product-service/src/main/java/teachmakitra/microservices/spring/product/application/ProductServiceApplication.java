package teachmakitra.microservices.spring.product.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

@EnableAutoConfiguration
public class ProductServiceApplication {

    public static void main(String[] args) {
       SpringApplication.run(ProductServiceApplication.class, args);
    }


}
