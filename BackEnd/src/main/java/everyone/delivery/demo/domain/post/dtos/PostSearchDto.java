package everyone.delivery.demo.domain.post.dtos;

import everyone.delivery.demo.common.request.dto.PagingRequestDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

/***
 * 글 검색 조건
 *      > 작성자
 *      > 제목
 *      > 주소
 *      > 검색 기간: 마지막 수정 시간 또는 생성 시간 기준 start ~ end
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostSearchDto extends PagingRequestDto {

    //TODO: List Validation check: 리스트의 모든 값이 0 이상이여야 함
    private List<Long> posterIdList;    // 검색조건: 작성자 아이디 리스트(IN 검색)
    private String title;               // 검색조건: 글 제목
    private List<String> addresses;     // 검색조건: 주소 리스트(IN 검색)
    @NotNull
    private LocalDateTime startDate;    // 검색조건: 시작 기간
    @NotNull
    private LocalDateTime endDate;      // 검색조건: 끝 기간
    @NotNull
    private KeyColumn keyColumn;        // 페이징 조건: 정렬 key

    /***
     * > 정렬 조건을 enum 으로
     */
    public static enum KeyColumn{
        REG_DATE, UPDATE_DATE;
    }
}
