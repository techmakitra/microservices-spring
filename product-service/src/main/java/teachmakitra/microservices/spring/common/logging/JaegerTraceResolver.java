package teachmakitra.microservices.spring.common.logging;

import io.opentracing.SpanContext;
import io.opentracing.contrib.web.servlet.filter.TracingFilter;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.layout.template.json.resolver.EventResolver;
import org.apache.logging.log4j.layout.template.json.util.JsonWriter;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

public class JaegerTraceResolver implements EventResolver {

    @Override
    public boolean isResolvable(LogEvent event) {
        return findSpanContext() != null;
    }

    @Override
    public boolean isFlattening() {
        return true;
    }

    @Override
    public void resolve(LogEvent value, JsonWriter jsonWriter) {
        SpanContext spanContext = findSpanContext();
        if (spanContext == null) {
            return;
        }
        StringBuilder sb = jsonWriter.getStringBuilder();
        if (sb.length() > 0 && sb.charAt(sb.length() - 1) != '{') {
            jsonWriter.writeSeparator();
        }
        jsonWriter.writeObjectKey("traceId");
        jsonWriter.writeString(spanContext.toTraceId());
        jsonWriter.writeSeparator();
        jsonWriter.writeObjectKey("spanId");
        jsonWriter.writeString(spanContext.toSpanId());
    }


    private SpanContext findSpanContext() {
        RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
        if (attributes == null) {
            return null;
        }
        return (SpanContext) attributes.getAttribute(TracingFilter.SERVER_SPAN_CONTEXT,
                                                                RequestAttributes.SCOPE_REQUEST);
    }
}
