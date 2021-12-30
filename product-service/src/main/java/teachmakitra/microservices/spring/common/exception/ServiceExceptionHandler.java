package teachmakitra.microservices.spring.common.exception;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.message.ObjectMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import teachmakitra.microservices.spring.common.dto.ProblemDto;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class ServiceExceptionHandler {


    private static final Logger logger = LogManager.getLogger();

    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<ProblemDto> handleServiceException(ServiceException exception, WebRequest request) {
        String httpStatus = formatStatus(exception.getStatus());
        String code = formatCode(exception.getCode());
        String message = formatMessage(exception.getMessage());
        List<String> errors = formatErrors(exception.getErrors());

        LogBuilder logBuilder = logger.atLevel(exception.isCritical() ? Level.ERROR : Level.WARN);
        if (exception.isCritical()) {
            logBuilder.withThrowable(exception);
        }

        logBuilder.log(() -> new ObjectMessage(ProblemLog.builder()
                                                         .httpStatus(httpStatus)
                                                         .code(code)
                                                         .message(message)
                                                         .errors(errors)
                                                         .attributes(exception.getAttributes())
                                                         .build()));

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
