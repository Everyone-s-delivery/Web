package everyone.delivery.demo.common.exception.error;

import everyone.delivery.demo.common.response.ResponseError;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.EnumSet;

/***
 * 미리 정의해 둔 에러들
 */
@AllArgsConstructor
public enum CommonError implements RestError{

    UNAUTHORIZED_JWT_TOKEN(HttpStatus.UNAUTHORIZED,"JWT: 해당 리소스에 접근할 권한이 없습니다.(토큰은 있으나 권한 없음)"),
    NOT_INCLUDE_JWT_TOKEN(HttpStatus.UNAUTHORIZED,"JWT: 해당 리소스에 접근할 권한이 없습니다.(토큰 없음)"),
    INVALID_DATA(HttpStatus.BAD_REQUEST,"요청 오류 입니다.(의미상 오류: api 스팩은 맞지만 논리상 안맞는 요청)"),
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "요청 오류 입니다.(형식상 오류: api 스팩에 안맞는 요청)"),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버 오류 입니다.");

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
