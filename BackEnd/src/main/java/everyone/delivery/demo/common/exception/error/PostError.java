package everyone.delivery.demo.common.exception.error;

import everyone.delivery.demo.common.response.ResponseError;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum PostError implements RestError{
    NO_AUTHORITY_TO_MODIFY(HttpStatus.BAD_REQUEST, "요청한 사용자는 작성자가 아닙니다. 글 수정 권한이 없습니다."),
    NOT_FOUND_POST(HttpStatus.BAD_REQUEST, "요청한 아이디에 해당하는 글이 존재하지 않습니다.");

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
