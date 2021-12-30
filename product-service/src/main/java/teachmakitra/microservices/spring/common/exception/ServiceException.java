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
    boolean isCritical;
    ErrorCode code;
    Map<String, String> attributes;
    List<ErrorCode> errors;

    @Builder
    public ServiceException(String message,
                            Throwable cause,
                            ErrorCode code,
                            Map<String, String> attributes,
                            HttpStatus status,
                            List<ErrorCode> errors,
                            boolean isCritical) {
        super(message, cause);
        this.status = status;
        this.attributes = attributes;
        this.errors = errors;
        this.code = code;
        this.isCritical = isCritical;
    }

    public static class ServiceExceptionBuilder {


        ServiceExceptionBuilder() {
            this.attributes = new LinkedHashMap<>();
            this.errors = new ArrayList<>();
        }

        public ServiceException build() {
            return new ServiceException(
                    this.message,
                    this.cause,
                    this.code,
                    Collections.unmodifiableMap(this.attributes),
                    this.status,
                    Collections.unmodifiableList(this.errors),
                    isCritical);
        }

        public ServiceExceptionBuilder attribute(String name, Object value) {
            this.attributes.put(name, String.valueOf(value));
            return this;
        }

        public ServiceExceptionBuilder error(ErrorCode error) {
            this.errors.add(error);
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
