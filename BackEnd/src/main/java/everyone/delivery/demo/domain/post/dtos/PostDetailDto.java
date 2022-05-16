package everyone.delivery.demo.domain.post.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import everyone.delivery.demo.common.validation.annotaion.MustExistUserId;
import everyone.delivery.demo.domain.postComment.dtos.PostCommentDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostDetailDto {

    private Long postId;

    @MustExistUserId
    @Min(value = 1, message = "posterId cannot be minus.")
    @NotNull(message = "Not enough post data. posterId cannot be null.")
    private Long posterId;          // 모집글에 대한 작성자 아이디

    //TODO: 이 부분 입력시에는 빼고 posterId로만 대체 가능?
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)  //역직렬화(json to object) 과정에서 제외
    private String posterEmail;       // 모집글에 대한 작성자 이메일

    //TODO: 이 부분 입력시에는 빼고 posterId로만 대체 가능?
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)  //역직렬화(json to object) 과정에서 제외
    private String posterNickName;

    @NotNull(message = "Not enough post data. title cannot be null.")
    private String title;

    @NotNull(message = "Not enough post data. description cannot be null.")
    private String description;

    private Set<String> addresses;

    private List<PostCommentDto> comments;

    //역직렬화(json to java object) 시 사용 안함 => 역직렬화 할때는 이 필드를 전혀 신경쓰지않고 값이 있든 없든 null 로 채우게된다.
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long regDate;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long updateDate;

}
