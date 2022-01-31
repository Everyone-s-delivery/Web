package everyone.delivery.demo.common.exception.error;

import everyone.delivery.demo.common.response.ResponseError;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.EnumSet;

@Getter
@AllArgsConstructor
public enum UserError implements RestError{

    OVERLAP_EMAIL(HttpStatus.BAD_REQUEST, "중복된 이메일이 있습니다."),
    OVERLAP_NICKNAME(HttpStatus.BAD_REQUEST, "중복된 닉네임이 있습니다."),

    INVALID_USER_ID(HttpStatus.BAD_REQUEST, "올바른 사용자 아이디가 아닙니다. 사용자 아이디를 확인해 주세요."),
    INVALID_PASSWORD(HttpStatus.BAD_REQUEST, "올바른 비밀번호가 아닙니다. 비밀번호를 확인해 주세요."),
    INVALID_EMAIL(HttpStatus.BAD_REQUEST, "올바른 이메일이 아닙니다. 이메일을 확인해 주세요.");

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
