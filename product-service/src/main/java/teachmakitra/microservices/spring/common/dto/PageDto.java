package teachmakitra.microservices.spring.common.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Value;

@Data
@Builder
public class PageDto {
    private long offset;
    private long limit;
    private long count;
    private long totalCount;
}
