package everyone.delivery.demo.domain.post.dtos;

import everyone.delivery.demo.domain.postComment.dtos.PostCommentDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;
/***
 * 모집 글 수정 api에서 데이터 받기 위한 DTO
 * id값은 pathVariable로 컨트롤러에서 받는다.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdatePostDto {

    @NotNull(message = "Not enough post data. title cannot be null.")
    private String title;

    @NotNull(message = "Not enough post data. description cannot be null.")
    private String description;

    private List<String> addresses;
}
