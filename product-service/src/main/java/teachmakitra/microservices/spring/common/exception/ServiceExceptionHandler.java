package teachmakitra.microservices.spring.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import teachmakitra.microservices.spring.common.dto.ProblemDto;

@ControllerAdvice
public class ServiceExceptionHandler {

    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<ProblemDto> handleServiceException(ServiceException exception, WebRequest request) {
        return ResponseEntity.status(exception.getStatus())
                             .body(ProblemDto.builder()
                                           .status(formatStatus(exception.getStatus()))
                                           .code(formatCode(exception.getCode()))
                                           .message(formatMessage(exception.getMessage()))
                                           .errors(exception.getErrors())
                                           .build());
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
