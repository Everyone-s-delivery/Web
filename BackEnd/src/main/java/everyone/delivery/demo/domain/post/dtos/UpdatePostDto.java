package everyone.delivery.demo.domain.post.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

/***
 * 모집 글 수정 api에서 데이터 받기 위한 DTO
 * id값은 pathVariable로 컨트롤러에서 받는다.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdatePostDto {

    private String title;

    private String description;

    private Set<String> addresses;
}
