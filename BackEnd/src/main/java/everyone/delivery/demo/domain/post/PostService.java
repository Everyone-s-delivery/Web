package everyone.delivery.demo.domain.post;

import everyone.delivery.demo.common.exception.ExceptionUtils;
<<<<<<< HEAD
=======
import everyone.delivery.demo.common.exception.LogicalRuntimeException;
import everyone.delivery.demo.common.exception.error.PostError;
import everyone.delivery.demo.common.exception.error.UserError;
>>>>>>> dev
import everyone.delivery.demo.domain.post.dtos.CreatePostDto;
import everyone.delivery.demo.domain.post.dtos.PostDetailDto;
import everyone.delivery.demo.domain.post.dtos.PostSearchDto;
import everyone.delivery.demo.domain.post.dtos.UpdatePostDto;
import everyone.delivery.demo.domain.post.repository.PostRepository;
import everyone.delivery.demo.domain.postComment.PostCommentEntity;
import everyone.delivery.demo.domain.postComment.PostCommentService;
import everyone.delivery.demo.domain.postComment.dtos.PostCommentDto;
import everyone.delivery.demo.security.user.UserEntity;
import everyone.delivery.demo.security.user.UserRepository;
<<<<<<< HEAD
=======
import everyone.delivery.demo.security.user.dtos.UserDto;
>>>>>>> dev
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.ListUtils;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final PostCommentService postCommentService;

    /***
     * 조회
     * 모든 post 조회
     * 추후 검색조건 추가 필요
     * @return
     */
    public List<PostDetailDto> getList(){
        List<PostEntity> postEntities = postRepository.findAll();
        List<PostDetailDto> postDtos = new ArrayList<>();
        for(PostEntity postEntity: postEntities){
            postDtos.add(postEntity.toDto());
        }
        return postDtos;
    }

    /***
     * 조회
     * 검색조건과 페이징을 통한 post 조회
     * @return
     */
    public List<PostDetailDto> getPagedList(PostSearchDto postSearchDto){
        Slice<PostEntity> postEntitySlice = postRepository.getPagedList(postSearchDto);

        List<PostDetailDto> postDtoList = new ArrayList<>();
        for(PostEntity postEntity: ListUtils.emptyIfNull(postEntitySlice.getContent())){
            postDtoList.add(postEntity.toDto());
        }

        return postDtoList;
    }

    /***
     * 조회
     * postId에 해당하는 post 조회
     * @param postId
     * @return
     */
    public PostDetailDto getById(Long postId){
        Optional<PostEntity> postEntityOp = postRepository.findById(postId);
        PostEntity postEntity = ExceptionUtils
                .ifNullThrowElseReturnVal(postEntityOp,"postEntity is null. postId: {}",postId);
        return postEntity.toDto();
    }

    /***
     * 등록
     * basicPostDto 로 받은 덧글 정보를 디비에 등록
     * @param createPostDto
     * @return
     */
    @Transactional
<<<<<<< HEAD
    public PostDetailDto create(CreatePostDto createPostDto){
        PostEntity postEntity = convertDTOToEntity(createPostDto);
=======
    public PostDetailDto create(Long tokenUserId, CreatePostDto createPostDto){
        Optional<UserEntity> userEntityOp = userRepository.findByUserId(tokenUserId);
        UserEntity userEntity = ExceptionUtils.ifNullThrowElseReturnVal(
                UserError.INVALID_USER_ID, userEntityOp,"invalid tokenUserId. tokenUserId: {}", tokenUserId);

        PostEntity postEntity = convertDTOToEntity(userEntity, createPostDto);
>>>>>>> dev
        postEntity = postRepository.save(postEntity);
        return postEntity.toDto();
    }

    /***
     * 수정(제목 / 설명 글 / 주소 만 수정 가능)
     * postId에 해당하는 post를 수정
     * @param postId
     * @param updatePostDto
     * @return
     */
    @Transactional
<<<<<<< HEAD
    public PostDetailDto update(Long postId, UpdatePostDto updatePostDto){
=======
    public PostDetailDto update(UserDto tokenUserDto, Long postId, UpdatePostDto updatePostDto){
>>>>>>> dev
        Optional<PostEntity> postEntityOp = postRepository.findById(postId);
        PostEntity postEntity = ExceptionUtils
                .ifNullThrowElseReturnVal(PostError.NOT_FOUND_POST, postEntityOp, "postEntity is null. postId: {}", postId);

        if(!tokenUserDto.isAdmin() && postEntityOp.get().getPoster().getUserId() != tokenUserDto.getUserId()){
            //관리자도 아니고, 작성한 사람도 아닌 경우
            log.error("requested user is not the author. do not have permission to modify. tokenUserId: {}", tokenUserDto.getUserId());
            throw new LogicalRuntimeException(PostError.NO_AUTHORITY_TO_MODIFY);
        }

        postEntity.setTitle(updatePostDto.getTitle());
        postEntity.setDescription(updatePostDto.getDescription());
        postEntity.setAddresses(updatePostDto.getAddresses());
        postEntity = postRepository.save(postEntity);
        return postEntity.toDto();
    }

    /***
     * 삭제
     * postId 해당하는 글 삭제
     * TODO: 딸린 덧글도 다 같이 삭제되는지 확인
     * @param postId
     * @return
     */
    @Transactional
    public Long delete(UserDto tokenUserDto, Long postId){
        Optional<PostEntity> postEntityOp = postRepository.findById(postId);
        ExceptionUtils.ifNullThrowElseReturnVal(PostError.NOT_FOUND_POST,
                postEntityOp,"postEntity is null. postId: {}", postId);
        if(!tokenUserDto.isAdmin() && postEntityOp.get().getPoster().getUserId() != tokenUserDto.getUserId()){
            //관리자도 아니고, 작성한 사람도 아닌 경우
            log.error("requested user is not the author. do not have permission to modify. tokenUserId: {}", tokenUserDto.getUserId());
            throw new LogicalRuntimeException(PostError.NO_AUTHORITY_TO_MODIFY);
        }

        postRepository.deleteById(postId);
        return postId;
    }


    public PostEntity convertDTOToEntity(PostDetailDto postDto){
        Optional<UserEntity> userEntityOp = userRepository.findByUserId(postDto.getPosterId());
        UserEntity userEntity = ExceptionUtils.ifNullThrowElseReturnVal(userEntityOp);
        List<PostCommentDto> commentDtos = postDto.getComments();
        List<PostCommentEntity> commentEntities = new ArrayList<>();
        for (PostCommentDto commentDto: commentDtos){
            commentEntities.add(postCommentService.convertDTOToEntity(commentDto));
        }

        return PostEntity.builder()
                .postId(postDto.getPostId())
                .poster(userEntity)
                .title(postDto.getTitle())
                .description(postDto.getDescription())
                .addresses(postDto.getAddresses())
                .comments(commentEntities)
                .regDate(postDto.getRegDate())
                .updateDate(postDto.getUpdateDate())
                .build();
    }

    public PostEntity convertDTOToEntity(UserEntity userEntity, CreatePostDto createPostDto){
        return PostEntity.builder()
                .poster(userEntity)
                .title(createPostDto.getTitle())
                .description(createPostDto.getDescription())
                .addresses(createPostDto.getAddresses())
                .build();
    }
}
