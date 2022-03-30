package everyone.delivery.demo.common.request.dto;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/***
 * > 페이징 요청의 공통 부분을 추출
 * > 페이징 요청 DTO 는 PageRequestDto 를 상속해서 사용할 것
 * > 이렇게 사용하면 상속받는 클래스는 검색 조건만 선언하면 됨
 */
@Data
public class PagingRequestDto {

    @Min(value = 0, message = "offset cannot be minus.")
    @NotNull
    private Integer offset;             // 페이징 조건: 시작 위치
    @Min(value = 1, message = "limit cannot be minus.")
    @NotNull
    private Integer limit;              // 페이징 조건: 갯수
    @NotNull
    private OrderBy orderBy;            // 페이징 조건: 오름차순, 내림차순

    /***
     * > 정렬 조건이 ASC 이면 true
     * @return
     */
    public boolean isAsc(){
        return this.orderBy.equals(OrderBy.ASC);
    }

    /***
     * > 오름차순 내림 차순을 enum 으로
     */
    public static enum OrderBy{
        ASC,DESC;
    }
}
