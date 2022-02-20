package everyone.delivery.demo.security.user;


import com.fasterxml.jackson.annotation.JsonIgnore;
import everyone.delivery.demo.domain.post.PostEntity;
import everyone.delivery.demo.domain.postComment.PostCommentEntity;
import everyone.delivery.demo.security.user.dtos.UserDto;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@SequenceGenerator(name = "userTable_SEQ_GENERATOR", sequenceName = "userTable_SEQ", initialValue = 1, allocationSize = 1)
@Entity
@Table(name = "userTable")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class UserEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE , generator="userTable_SEQ_GENERATOR")
    private Long userId;

    @Column(length = 50, nullable = false)
    private String email;

    @Column(length = 50, nullable = false, unique=true)
    private String nickName;

    @Column(length = 300, nullable = false)
    private String password;

    // 참고: https://gunju-ko.github.io/jpa/2019/06/15/JPA-%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%B0%8D-%EC%9E%85%EB%AC%B8-chapter09.-%EA%B0%92-%EC%BB%AC%EB%A0%89%EC%85%98-%EB%A7%A4%ED%95%91.html
    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)    //이넘 값을 그대로 디비에 저장
    private List<UserRole> roles;

    @Column(length = 300, nullable = false)
    private String address;

    @CreatedDate
    @Column(updatable = false)
	private LocalDateTime regDate;		//등록일자

    @LastModifiedDate
    private LocalDateTime updateDate;	//수정일자

    /***
     * > user Entity 에 post 와 postComment 관계를 넣은 이유
     *      > user 가 지워질 때 함께 지워지게 하기 위함
     *      > 우선 이 용도 말고는 해당 필드를 사용하지는 않을 예정임
     */
    @OneToMany(fetch = LAZY, cascade = CascadeType.ALL) // userEntity 에 딸려있는 post 는 userEntity 에 전파된다.
    @JoinColumn(name="user_id")
    private List<PostEntity> posts;

    @OneToMany(fetch = LAZY, cascade = CascadeType.ALL) // userEntity 에 딸려있는 comment 는 userEntity 에 전파된다.
    @JoinColumn(name="user_id")
    private List<PostCommentEntity> comments;

    public UserDto toDTO(){
        return UserDto.builder()
                .userId(userId)
                .email(email)
                .nickName(nickName)
                .password(password)
                .roles(roles)
                .address(address)
                .regDate(regDate)
                .updateDate(updateDate)
                .build();
    }
}