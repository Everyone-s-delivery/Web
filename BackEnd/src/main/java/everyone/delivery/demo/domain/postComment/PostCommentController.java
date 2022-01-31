package everyone.delivery.demo.domain.postComment;

import everyone.delivery.demo.common.response.ResponseUtils;
import everyone.delivery.demo.domain.postComment.dtos.CreatePostCommentDto;
import io.swagger.annotations.*;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;


@Validated
@Api(tags = {"* 모집 덧글 API(사용자[모집자 또는 참여자] 권한)"})
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/post/comments")
public class PostCommentController {

    private final PostCommentService postCommentService;

    @GetMapping("{postCommentId}")
    @ApiOperation(value = "덧글 개별 조회", notes = "postCommentId에 해당하는 덧글을 조회할 수 있습니다.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token(사용자 토큰)", required = true, dataType = "String", paramType = "header")
    })
    public ResponseEntity getById(@PathVariable @Min(value = 1, message = "postCommentId cannot be minus.")Long postCommentId){
        return ResponseUtils.out(postCommentService.getById(postCommentId));
    }

    @PostMapping("")
    @ApiOperation(value = "덧글 등록", notes = "덧글을 등록한다.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token(사용자 토큰)", required = true, dataType = "String", paramType = "header")
    })
    public ResponseEntity create(@Valid @RequestBody @ApiParam(value = "덧글 정보를 갖는 객체", required = true) CreatePostCommentDto createPostCommentDto){
        return ResponseUtils.out(postCommentService.create(createPostCommentDto));
    }

    @PutMapping("{postCommentId}")
    @ApiOperation(value = "덧글 수정", notes = "덧글을 수정한다.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token(사용자 토큰)", required = true, dataType = "String", paramType = "header")
    })
    public ResponseEntity update(@PathVariable @Min(value = 1, message = "postCommentId cannot be minus.")Long postCommentId,
                                    @ApiParam(value = "수정할 덧글", required = true) @RequestParam("comment") @NotNull(message = "comment should be included") String comment){
        return ResponseUtils.out(postCommentService.update(postCommentId,comment));
    }

    @DeleteMapping("/{postCommentId}")
    @ApiOperation(value = "기존 덧글 삭제",
            notes = "기존 덧글을 삭제합니다.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token(사용자 토큰)", required = true, dataType = "String", paramType = "header")
    })
    public ResponseEntity delete(@PathVariable @Min(value = 1, message = "postCommentId cannot be minus.") Long postCommentId) {
        return ResponseUtils.out(postCommentService.delete(postCommentId));
    }
}
