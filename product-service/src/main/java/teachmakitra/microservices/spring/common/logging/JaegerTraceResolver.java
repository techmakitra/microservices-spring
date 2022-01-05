package teachmakitra.microservices.spring.common.logging;

import io.opentracing.Span;
import io.opentracing.SpanContext;

import io.opentracing.Tracer;
import io.opentracing.util.GlobalTracer;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.layout.template.json.resolver.EventResolver;
import org.apache.logging.log4j.layout.template.json.util.JsonWriter;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

public class JaegerTraceResolver implements EventResolver {

    @Override
    public boolean isResolvable(LogEvent event) {
        return GlobalTracer.get() != null && GlobalTracer.get().activeSpan() != null;
    }

    @Override
    public boolean isFlattening() {
        return true;
    }

    @Override
    public void resolve(LogEvent value, JsonWriter jsonWriter) {
        Tracer tracer = GlobalTracer.get();
        Span span = tracer.activeSpan();

        if (span == null) {
            return;
        }
        StringBuilder sb = jsonWriter.getStringBuilder();
        if (sb.length() > 0 && sb.charAt(sb.length() - 1) != '{') {
            jsonWriter.writeSeparator();
        }
        jsonWriter.writeObjectKey("traceId");
        jsonWriter.writeString(span.context().toTraceId());
        jsonWriter.writeSeparator();
        jsonWriter.writeObjectKey("spanId");
        jsonWriter.writeString(span.context().toTraceId());
    }
}
