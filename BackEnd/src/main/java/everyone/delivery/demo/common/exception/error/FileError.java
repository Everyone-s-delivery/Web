package everyone.delivery.demo.common.exception.error;

import everyone.delivery.demo.common.response.ResponseError;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.EnumSet;

@Getter
@AllArgsConstructor
public enum FileError implements RestError{

    NOT_IMAGE_EXTENSION(HttpStatus.BAD_REQUEST, "파일 처리 실패. 이미지 파일이 아닙니다.");

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
