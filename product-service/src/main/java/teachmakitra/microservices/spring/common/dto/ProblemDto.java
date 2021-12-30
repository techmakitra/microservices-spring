package teachmakitra.microservices.spring.common.dto;

import lombok.*;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProblemDto {
    private String code;
    private String message;
    private Map<String, String> parameters;
}
