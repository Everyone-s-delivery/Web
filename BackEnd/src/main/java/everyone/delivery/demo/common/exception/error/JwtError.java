package everyone.delivery.demo.common.exception.error;

import everyone.delivery.demo.common.response.ResponseError;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

/***
 * 미리 정의해 둔 에러들
 */
@AllArgsConstructor
public enum JwtError implements RestError{

    UNAUTHORIZED_JWT_TOKEN(HttpStatus.UNAUTHORIZED,"JWT: 해당 리소스에 접근할 권한이 없습니다.(유효한 토큰은 있으나 권한 없음)"),
    INVALID_JWT_TOKEN(HttpStatus.UNAUTHORIZED,"JWT: 해당 리소스에 접근할 권한이 없습니다.(유효하지 않은 토큰)");

    private HttpStatus httpStatus;
    private String errorMsg;

    @Override
    public String toString(){
        return this.name();
    }

    @Override
    public ResponseError toResponseError(){
        return ResponseError.builder()
                .errorCode(this.name())
                .errorMessage(this.errorMsg).build();
    }

    @Override
    public HttpStatus getHttpStatus() {
        return this.httpStatus;
    }

    @Override
    public String getErrorMsg() {
        return this.errorMsg;
    }
}
