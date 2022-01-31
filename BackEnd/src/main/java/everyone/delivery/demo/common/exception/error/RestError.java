package everyone.delivery.demo.common.exception.error;

import everyone.delivery.demo.common.response.ResponseError;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.EnumSet;

public interface RestError {

    public ResponseError toResponseError();

    public HttpStatus getHttpStatus();

    public String getErrorMsg();

}
