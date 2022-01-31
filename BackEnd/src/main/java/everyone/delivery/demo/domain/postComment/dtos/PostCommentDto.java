package everyone.delivery.demo.domain.postComment.dtos;


import com.fasterxml.jackson.annotation.JsonProperty;
import everyone.delivery.demo.domain.postComment.PostCommentEntity;
import everyone.delivery.demo.domain.postComment.PostCommentRepository;
import everyone.delivery.demo.security.user.UserEntity;
import everyone.delivery.demo.security.user.UserRepository;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Optional;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostCommentDto {

    private Long postCommentId;

    @NotNull(message = "Not enough comment data. commenterId cannot be null.")
    private Long commenterId;

    @NotNull(message = "Not enough comment data. commenterEmail cannot be null.")
    private String commenterEmail;

    @NotNull(message = "Not enough comment data. commenterNickName cannot be null.")
    private String commenterNickName;

    @NotNull(message = "Not enough comment data. postId cannot be null.")
    private Long postId;

    @NotNull(message = "Not enough comment data. comment cannot be null.")
    private String comment;

    //역직렬화(json to java object) 시 사용 안함 => 역직렬화 할때는 이 필드를 전혀 신경쓰지않고 값이 있든 없든 null 로 채우게된다.
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime regDate;		//등록일자

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime updateDate;	//수정일자
}
