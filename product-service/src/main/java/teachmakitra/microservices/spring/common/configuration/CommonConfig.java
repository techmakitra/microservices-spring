package teachmakitra.microservices.spring.common.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({
        RestConfig.class,
        TracingConfig.class
})
public class CommonConfig {
}
