package teachmakitra.microservices.spring.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import teachmakitra.microservices.spring.common.configuration.CommonConfig;
import teachmakitra.microservices.spring.common.configuration.RestConfig;

@SpringBootApplication
@Import({
        CommonConfig.class
})
public class ProductServiceApplication {

    public static void main(String[] args) {
       SpringApplication.run(ProductServiceApplication.class, args);
    }

}
