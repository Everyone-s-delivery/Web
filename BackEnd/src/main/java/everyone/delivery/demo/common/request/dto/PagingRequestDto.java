package everyone.delivery.demo.common.request.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/***
 * > 페이징 요청의 공통 부분을 추출
 * > 페이징 요청 DTO 는 PageRequestDto 를 상속해서 사용할 것
 * > 이렇게 사용하면 상속받는 클래스는 검색 조건만 선언하면 됨
 */
@Data
@NoArgsConstructor
public class PagingRequestDto {

    private Long cursor;                // 페이징 조건: 커서
    private Integer fetchSize;          // 페이징 조건: 페치 사이즈
    private OrderBy orderBy;            // 페이징 조건: 오름차순, 내림차순
    private KeyColumn keyColumn;        // 페이징 조건: 정렬 키

    public PagingRequestDto(Long cursor, Integer fetchSize, OrderBy orderBy, KeyColumn keyColumn) {
        this.cursor = cursor;
        this.fetchSize = fetchSize;
        this.orderBy = orderBy;
        this.keyColumn = keyColumn;
    }

    /***
     * > 정렬 조건이 ASC 이면 true
     * @return
     */
    public boolean isAsc(){
        return this.orderBy.equals(OrderBy.ASC);
    }


}
