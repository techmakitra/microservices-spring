package teachmakitra.microservices.spring.common.logging;

import lombok.Getter;
import org.apache.logging.log4j.message.Message;
import org.apache.logging.log4j.util.StringBuilderFormattable;

import java.util.LinkedHashMap;
import java.util.Map;

@Getter
public class LogMessage implements Message, StringBuilderFormattable {

    private String message;
    @Getter
    private Map<String, Object> data = new LinkedHashMap<>();
    private boolean appendParameters = true;

    private LogMessage(String message) {
        this.message = message;
    }

    public static LogMessage withMessage(String message) {
        return new LogMessage(message);
    }

    public LogMessage appendParameters(boolean appendParametersFlag) {
        this.appendParameters = appendParametersFlag;
        return this;
    }

    public LogMessage value(String key, Object value) {
        this.data.put(key, value);
        return this;
    }

    public LogMessage value(Enum<?> key, String value) {
        this.data.put(key.name(), value);
        return this;
    }

    @Override
    public String getFormattedMessage() {
        StringBuilder buffer = new StringBuilder();
        formatTo(buffer);
        return buffer.toString();
    }

    @Override
    public void formatTo(StringBuilder buffer) {
        buffer.append(this.message);
        if (!this.data.isEmpty() && this.appendParameters) {
            buffer.append(":");
            for(Map.Entry<String, Object> parameter : this.data.entrySet()) {
                buffer.append(" ");
                buffer.append(parameter.getKey());
                buffer.append("=");
                buffer.append("'").append(parameter.getValue()).append("'");
            }
        }
    }

    @Override
    public String getFormat() {
        return getFormattedMessage();
    }

    @Override
    public Object[] getParameters() {
        return this.data.values().toArray(Object[]::new);
    }

    @Override
    public Throwable getThrowable() {
        return null;
    }


}
