package teachmakitra.microservices.spring.common.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.Level;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import teachmakitra.microservices.spring.common.dto.ProblemDto;
import teachmakitra.microservices.spring.common.logging.LogMessage;

import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@ControllerAdvice
public class ServiceExceptionHandler {


    //private static final Logger logger = LogManager.getLogger();


    private ObjectMapper objectMapper = new ObjectMapper();

    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<ProblemDto> handleServiceException(ServiceException exception, WebRequest request) {
        String httpStatus = formatStatus(exception.getStatus());
        String code = formatCode(exception.getCode());
        String message = formatMessage(exception.getMessage());
        List<String> errors = formatErrors(exception.getErrors());

        log.atLevel(Level.WARN)
           .withThrowable(exception)
           .log(() -> LogMessage.withMessage("Service exception")
                                .value("message", message)
                                .value("httpStatus", httpStatus)
                                .value("code", code)
                                .value("errors", errors));
        return ResponseEntity.status(exception.getStatus())
                             .body(ProblemDto.builder()
                                             .status(httpStatus)
                                             .code(code)
                                             .message(message)
                                             .errors(errors)
                                             .build());
    }

    private List<String> formatErrors(List<ErrorCode> errors) {
        if (errors == null) {
            return List.of();
        }
        return errors.stream().map(ErrorCode::getValue).collect(Collectors.toList());
    }

    private String formatStatus(HttpStatus status) {
        return status != null ? status.name() : HttpStatus.INTERNAL_SERVER_ERROR.name();
    }

    private String formatCode(ErrorCode code) {
        return code != null ? code.getValue() : "";
    }

    private String formatMessage(String message) {
        return message != null ? message : "";
    }
}
