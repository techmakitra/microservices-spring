package teachmakitra.microservices.spring.common.logging;

import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginFactory;
import org.apache.logging.log4j.layout.template.json.resolver.EventResolverContext;
import org.apache.logging.log4j.layout.template.json.resolver.EventResolverFactory;
import org.apache.logging.log4j.layout.template.json.resolver.TemplateResolverConfig;
import org.apache.logging.log4j.layout.template.json.resolver.TemplateResolverFactory;

@Plugin(name = "RandomUUIDResolverFactory", category = TemplateResolverFactory.CATEGORY)
public final class RandomUUIDResolverFactory implements EventResolverFactory {

    private static final RandomUUIDResolverFactory INSTANCE =
            new RandomUUIDResolverFactory();

    private RandomUUIDResolverFactory() {}

    @PluginFactory
    public static RandomUUIDResolverFactory getInstance() {
        return INSTANCE;
    }

    @Override
    public String getName() {
        return RandomUUIDResolver.getName();
    }

    @Override
    public RandomUUIDResolver create(
            final EventResolverContext context,
            final TemplateResolverConfig config) {
        return new RandomUUIDResolver(config);
    }

}