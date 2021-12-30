package teachmakitra.microservices.spring.common.dto;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProblemDto {
    private String status;
    private String code;
    private String message;
    @Singular
    private List<String> errors;
}
