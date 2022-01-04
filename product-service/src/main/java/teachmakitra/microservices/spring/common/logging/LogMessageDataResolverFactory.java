package teachmakitra.microservices.spring.common.logging;

import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginFactory;
import org.apache.logging.log4j.layout.template.json.resolver.*;

@Plugin(name = "LogMessageDataResolverFactory", category = TemplateResolverFactory.CATEGORY)
public class LogMessageDataResolverFactory implements EventResolverFactory {

    private static final LogMessageDataResolverFactory INSTANCE = new LogMessageDataResolverFactory();

    @PluginFactory
    public static LogMessageDataResolverFactory getInstance() {
        return INSTANCE;
    }

    @Override
    public String getName() {
        return "logMessageData";
    }

    @Override
    public TemplateResolver<LogEvent> create(EventResolverContext context, TemplateResolverConfig config) {
        return new LogMessageDataResolver();
    }
}
