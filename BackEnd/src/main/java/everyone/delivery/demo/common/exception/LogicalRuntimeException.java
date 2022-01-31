package everyone.delivery.demo.common.exception;

import everyone.delivery.demo.common.exception.error.RestError;
import lombok.Data;

@Data
public class LogicalRuntimeException extends RuntimeException{
    private RestError restError;

    public LogicalRuntimeException(){
        super();
    }

    public LogicalRuntimeException(RestError restError){
        this.restError = restError;
    }

    /***
     * 로직상 던지는 오류이기 때문에 stackTrace를 안만들도록(성능 up)
     * 예외에 대한 로깅이 필수인 곳에는 사용하면 안됨
     * @return
     */
    @Override
    public synchronized Throwable fillInStackTrace() {
        return null;
    }
}
