package everyone.delivery.demo.common.exception;

import everyone.delivery.demo.common.exception.error.RestError;
import lombok.Data;

/***
 * > 목적 1: 로직상 분기를 if ~ else 와 return 의 반복으로 안하고 보다 깔끔하게 하기 위함
 * > 목적 2: spring 의 예외에 대한 handler & advice 기능을 활용하여 예외 상황 시 응답을 공통화 하기 위함
 */
@Data
public class LogicalRuntimeException extends RuntimeException{
    private RestError restError;

    /***
     * > 오로지 restError 를 인자로 받는 생성자만 허용
     * > LogicalRuntimeException 은 반드시 restError 를 가져야 함을 강제한다.
     * @param restError
     */
    public LogicalRuntimeException(RestError restError){
        this.restError = restError;
    }

    /***
     * 로직상 던지는 오류이기 때문에 stackTrace 를 안만들도록(성능 up)
     * 예외에 대한 로깅이 필수인 곳에는 사용하면 안됨
     * @return
     */
    @Override
    public synchronized Throwable fillInStackTrace() {
        return null;
    }
}
