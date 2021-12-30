package teachmakitra.microservices.spring.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
public class ListResponseDto<T> {
    @Getter
    @JsonProperty("data")
    private List<T> data;

    @Getter
    @JsonProperty("page")
    private PageDto page;


    public static <T> ListResponseDto<T> of(List<T> data, PageDto page) {
        return new ListResponseDto<>(data, page);
    }

    public static <T> ListResponseDto<T> of(List<T> data, long offset, long limit, long totalCount) {
        return of(data,
                PageDto.builder()
                        .offset(offset)
                        .limit(limit)
                        .count(data.size())
                        .totalCount(totalCount)
                        .build());
    }

    public static <T> ListResponseDto<T> of(List<T> data) {
        return of(data,
                PageDto.builder()
                        .offset(0)
                        .limit(data.size())
                        .count(data.size())
                        .totalCount(data.size())
                        .build());
    }

}
