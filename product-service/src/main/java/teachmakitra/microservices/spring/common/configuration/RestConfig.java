package teachmakitra.microservices.spring.common.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import teachmakitra.microservices.spring.common.exception.ServiceExceptionHandler;

@Configuration
public class RestConfig {

    @Bean
    ServiceExceptionHandler serviceExceptionHandler() {
        return new ServiceExceptionHandler();
    }
}
