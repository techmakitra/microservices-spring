package teachmakitra.microservices.spring.common.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import teachmakitra.microservices.spring.common.tracing.CustomTracingTagsInterceptor;

@Configuration
public class TracingConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new CustomTracingTagsInterceptor());
    }
}
