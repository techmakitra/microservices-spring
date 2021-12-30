package teachmakitra.microservices.spring.common.exception;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@Builder
public class ProblemLog {
    private String httpStatus;
    private String code;
    private String message;
    private List<String> errors;
    private Map<String, String> attributes;

    @Override
    public String toString() {
        return "%s/%s %s - %s: %s".formatted(httpStatus, code, errors, message, attributes);
    }
}
