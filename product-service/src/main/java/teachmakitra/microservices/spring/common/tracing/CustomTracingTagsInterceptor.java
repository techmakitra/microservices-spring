package teachmakitra.microservices.spring.common.tracing;

import io.opentracing.Span;
import io.opentracing.Tracer;
import io.opentracing.util.GlobalTracer;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.InetAddress;
import java.util.concurrent.atomic.AtomicReference;

public class CustomTracingTagsInterceptor implements HandlerInterceptor {

    private AtomicReference<String> hostname = new AtomicReference<>();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        Tracer tracer = GlobalTracer.get();
        Span span = tracer.activeSpan();
        span.setTag("hostname", resoveHostname());
        return true;
    }

    private String resoveHostname() {
        if (this.hostname.get() != null) {
            return this.hostname.get();
        }
        try {
            this.hostname.set(InetAddress.getLocalHost().getHostName());
            return this.hostname.get();
        } catch (Exception e) {
            return  "";
        }
    }
}
