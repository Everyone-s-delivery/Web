package everyone.delivery.demo.domain.postComment.dtos;


import com.fasterxml.jackson.annotation.JsonProperty;
import everyone.delivery.demo.common.exception.ExceptionUtils;
import everyone.delivery.demo.domain.post.PostEntity;
import everyone.delivery.demo.domain.postComment.PostCommentEntity;
import everyone.delivery.demo.security.user.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Optional;

/***
 * 덧글 생성 api에서 데이터 받기 위한 DTO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreatePostCommentDto {

    @NotNull(message = "Not enough comment data. commenterId cannot be null.")
    private Long commenterId;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)  //역직렬화(json to object) 과정에서 제외
    private String commenterEmail;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)  //역직렬화(json to object) 과정에서 제외
    private String commenterNickName;

    @NotNull(message = "Not enough comment data. postId cannot be null.")
    private Long postId;

    @NotNull(message = "Not enough comment data. comment cannot be null.")
    private String comment;
}
