package everyone.delivery.demo.domain.postComment;


import everyone.delivery.demo.domain.post.PostEntity;
import everyone.delivery.demo.domain.postComment.dtos.PostCommentDto;
import everyone.delivery.demo.security.user.UserEntity;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

import static javax.persistence.FetchType.LAZY;

@SequenceGenerator(name = "postCommentTable_SEQ_GENERATOR", sequenceName = "postCommentTable_SEQ", initialValue = 1, allocationSize = 1)
@Entity
@Table(name = "postCommentTable")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class PostCommentEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE , generator="postCommentTable_SEQ_GENERATOR")
    private Long postCommentId;

    @ManyToOne
    @JoinColumn(name="user_id")
    private UserEntity commenter;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "post_id")
    private PostEntity post;

    @Column(length = 3000, nullable = false)
    private String comment;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime regDate;		//등록일자

    @LastModifiedDate
    private LocalDateTime updateDate;	//수정일자

    public PostCommentDto toDto(){
        return PostCommentDto.builder()
                .postCommentId(this.postCommentId)
                .commenterId(this.commenter.getUserId())
                .commenterEmail(this.commenter.getEmail())
                .commenterNickName(this.commenter.getNickName())
                .postId(this.post.getPostId())
                .comment(this.comment)
                .regDate(this.regDate)
                .updateDate(this.updateDate)
                .build();
    }
}
