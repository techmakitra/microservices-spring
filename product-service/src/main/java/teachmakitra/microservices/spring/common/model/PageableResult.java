package teachmakitra.microservices.spring.common.model;

import lombok.Getter;
import lombok.Value;

import java.util.List;
@Getter
@Value
public class PageableResult<T> {
    long offset;
    long limit;
    long totalCount;
    List<T> data;

    public PageableResult(long offset, long limit, long totalCount, List<T> data) {
        this.offset = offset;
        this.limit = limit;
        this.totalCount = totalCount;
        this.data = data;
    }

    public static <T> PageableResult<T> create(long offset, long limit, long totalCount, List<T> data) {
        return new PageableResult<>(offset, limit, totalCount, data);
    }
}
