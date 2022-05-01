package everyone.delivery.demo.domain.post;

import everyone.delivery.demo.domain.post.dtos.PostDetailDto;
import everyone.delivery.demo.domain.post.dtos.PostDto;
import everyone.delivery.demo.domain.postComment.PostCommentEntity;
import everyone.delivery.demo.domain.postComment.dtos.PostCommentDto;
import everyone.delivery.demo.security.user.UserEntity;
import lombok.*;
import org.apache.commons.collections4.ListUtils;
import org.hibernate.annotations.BatchSize;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static javax.persistence.FetchType.EAGER;
import static javax.persistence.FetchType.LAZY;

@SequenceGenerator(name = "postTable_SEQ_GENERATOR", sequenceName = "postTable_SEQ", initialValue = 1, allocationSize = 1)
@Entity
@Table(name = "postTable")
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class PostEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE , generator="postTable_SEQ_GENERATOR")
    private Long postId;

    /***
     * > poster 에 해당하는 사용자가 삭제될 때 해당 comment 도 삭제되는게 맞나?
     * > 우선 삭제되는 것으로 구현
     */
    @ManyToOne
    @JoinColumn(name="user_id")
    private UserEntity poster;

    @Column(length = 300, nullable = false)
    private String title;

    @Lob
    @Column(nullable = false)
    private String description;

    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> addresses;

    @OneToMany(fetch = LAZY, cascade = CascadeType.ALL) // postEntity에 딸려있는 comment는 postEntity에 전파된다.
    @JoinColumn(name="post_id")
    private List<PostCommentEntity> comments;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime regDate;		//등록일자

    @LastModifiedDate
    private LocalDateTime updateDate;	//수정일자

    /***
     * > 게시글 기본 정보 수정 메소드
     * @param title
     * @param description
     * @param addresses
     */
    public void changeBasicInfo(String title, String description, Set<String> addresses){
        this.title = title;
        this.description = description;
        this.addresses = addresses;
    }

    /***
     * > 글 entity 단건에 대해서는 글에 딸린 덧글 까지 보여주기
     * @return
     */
    public PostDetailDto toDetailDto(){
        List<PostCommentDto> postCommentDtos = new ArrayList<>();
        for(PostCommentEntity postCommentEntity: ListUtils.emptyIfNull(this.comments)){
            postCommentDtos.add(postCommentEntity.toDto());
        }
        return PostDetailDto.builder()
                .postId(this.postId)
                .posterId(this.poster.getUserId())
                .posterEmail(this.poster.getEmail())
                .posterNickName(this.poster.getNickName())
                .title(this.title)
                .description(this.description)
                .addresses(this.addresses)
                .comments(postCommentDtos)
                .regDate(this.regDate)
                .updateDate(this.updateDate)
                .build();
    }

    public PostDto toDto(){
        return PostDto.builder()
                .postId(this.postId)
                .posterId(this.poster.getUserId())
                .posterEmail(this.poster.getEmail())
                .posterNickName(this.poster.getNickName())
                .title(this.title)
                .description(this.description)
                .addresses(this.addresses)
                .regDate(this.regDate)
                .updateDate(this.updateDate)
                .build();
    }
}
