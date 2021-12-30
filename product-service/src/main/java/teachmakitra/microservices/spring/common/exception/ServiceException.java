package teachmakitra.microservices.spring.common.exception;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Singular;
import lombok.Value;
import org.springframework.http.HttpStatus;
import teachmakitra.microservices.spring.common.dto.ProblemDto;

import java.util.*;

@Value
@EqualsAndHashCode(callSuper = true)
public class ServiceException extends RuntimeException {
    HttpStatus status;
    Map<String, String> attributes;
    List<Problem> problems;

    @Builder
    public ServiceException(String message,
                            Throwable cause,
                            Map<String, String> attributes,
                            HttpStatus status,
                            List<Problem> problems) {
        super(message, cause);
        this.status = status;
        this.problems = problems;
        this.attributes = attributes;
    }

    public static class ServiceExceptionBuilder {

        ServiceExceptionBuilder() {
            this.attributes = new LinkedHashMap<>();
            this.problems = new ArrayList<>();
        }

        public ServiceException build() {
            return new ServiceException(
                    this.message,
                    this.cause,
                    Collections.unmodifiableMap(this.attributes),
                    this.status,
                    Collections.unmodifiableList(this.problems));
        }

        public ServiceExceptionBuilder attribute(String name, Object value) {
            this.attributes.put(name, String.valueOf(value));
            return this;
        }
    }

    public static ServiceException.ServiceExceptionBuilder notFound() {
        return status(HttpStatus.NOT_FOUND);
    }

    public static ServiceException.ServiceExceptionBuilder internalError() {
        return status(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public static ServiceException.ServiceExceptionBuilder status(HttpStatus status) {
        return ServiceException.builder().status(status);
    }


}
