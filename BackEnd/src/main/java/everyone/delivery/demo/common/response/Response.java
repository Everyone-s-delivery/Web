package everyone.delivery.demo.common.response;

import everyone.delivery.demo.common.exception.error.CommonError;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Response<T> {
    private T data;
    private CommonError error;

    public boolean hasError(){
        return (error != null);
    }
}
