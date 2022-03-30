package everyone.delivery.demo.common.response;

import everyone.delivery.demo.common.exception.error.RestError;
import everyone.delivery.demo.common.request.dto.PagingRequestDto;
import everyone.delivery.demo.common.response.dto.PagingResponseDto;
import org.springframework.http.ResponseEntity;

import java.util.Collection;

public class ResponseUtils {

    /***
     * data만 들어오면 성공 응답
     * @param data
     * @param <T>
     * @return
     */
    public static <T> ResponseEntity out(T data){
        return out(convertSuccessResponse(data));
    }

    /***
     * >  Slice 형태의 페이징에서 다음 페이지 유무를 서버에서 판단해서 내려주기 위한 메소드
     * >  api 의 직관성을 위해 이렇게 구현하는 것이 좋다고 결정해서 구현 함
     * @param pagingData
     * @param pagingRequestDto
     * @param <T>
     * @return
     */
    public static <T> ResponseEntity out(Collection pagingData, PagingRequestDto pagingRequestDto){
        PagingResponseDto result;
        if(pagingData.size() == pagingRequestDto.getLimit() + 1){
            pagingData.remove(pagingData.toArray()[pagingData.size()-1]);
            result = PagingResponseDto.builder().next(true).data(pagingData).build();
        }
        else{
            result = PagingResponseDto.builder().next(false).data(pagingData).build();
        }
        return out(convertSuccessResponse(result));
    }

    /***
     * RestError만 들어오면 실패 응답
     * @param restError
     * @return
     */
    public static ResponseEntity out(RestError restError){
        return ResponseEntity.status(restError.getHttpStatus()).body(restError.toResponseError());
    }

    /***
     * Response가 들어오면 객체를 까서 성공 & 실패를 판단 후 적절한 응답
     * @param response
     * @return
     */
    public static ResponseEntity out(Response response){
        if(response.hasError()){
            RestError restError = response.getError();
            return ResponseEntity.status(restError.getHttpStatus()).body(restError.toResponseError());
        }
        else{
            return ResponseEntity.ok(response.getData());
        }
    }

    /***
     * data -> 성공 형태의 Response로 변환
     * @param data
     * @param <T>
     * @return
     */
    public static <T> Response<T> convertSuccessResponse(T data){
        return new Response<T>(data,null);
    }
}
