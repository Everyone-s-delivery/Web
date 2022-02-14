package everyone.delivery.demo.domain.post.dtos;

import everyone.delivery.demo.common.validation.annotaion.MustExistUserId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

/***
 * 글 검색 조건
 *      > 작성자
 *      > 제목
 *      > 주소
 *      > 검색 기간: 마지막 수정 시간 또는 생성 시간 기준 start ~ end
 * 정렬 조건
 *      > 마지막 수정 시간 또는 생성 시간(검색 기간과 동기화)
 * 페이징
 *      > offset & limit
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostSearchDto {

    private List<Long> posterIdList;    //TODO: List Validation check: 리스트의 모든 값이 0 이상이여야 함
    private String title;
    private List<String> addresses;

    @NotNull
    private KeyColumn keyColumn;

    @NotNull
    private boolean isASC;

    @NotNull
    private LocalDateTime startDate;

    @NotNull
    private LocalDateTime endDate;

    @Min(value = 0, message = "offset cannot be minus.")
    @NotNull
    private Integer offset;

    @Min(value = 1, message = "limit cannot be minus.")
    @NotNull
    private Integer limit;

    public static enum KeyColumn{
        REG_DATE, UPDATE_DATE;
    }
}
