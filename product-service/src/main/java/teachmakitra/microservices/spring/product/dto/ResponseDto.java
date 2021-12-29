package teachmakitra.microservices.spring.product.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Value;

import java.util.List;

@AllArgsConstructor
public class ResponseDto<T>{
    @Getter
    @JsonProperty("data")
    private T data;

    public static <T> ResponseDto<T> of(T data) {
        return new ResponseDto<>(data);
    }
}
