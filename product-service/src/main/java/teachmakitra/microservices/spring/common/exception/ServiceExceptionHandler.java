package teachmakitra.microservices.spring.common.exception;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import teachmakitra.microservices.spring.common.dto.ProblemDto;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class ServiceExceptionHandler {


    private static final Logger logger = LogManager.getLogger();

    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<ProblemDto> handleServiceException(ServiceException exception, WebRequest request) {
        ProblemDto problemDto = ProblemDto.builder()
                                          .status(formatStatus(exception.getStatus()))
                                          .code(formatCode(exception.getCode()))
                                          .message(formatMessage(exception.getMessage()))
                                          .errors(formatErrors(exception.getErrors()))
                                          .build();
        logger.atWarn()
              .withThrowable(exception)
              .log("{}/{}/{} - {}: {}",
                   problemDto::getStatus,
                   problemDto::getCode,
                   () -> problemDto.getErrors().toString(),
                   exception::getMessage,
                   () -> exception.getAttributes().toString());

        return ResponseEntity.status(exception.getStatus())
                             .body(problemDto);
    }

    private Collection<String> formatErrors(List<ErrorCode> errors) {
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
