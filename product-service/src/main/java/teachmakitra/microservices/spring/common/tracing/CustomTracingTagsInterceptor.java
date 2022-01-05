package teachmakitra.microservices.spring.common.tracing;

import io.opentelemetry.api.trace.Span;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.InetAddress;
import java.util.concurrent.atomic.AtomicReference;

public class CustomTracingTagsInterceptor implements HandlerInterceptor {

    private AtomicReference<String> hostname = new AtomicReference<>();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        copyAttributeToTag(request, "X-TestRunID", "test.run_id");
        copyAttributeToTag(request, "X-TestCaseID", "test.case_id");
        return true;
    }

    private void copyAttributeToTag(HttpServletRequest request, String attributeName, String tagName) {
        String attributeValue = request.getHeader(attributeName);
        if (StringUtils.isNoneBlank(attributeValue)) {
           Span.current().setAttribute(tagName, StringUtils.substring(attributeValue, 0, 100));
        }
    }

}
