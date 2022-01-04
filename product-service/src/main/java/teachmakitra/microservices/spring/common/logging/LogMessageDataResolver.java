package teachmakitra.microservices.spring.common.logging;


import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.layout.template.json.resolver.EventResolver;
import org.apache.logging.log4j.layout.template.json.util.JsonWriter;
import org.apache.logging.log4j.message.Message;

public class LogMessageDataResolver implements EventResolver {

    @Override
    public void resolve(LogEvent value, JsonWriter jsonWriter) {
        Message message = value.getMessage();
        if (message instanceof LogMessage logMessage) {
            jsonWriter.writeObject(logMessage.getData());
        } else {
            jsonWriter.writeObjectStart();
            jsonWriter.writeObjectEnd();
        }
    }
}
