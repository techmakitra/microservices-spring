package teachmakitra.microservices.spring.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class ResponseDto<T>{
    @Getter
    @JsonProperty("data")
    private T data;

    public static <T> ResponseDto<T> of(T data) {
        return new ResponseDto<>(data);
    }
}
