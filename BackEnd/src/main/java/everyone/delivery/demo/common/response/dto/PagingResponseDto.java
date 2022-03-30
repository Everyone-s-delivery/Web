package everyone.delivery.demo.common.response.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.Collections;

/***
 * > 페이징 응답의 공통 부분을 추출
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PagingResponseDto {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)  //역직렬화(json to object) 과정에서 제외
    private boolean next;     // 다음 페이지 유무

    private Collection data;   // 페이징 데이터
}
