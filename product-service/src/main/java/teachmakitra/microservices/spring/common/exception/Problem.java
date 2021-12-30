package teachmakitra.microservices.spring.common.exception;

import lombok.Builder;
import lombok.Singular;
import lombok.Value;

import java.util.Map;

@Value
@Builder
public class Problem {
    String code;
    String message;
    @Singular
    Map<String,String> parameters;
}
