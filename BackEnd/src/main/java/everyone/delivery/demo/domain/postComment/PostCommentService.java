package everyone.delivery.demo.domain.postComment;

import everyone.delivery.demo.common.exception.ExceptionUtils;
import everyone.delivery.demo.common.utils.TimeUtils;
import everyone.delivery.demo.domain.post.PostEntity;
import everyone.delivery.demo.domain.post.repository.PostRepository;
import everyone.delivery.demo.domain.postComment.dtos.CreatePostCommentDto;
import everyone.delivery.demo.domain.postComment.dtos.PostCommentDto;
import everyone.delivery.demo.security.user.UserEntity;
import everyone.delivery.demo.security.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostCommentService {

    private final PostCommentRepository postCommentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;


    /***
     * 조회
     * postCommentId에 해당하는 덧글 반환
     * @param postCommentId
     * @return
     */
    public PostCommentDto getById(Long postCommentId){
        Optional<PostCommentEntity> postCommentEntityOp = postCommentRepository.findById(postCommentId);
        PostCommentEntity postCommentEntity = ExceptionUtils
                .ifNullThrowElseReturnVal(postCommentEntityOp,"postCommentEntity is null. postCommentId: {}", postCommentId);
        return postCommentEntity.toDto();
    }

    /***
     * 등록
     * postCommentDto로 받은 덧글 정보를 디비에 등록
     * @param createPostCommentDto
     * @return
     */
    @Transactional
    public PostCommentDto create(CreatePostCommentDto createPostCommentDto){
        PostCommentEntity postCommentEntity = convertDTOToEntity(createPostCommentDto);
        postCommentEntity = postCommentRepository.save(postCommentEntity);
        return postCommentEntity.toDto();
    }

    /***
     * 수정(덧글은 comment만 수정 가능)
     * postCommentId에 해당하는 comment를 수정
     * @param postCommentId
     * @param comment
     * @return
     */
    @Transactional
    public PostCommentDto update(Long postCommentId, String comment){
        Optional<PostCommentEntity> postCommentEntityOp = postCommentRepository.findById(postCommentId);
        PostCommentEntity postCommentEntity = ExceptionUtils
                .ifNullThrowElseReturnVal(postCommentEntityOp,"postCommentEntity is null. postCommentId: {}", postCommentId );
        postCommentEntity.changeComment(comment);
        postCommentEntity = postCommentRepository.save(postCommentEntity);

        return postCommentEntity.toDto();
    }



    /***
     * 삭제
     * postCommentId에 해당하는 덧글 삭제
     * @param postCommentId
     * @return
     */
    @Transactional
    public Long delete(Long postCommentId){
        Optional<PostCommentEntity> postCommentEntity = postCommentRepository.findById(postCommentId);
        ExceptionUtils.ifNullThrowElseReturnVal(postCommentEntity,"postCommentEntity is null. postCommentId: {}", postCommentId);
        postCommentRepository.deleteById(postCommentId);
        return postCommentId;
    }


    public PostCommentEntity convertDTOToEntity(PostCommentDto postCommentDto){
        Optional<UserEntity> userEntityOp = userRepository.findByUserId(postCommentDto.getCommenterId());
        UserEntity userEntity = ExceptionUtils.ifNullThrowElseReturnVal(userEntityOp);

        Optional<PostEntity> postEntityOp = postRepository.findById(postCommentDto.getPostId());
        PostEntity postEntity = ExceptionUtils.ifNullThrowElseReturnVal(postEntityOp);

        return PostCommentEntity.builder()
                .postCommentId(postCommentDto.getPostCommentId())
                .commenter(userEntity)
                .post(postEntity)
                .comment(postCommentDto.getComment())
                .regDate(TimeUtils.longToLocalDateTime(postCommentDto.getRegDate()))
                .updateDate(TimeUtils.longToLocalDateTime(postCommentDto.getUpdateDate()))
                .build();
    }

    public PostCommentEntity convertDTOToEntity(CreatePostCommentDto basicPostCommentDto){
        Optional<UserEntity> userEntityOp = userRepository.findByUserId(basicPostCommentDto.getCommenterId());
        UserEntity userEntity = ExceptionUtils.ifNullThrowElseReturnVal(userEntityOp);
        Optional<PostEntity> postEntityOp = postRepository.findById(basicPostCommentDto.getPostId());
        PostEntity postEntity = ExceptionUtils.ifNullThrowElseReturnVal(postEntityOp);

        return PostCommentEntity.builder()
                .commenter(userEntity)
                .comment(basicPostCommentDto.getComment())
                .post(postEntity)
                .build();
    }
}
