package teachmakitra.microservices.spring.common.logging;

import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginFactory;
import org.apache.logging.log4j.layout.template.json.resolver.*;

@Plugin(name = "TraceResolverFactory", category = TemplateResolverFactory.CATEGORY)
public class JaegerTraceResolverFactory implements EventResolverFactory {

    private static final JaegerTraceResolverFactory INSTANCE = new JaegerTraceResolverFactory();

    @PluginFactory
    public static JaegerTraceResolverFactory getInstance() {
        return INSTANCE;
    }

    @Override
    public String getName() {
        return "jaegerTrace";
    }

    @Override
    public TemplateResolver<LogEvent> create(EventResolverContext context, TemplateResolverConfig config) {
        return new JaegerTraceResolver();
    }
}
