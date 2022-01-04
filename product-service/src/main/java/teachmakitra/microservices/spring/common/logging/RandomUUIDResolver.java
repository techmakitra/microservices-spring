package teachmakitra.microservices.spring.common.logging;

import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.layout.template.json.resolver.EventResolver;
import org.apache.logging.log4j.layout.template.json.resolver.TemplateResolverConfig;
import org.apache.logging.log4j.layout.template.json.util.JsonWriter;

import java.util.UUID;

public final class RandomUUIDResolver implements EventResolver {

    RandomUUIDResolver(final TemplateResolverConfig config) {

    }

    static String getName() {
        return "randomUUID";
    }

    @Override
    public void resolve(
            final LogEvent value,
            final JsonWriter jsonWriter) {
        jsonWriter.writeString(UUID.randomUUID().toString());
    }

}
