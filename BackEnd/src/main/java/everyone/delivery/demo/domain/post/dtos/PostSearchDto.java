package everyone.delivery.demo.domain.post.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.PageRequest;

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



}
