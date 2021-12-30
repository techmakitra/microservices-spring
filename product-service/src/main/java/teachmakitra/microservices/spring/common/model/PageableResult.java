package teachmakitra.microservices.spring.common.model;

import lombok.Getter;
import teachmakitra.microservices.spring.common.exception.ServiceException;

import java.util.List;
@Getter
public class PageableResult<T> {
    private long offset;
    private long limit;
    private long totalCount;
    private List<T> data;

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
